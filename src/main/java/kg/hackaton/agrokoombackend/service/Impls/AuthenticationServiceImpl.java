package kg.hackaton.agrokoombackend.service.Impls;

import kg.hackaton.agrokoombackend.dto.AuthenticationResponse;
import kg.hackaton.agrokoombackend.dto.user.RequestUserDTO;
import kg.hackaton.agrokoombackend.enums.Status;
import kg.hackaton.agrokoombackend.exception.NoAccessException;
import kg.hackaton.agrokoombackend.exception.NotFoundException;
import kg.hackaton.agrokoombackend.exception.TokenNotValidException;
import kg.hackaton.agrokoombackend.exception.UserAlreadyExistException;
import kg.hackaton.agrokoombackend.model.RecoveryToken;
import kg.hackaton.agrokoombackend.model.User;
import kg.hackaton.agrokoombackend.repository.RecoveryTokenRepository;
import kg.hackaton.agrokoombackend.repository.UserRepository;
import kg.hackaton.agrokoombackend.service.AuthenticationService;
import kg.hackaton.agrokoombackend.service.EmailService;
import kg.hackaton.agrokoombackend.service.JwtService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    JwtService jwtService;
    RecoveryTokenRepository recoveryTokenRepository;
    EmailService emailService;


    @Override
    public ResponseEntity<String> register(RequestUserDTO userDTO) throws UserAlreadyExistException {
        if (userRepository.existsByEmail(userDTO.getEmail()))
            throw new UserAlreadyExistException("User with such email already exists");

        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Passwords don't match");
        }
        var user = buildUser(userDTO);
        userRepository.save(user);

        RecoveryToken recoveryToken = constructToken(user);
        recoveryTokenRepository.save(recoveryToken);
        log.info("your code: {}", recoveryToken.getToken());

        sendToken(recoveryToken, user);

        return ResponseEntity.ok("Аккаунт успешно зарегистрирован");
    }

    @Override
    public AuthenticationResponse authenticate(String email, String password) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with such email wasn't found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new NoAccessException("Write password correctly");
        }

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    @Override
    public ResponseEntity<String> activateAccount(String token) {
        RecoveryToken recoveryToken = recoveryTokenRepository.findByToken(token)
                .orElseThrow(
                        () -> new TokenNotValidException("Неверный код")
                );

        User activateUser = userRepository.findById(recoveryToken.getUser().getId())
                .orElseThrow(
                        () -> new NotFoundException("Пользователь не найден")
                );

        activateUser.setStatus(Status.ACTIVE);
        userRepository.save(activateUser);

        recoveryTokenRepository.delete(recoveryToken);

        return ResponseEntity.ok("Аккаунт успешно активирован!");
    }

    @Override
    public ResponseEntity<String> resendCode(String email) {
        return null;
    }

    private User buildUser(RequestUserDTO userDTO) {
        return User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .status(Status.ACTIVE)
                .build();
    }

    private void sendToken(RecoveryToken recoveryToken, User user) {
        SimpleMailMessage activationEmail = new SimpleMailMessage();
        activationEmail.setFrom("bilem@gmail.com");
        activationEmail.setTo(user.getEmail());
        activationEmail.setSubject("Активация аккаунта");
        activationEmail.setText("Для активации аккаунта введите следующий код: " + recoveryToken.getToken() +
                "\nИстекает " + recoveryToken.getExpireAt().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")));

        emailService.sendEmail(activationEmail);
        log.info("Код успешно отправлен на почту " + user.getEmail());
    }

    private RecoveryToken constructToken(User user) {
        Random random = new Random();
        String token = String.valueOf(random.nextInt(100000, 999999));
        while (recoveryTokenRepository.existsByToken(token)) {
            token = String.valueOf(random.nextInt(100000, 999999));
        }

        return RecoveryToken.builder()
                .user(user)
                .token(token)
                .expireAt(LocalDateTime.now().plusDays(1))
                .createdAt(LocalDateTime.now())
                .build();
    }
}

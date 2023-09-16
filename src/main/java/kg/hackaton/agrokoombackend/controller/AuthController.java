package kg.hackaton.agrokoombackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kg.hackaton.agrokoombackend.dto.AuthenticationResponse;
import kg.hackaton.agrokoombackend.dto.user.RequestUserDTO;
import kg.hackaton.agrokoombackend.exception.UserAlreadyExistException;
import kg.hackaton.agrokoombackend.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(
        name = "Контроллер для авторизации/регистрации",
        description = "В этом контроллере есть возможности авторизации, регистрации"
)
public class AuthController {
    AuthenticationService authenticationService;

    @PostMapping("/register")
    @Operation(
            summary = "Регистрация нового аккаунта"
    )
    public ResponseEntity<String> register(@Valid @RequestBody RequestUserDTO userDTO) throws UserAlreadyExistException {
        return authenticationService.register(userDTO);
    }

    @PostMapping("/login")
    @Operation(
            summary = "Авторизация"
    )
    public AuthenticationResponse authenticate(@RequestParam String email,
                                               @RequestParam String password) {
        return authenticationService.authenticate(email, password);
    }

    @PostMapping("/resend-code")
    @Operation(
            summary = "Отправить код активации аккаунта повторно"
    )
    public ResponseEntity<String> resendCode(@RequestParam String email) {
        return authenticationService.resendCode(email);
    }

    @PostMapping("/activate/{token}")
    @Operation(
            summary = "Активация аккаунта с помощью кода, отправленного на почту"
    )
    public ResponseEntity<String> activate(@PathVariable String token) {
        return authenticationService.activateAccount(token);
    }
}

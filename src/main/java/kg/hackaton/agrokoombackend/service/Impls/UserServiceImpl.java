package kg.hackaton.agrokoombackend.service.Impls;

import kg.hackaton.agrokoombackend.dto.user.UpdateUserDTO;
import kg.hackaton.agrokoombackend.dto.user.GetUserDTO;
import kg.hackaton.agrokoombackend.model.User;
import kg.hackaton.agrokoombackend.repository.UserRepository;
import kg.hackaton.agrokoombackend.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static kg.hackaton.agrokoombackend.dto.user.GetUserDTO.toGetUserDTO;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    ModelMapper modelMapper;

    @Override
    public GetUserDTO getUsersInfo(User user) {
        return toGetUserDTO(user);
    }

    @Override
    public ResponseEntity<String> changeUsersInfo(UpdateUserDTO userDTO, User user) {
        modelMapper.map(userDTO, user);
        userRepository.save(user);

        return ResponseEntity.ok("Информация пользователя была обновлена");
    }

}

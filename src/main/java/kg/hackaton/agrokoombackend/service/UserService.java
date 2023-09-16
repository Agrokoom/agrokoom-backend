package kg.hackaton.agrokoombackend.service;

import kg.hackaton.agrokoombackend.dto.user.UpdateUserDTO;
import kg.hackaton.agrokoombackend.dto.user.GetUserDTO;
import kg.hackaton.agrokoombackend.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    GetUserDTO getUsersInfo(User user);

    ResponseEntity<String> changeUsersInfo(UpdateUserDTO userDTO, User user);
}

package kg.hackaton.agrokoombackend.service;

import kg.hackaton.agrokoombackend.dto.AuthenticationResponse;
import kg.hackaton.agrokoombackend.dto.user.RequestUserDTO;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<String> register(RequestUserDTO userDTO);

    AuthenticationResponse authenticate(String email, String password);

    ResponseEntity<String> activateAccount(String token);

    ResponseEntity<String> resendCode(String email);
}

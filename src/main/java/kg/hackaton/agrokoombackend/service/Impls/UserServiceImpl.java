package kg.hackaton.agrokoombackend.service.Impls;

import kg.hackaton.agrokoombackend.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserServiceImpl {
    UserRepository userRepository;

    //TODO
}

package kg.hackaton.agrokoombackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.hackaton.agrokoombackend.dto.payment.RequestPaymentDTO;
import kg.hackaton.agrokoombackend.dto.user.UpdateUserDTO;
import kg.hackaton.agrokoombackend.dto.user.GetUserDTO;
import kg.hackaton.agrokoombackend.model.User;
import kg.hackaton.agrokoombackend.service.ImageService;
import kg.hackaton.agrokoombackend.service.PaymentService;
import kg.hackaton.agrokoombackend.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(
        name = "Контроллер для работы с пользователями",
        description = "В этом контроллере есть возможности получения, изменения данных пользователя"
)
public class UserController {
    UserService userService;
    ImageService imageService;
    PaymentService paymentService;

    @GetMapping("/my-info")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Получение данных пользователя"
    )
    public GetUserDTO getUsersInfo(@AuthenticationPrincipal User user) {
        return userService.getUsersInfo(user);
    }

    @PostMapping("/change-my-info")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Изменение данных пользователя"
    )
    public ResponseEntity<String> changeUsersInfo(@RequestBody UpdateUserDTO updateUser,
                                                  @AuthenticationPrincipal User user) {
        return userService.changeUsersInfo(updateUser, user);
    }

    @PostMapping("/save-image")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Добавление или обновление фотографии пользователя"
    )
    public ResponseEntity<String> saveImageForUser(@RequestPart MultipartFile file,
                                                   @AuthenticationPrincipal User user) {
        return imageService.saveForUser(file, user);
    }

    @PostMapping("/save-registration-certificate")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Добавление или обновление свидетельства о регистрации"
    )
    public ResponseEntity<String> saveRegistrationCertificate(@RequestPart MultipartFile file,
                                                              @AuthenticationPrincipal User user) {
        return imageService.saveRegistrationCertificate(file, user);
    }

    @PostMapping("/add-payment-account")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Добавление расчетного счета"
    )
    public ResponseEntity<String> addPaymentAccount(@RequestBody RequestPaymentDTO paymentDTO,
                                                    @AuthenticationPrincipal User user) {
        return paymentService.addPaymentAccount(paymentDTO, user);
    }
}

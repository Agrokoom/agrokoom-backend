package kg.hackaton.agrokoombackend.dto.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import kg.hackaton.agrokoombackend.model.Payment;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateUserDTO {
    @NotEmpty(message = "Имя не может быть пустым")
    String name;

    String secondName;

    @NotEmpty(message = "Фамилия не может быть пустой")
    String surname;

    String closePersonName;

    String closePersonSecondName;

    String closePersonSurname;

    String closePersonPhoneNumber;

    @NotNull(message = "Дата рождения не может быть пустой")
    LocalDate dateOfBirth;

    @NotEmpty(message = "Номер телефона не может быть пустым")
    String phoneNumber;

    String businessRegistrationForm;

    String INN;

    String OKEDCode;

    String registrationCertificateUrl;
}

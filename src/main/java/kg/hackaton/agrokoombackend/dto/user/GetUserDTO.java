package kg.hackaton.agrokoombackend.dto.user;

import kg.hackaton.agrokoombackend.model.Payment;
import kg.hackaton.agrokoombackend.model.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetUserDTO {
    String name;

    String secondName;

    String surname;

    String closePersonName;

    String closePersonSecondName;

    String closePersonSurname;

    String closePersonPhoneNumber;

    LocalDate dateOfBirth;

    String email;

    String phoneNumber;

    String imageUrl;

    String businessRegistrationForm;

    String INN;

    String OKEDCode;

    String registrationCertificateUrl;

    List<Payment> paymentAccounts;

    String role;

    String status;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    public static GetUserDTO toGetUserDTO(User user){
        return GetUserDTO.builder()
                .name(user.getName())
                .secondName(user.getSecondName())
                .surname(user.getSurname())
                .closePersonName(user.getClosePersonName())
                .closePersonSurname(user.getClosePersonSurname())
                .closePersonSecondName(user.getClosePersonSecondName())
                .closePersonPhoneNumber(user.getClosePersonPhoneNumber())
                .dateOfBirth(user.getDateOfBirth())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .imageUrl(user.getImageUrl())
                .role(user.getRole().name())
                .status(user.getStatus().name())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .businessRegistrationForm(user.getBusinessRegistrationForm())
                .INN(user.getINN())
                .OKEDCode(user.getOKEDCode())
                .registrationCertificateUrl(user.getRegistrationCertificateUrl())
                .paymentAccounts(user.getPaymentAccounts())
                .build();
    }

    public static List<GetUserDTO> toGetUserDTOs(List<User> users){
        return users.stream().map(GetUserDTO::toGetUserDTO).toList();
    }
}

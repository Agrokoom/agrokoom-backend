package kg.hackaton.agrokoombackend.dto.payment;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestPaymentDTO {
    @NotEmpty(message = "Код банка не может быть пустым")
    String bankCode;

    @NotEmpty(message = "Номер расчетного счета не может быть пустым")
    String settlementAccountNumber;

    @NotEmpty(message = "Название счета не может быть пустым")
    String accountName;
}

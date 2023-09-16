package kg.hackaton.agrokoombackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "payment")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Payment extends BaseEntity{
    String bankCode;

    String settlementAccountNumber;

    String accountName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}

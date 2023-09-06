package kg.hackaton.agrokoombackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "recovery_token")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecoveryToken extends BaseEntity{
    String token;

    @Column(name = "expire_at")
    LocalDateTime expireAt;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @OneToOne
    User user;
}

package kg.hackaton.agrokoombackend.model;

import jakarta.persistence.*;
import kg.hackaton.agrokoombackend.enums.Role;
import kg.hackaton.agrokoombackend.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity implements UserDetails {
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

    @OneToMany(fetch = FetchType.EAGER, targetEntity = Payment.class, cascade = CascadeType.ALL)
    List<Payment> paymentAccounts;

    String password;

    @Enumerated(EnumType.STRING)
    Role role;

    @Enumerated(EnumType.STRING)
    Status status;

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;


    @OneToMany(fetch = FetchType.EAGER, targetEntity = Product.class, cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status == Status.ACTIVE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

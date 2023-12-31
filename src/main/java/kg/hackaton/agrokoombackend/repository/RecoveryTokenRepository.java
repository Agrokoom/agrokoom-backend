package kg.hackaton.agrokoombackend.repository;

import kg.hackaton.agrokoombackend.model.RecoveryToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecoveryTokenRepository extends JpaRepository<RecoveryToken, Long> {
    Optional<RecoveryToken> findByToken(String token);

    boolean existsByToken(String token);
}

package kg.hackaton.agrokoombackend.repository;

import kg.hackaton.agrokoombackend.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}

package kg.hackaton.agrokoombackend.service;

import kg.hackaton.agrokoombackend.dto.payment.RequestPaymentDTO;
import kg.hackaton.agrokoombackend.model.User;
import org.springframework.http.ResponseEntity;

public interface PaymentService {
    ResponseEntity<String> addPaymentAccount(RequestPaymentDTO paymentDTO, User user);
}

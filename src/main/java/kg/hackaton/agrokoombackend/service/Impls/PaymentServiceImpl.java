package kg.hackaton.agrokoombackend.service.Impls;

import kg.hackaton.agrokoombackend.dto.payment.RequestPaymentDTO;
import kg.hackaton.agrokoombackend.model.Payment;
import kg.hackaton.agrokoombackend.model.User;
import kg.hackaton.agrokoombackend.repository.PaymentRepository;
import kg.hackaton.agrokoombackend.service.PaymentService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    PaymentRepository paymentRepository;
    ModelMapper modelMapper;
    @Override
    public ResponseEntity<String> addPaymentAccount(RequestPaymentDTO paymentDTO, User user) {
        Payment payment = modelMapper.map(paymentDTO, Payment.class);
        payment.setUser(user);
        paymentRepository.save(payment);

        return ResponseEntity.ok("Расчетный счет сохранен");
    }
}

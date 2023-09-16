package kg.hackaton.agrokoombackend.service.Impls;

import kg.hackaton.agrokoombackend.service.EmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    JavaMailSender mailSender;

    @Override
    @Async
    public void sendEmail(SimpleMailMessage email) {
        mailSender.send(email);
    }

}

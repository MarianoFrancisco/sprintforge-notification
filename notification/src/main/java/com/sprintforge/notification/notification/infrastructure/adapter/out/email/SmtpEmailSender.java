package com.sprintforge.notification.notification.infrastructure.adapter.out.email;

import com.sprintforge.common.application.exception.BusinessException;
import com.sprintforge.notification.notification.application.exception.MailAuthenticatorException;
import com.sprintforge.notification.notification.application.exception.MailSenderException;
import com.sprintforge.notification.notification.application.exception.MessageException;
import com.sprintforge.notification.notification.application.port.out.EmailSender;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
@RequiredArgsConstructor
public class SmtpEmailSender implements EmailSender {

    private final JavaMailSender mailSender;

    @Override
    public void send(String toAddress, String subject, String htmlBody) {
        try {
            MimeMessage msg = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true, UTF_8.name());
            helper.setTo(toAddress);
            helper.setSubject(subject);
            helper.setText(htmlBody, true);
            mailSender.send(msg);
        } catch (MailAuthenticationException e) {
            throw new MailAuthenticatorException();
        } catch (MailSendException e) {
            throw new MailSenderException();
        } catch (MessagingException e) {
            throw new MessageException();
        } catch (Exception e) {
            throw new BusinessException("Error enviando el correo electrónico a " + toAddress);
        }
    }
}

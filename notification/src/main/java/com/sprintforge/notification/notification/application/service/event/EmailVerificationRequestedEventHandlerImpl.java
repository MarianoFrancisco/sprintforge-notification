package com.sprintforge.notification.notification.application.service.event;

import com.sprintforge.notification.common.infrastructure.verification.VerificationProperties;
import com.sprintforge.notification.notification.application.port.in.event.identity.EmailVerificationRequestedEventHandler;
import com.sprintforge.notification.notification.application.port.in.event.identity.EmailVerificationRequestedIntegrationEvent;
import com.sprintforge.notification.notification.application.port.out.EmailSender;
import com.sprintforge.notification.notification.application.port.out.renderer.EmailRenderer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class EmailVerificationRequestedEventHandlerImpl
        implements EmailVerificationRequestedEventHandler {

    private static final String SUBJECT = "Verificación de correo electrónico";
    private static final String TEMPLATE = "email-verification";
    private static final String VERIFICATION_LINK_PLACEHOLDER = "verificationLink";

    private final EmailSender emailSender;
    private final EmailRenderer emailRenderer;
    private final VerificationProperties verificationProperties;

    @Override
    public void handle(EmailVerificationRequestedIntegrationEvent event) {

        String verificationLink = verificationProperties.buildLink(event.token());
        String htmlBody = buildHtmlBody(verificationLink);

        emailSender.send(event.email(), SUBJECT, htmlBody);
    }

    private String buildHtmlBody(String verificationLink) {
        return emailRenderer.render(
                TEMPLATE,
                Map.of(VERIFICATION_LINK_PLACEHOLDER, verificationLink)
        );
    }
}

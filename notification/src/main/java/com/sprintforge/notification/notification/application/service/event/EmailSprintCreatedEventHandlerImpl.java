package com.sprintforge.notification.notification.application.service.event;

import com.sprintforge.notification.notification.application.port.in.event.scrum.EmailSprintCreatedEventHandler;
import com.sprintforge.notification.notification.application.port.in.event.scrum.EmailSprintCreatedIntegrationEvent;
import com.sprintforge.notification.notification.application.port.out.EmailSender;
import com.sprintforge.notification.notification.application.port.out.renderer.EmailRenderer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class EmailSprintCreatedEventHandlerImpl
        implements EmailSprintCreatedEventHandler {

    private static final String SUBJECT = "Ha sido creado un nuevo sprint";
    private static final String TEMPLATE = "sprint-created";
    private static final String EMPLOYEE_NAME = "employeeName";
    private static final String SPRINT_NAME = "sprintName";

    private final EmailSender emailSender;
    private final EmailRenderer emailRenderer;

    @Override
    public void handle(EmailSprintCreatedIntegrationEvent event) {
        String htmlBody = buildHtmlBody(
                event.fullName(),
                event.sprintName()
        );
        emailSender.send(event.email(), SUBJECT, htmlBody);
    }

    private String buildHtmlBody(String employeeName, String sprintName) {
        return emailRenderer.render(
                TEMPLATE,
                Map.of(
                        EMPLOYEE_NAME, employeeName,
                        SPRINT_NAME, sprintName
                )
        );
    }
}

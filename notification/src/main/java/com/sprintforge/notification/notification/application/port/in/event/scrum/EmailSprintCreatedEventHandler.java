package com.sprintforge.notification.notification.application.port.in.event.scrum;

public interface EmailSprintCreatedEventHandler {
    void handle(EmailSprintCreatedIntegrationEvent event);
}

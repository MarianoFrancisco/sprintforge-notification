package com.sprintforge.notification.notification.application.port.in.event.identity;

public interface EmailVerificationRequestedEventHandler {
    void handle(EmailVerificationRequestedIntegrationEvent event);
}

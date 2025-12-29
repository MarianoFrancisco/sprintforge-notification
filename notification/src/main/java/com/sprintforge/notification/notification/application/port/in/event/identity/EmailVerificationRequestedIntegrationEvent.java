package com.sprintforge.notification.notification.application.port.in.event.identity;

public record EmailVerificationRequestedIntegrationEvent(
        String email,
        String token
) {
}

package com.sprintforge.notification.notification.application.port.in.event.scrum;

public record EmailSprintCreatedIntegrationEvent(
        String email,
        String fullName,
        String sprintName
) {
}

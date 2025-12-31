package com.sprintforge.notification.notification.infrastructure.adapter.in.messaging.kafka.identity.scrum.event;

public record EmailSprintCreatedKafkaMessage(
        String email,
        String fullName,
        String sprintName
) {
}

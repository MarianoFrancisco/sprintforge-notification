package com.sprintforge.notification.notification.infrastructure.adapter.in.messaging.kafka.identity.event;

public record EmailVerificationRequestedKafkaMessage(
        String email,
        String token
) {
}

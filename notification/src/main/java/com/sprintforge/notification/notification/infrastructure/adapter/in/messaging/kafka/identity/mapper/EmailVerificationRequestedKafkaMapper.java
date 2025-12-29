package com.sprintforge.notification.notification.infrastructure.adapter.in.messaging.kafka.identity.mapper;

import com.sprintforge.notification.notification.application.port.in.event.identity.EmailVerificationRequestedIntegrationEvent;
import com.sprintforge.notification.notification.infrastructure.adapter.in.messaging.kafka.identity.event.EmailVerificationRequestedKafkaMessage;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EmailVerificationRequestedKafkaMapper {
    public EmailVerificationRequestedIntegrationEvent toEvent(
            EmailVerificationRequestedKafkaMessage message
    ) {
        return new EmailVerificationRequestedIntegrationEvent(
                message.email(),
                message.token()
        );
    }
}

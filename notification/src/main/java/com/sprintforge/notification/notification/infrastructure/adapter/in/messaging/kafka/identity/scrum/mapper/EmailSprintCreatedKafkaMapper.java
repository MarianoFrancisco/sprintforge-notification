package com.sprintforge.notification.notification.infrastructure.adapter.in.messaging.kafka.identity.scrum.mapper;

import com.sprintforge.notification.notification.application.port.in.event.scrum.EmailSprintCreatedIntegrationEvent;
import com.sprintforge.notification.notification.infrastructure.adapter.in.messaging.kafka.identity.scrum.event.EmailSprintCreatedKafkaMessage;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EmailSprintCreatedKafkaMapper {
    public EmailSprintCreatedIntegrationEvent toEvent(
            EmailSprintCreatedKafkaMessage message
    ) {
        return new EmailSprintCreatedIntegrationEvent(
                message.email(),
                message.fullName(),
                message.sprintName()
        );
    }
}

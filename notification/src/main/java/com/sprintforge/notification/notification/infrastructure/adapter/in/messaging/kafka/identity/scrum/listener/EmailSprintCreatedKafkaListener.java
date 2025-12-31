package com.sprintforge.notification.notification.infrastructure.adapter.in.messaging.kafka.identity.scrum.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprintforge.notification.notification.application.port.in.event.scrum.EmailSprintCreatedEventHandler;
import com.sprintforge.notification.notification.application.port.in.event.scrum.EmailSprintCreatedIntegrationEvent;
import com.sprintforge.notification.notification.infrastructure.adapter.in.messaging.kafka.identity.scrum.event.EmailSprintCreatedKafkaMessage;
import com.sprintforge.notification.notification.infrastructure.adapter.in.messaging.kafka.identity.scrum.mapper.EmailSprintCreatedKafkaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailSprintCreatedKafkaListener {

    private final EmailSprintCreatedEventHandler handler;
    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = "#{@kafkaTopicsProperties.emailSprintCreated}",
            groupId = "#{@kafkaGroupsProperties.defaultGroup}"
    )
    public void listen(String payload) {
        try {
            log.debug("Received EmailSprintCreated raw payload: {}", payload);

            EmailSprintCreatedKafkaMessage message =
                    objectMapper.readValue(payload, EmailSprintCreatedKafkaMessage.class);

            log.debug("Parsed EmailSprintCreated event: {}", message);

            EmailSprintCreatedIntegrationEvent event = EmailSprintCreatedKafkaMapper.toEvent(message);
            handler.handle(event);

        } catch (JsonProcessingException ex) {
            log.warn("Skipping invalid EmailSprintCreated payload: {}", payload, ex);
        }
    }
}

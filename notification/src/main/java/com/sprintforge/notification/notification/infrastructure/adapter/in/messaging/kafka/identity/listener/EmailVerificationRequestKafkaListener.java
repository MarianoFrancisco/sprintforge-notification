package com.sprintforge.notification.notification.infrastructure.adapter.in.messaging.kafka.identity.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprintforge.notification.notification.application.port.in.event.identity.EmailVerificationRequestedEventHandler;
import com.sprintforge.notification.notification.application.port.in.event.identity.EmailVerificationRequestedIntegrationEvent;
import com.sprintforge.notification.notification.infrastructure.adapter.in.messaging.kafka.identity.event.EmailVerificationRequestedKafkaMessage;
import com.sprintforge.notification.notification.infrastructure.adapter.in.messaging.kafka.identity.mapper.EmailVerificationRequestedKafkaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailVerificationRequestKafkaListener {

    private final EmailVerificationRequestedEventHandler handler;
    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = "#{@kafkaTopicsProperties.emailVerificationRequested}",
            groupId = "#{@kafkaGroupsProperties.defaultGroup}"
    )
    public void listen(String payload) {
        try {
            log.debug("Received EmailVerificationRequested raw payload: {}", payload);

            EmailVerificationRequestedKafkaMessage message =
                    objectMapper.readValue(payload, EmailVerificationRequestedKafkaMessage.class);

            log.debug("Parsed EmailVerificationRequested event: {}", message);

            EmailVerificationRequestedIntegrationEvent event = EmailVerificationRequestedKafkaMapper.toEvent(message);
            handler.handle(event);

        } catch (JsonProcessingException ex) {
            log.warn("Skipping invalid EmailVerificationRequested payload: {}", payload, ex);
        }
    }
}

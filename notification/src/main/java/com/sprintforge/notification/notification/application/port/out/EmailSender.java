package com.sprintforge.notification.notification.application.port.out;

public interface EmailSender {
    void send(String toAddress, String subject, String htmlBody);
}

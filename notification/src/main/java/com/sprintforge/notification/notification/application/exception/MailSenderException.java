package com.sprintforge.notification.notification.application.exception;

import com.sprintforge.common.application.exception.BusinessException;

public class MailSenderException extends BusinessException {
    public MailSenderException() {
        super("Error al enviar el correo");
    }
}

package com.sprintforge.notification.notification.application.exception;

import com.sprintforge.common.application.exception.BusinessException;

public class MessageException extends BusinessException {
    public MessageException() {
        super("Error al construir el mensaje");
    }
}

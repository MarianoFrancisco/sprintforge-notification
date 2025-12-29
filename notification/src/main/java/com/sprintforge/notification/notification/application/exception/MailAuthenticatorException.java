package com.sprintforge.notification.notification.application.exception;

import com.sprintforge.common.application.exception.BusinessException;

public class MailAuthenticatorException extends BusinessException {
    public MailAuthenticatorException() {
        super("Error al autenticar el correo saliente");
    }
}

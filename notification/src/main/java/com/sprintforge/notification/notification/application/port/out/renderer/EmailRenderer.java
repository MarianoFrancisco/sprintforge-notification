package com.sprintforge.notification.notification.application.port.out.renderer;

import java.util.Map;

public interface EmailRenderer {
    String render(String templateName, Map<String, Object> model);
}

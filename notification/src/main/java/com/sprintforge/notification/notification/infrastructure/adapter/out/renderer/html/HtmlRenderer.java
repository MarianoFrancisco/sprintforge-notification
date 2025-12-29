package com.sprintforge.notification.notification.infrastructure.adapter.out.renderer.html;

import com.sprintforge.notification.notification.application.port.out.renderer.EmailRenderer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.swing.*;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class HtmlRenderer implements EmailRenderer {
    private final TemplateEngine templateEngine;

    @Override
    public String render(String templateName, Map<String, Object> model) {

        Context context = new Context();
        context.setVariables(model);

        return templateEngine.process(
                templateName,
                context
        );
    }
}

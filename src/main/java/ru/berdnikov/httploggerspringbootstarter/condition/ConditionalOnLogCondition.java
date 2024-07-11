package ru.berdnikov.httploggerspringbootstarter.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import ru.berdnikov.httploggerspringbootstarter.logger.LogType;
import ru.berdnikov.httploggerspringbootstarter.utils.LogPropertiesUtils;

import java.util.Map;

/**
 * @author danilaberdnikov on ConditionalOnLogCondition.
 * @project http-logger-spring-boot-starter
 */
public class ConditionalOnLogCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        if (!LogPropertiesUtils.isLoggerEnabled(context)) {
            return false;
        }

        Map<String, Object> attributes = metadata.getAnnotationAttributes(ConditionalOnLog.class.getName());
        if (attributes == null) {
            return false;
        }

        LogType logType = (LogType) attributes.get("value");
        return switch (logType) {
            case ASPECT -> LogPropertiesUtils.isAspectLoggingEnabled(context);
            case FILTER -> LogPropertiesUtils.isFilterLoggingEnabled(context);
            case INTERCEPTOR -> LogPropertiesUtils.isInterceptorLoggingEnabled(context);
        };
    }
}

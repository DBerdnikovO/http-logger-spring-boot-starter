package ru.berdnikov.httploggerspringbootstarter.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import ru.berdnikov.httploggerspringbootstarter.logger.LoggerType;
import ru.berdnikov.httploggerspringbootstarter.utils.LoggerPropertiesUtils;

import java.util.Map;

/**
 * @author danilaberdnikov on OnLoggerCondition.
 * @project http-logger-spring-boot-starter
 */
public class OnLoggerCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        if (!LoggerPropertiesUtils.isLoggerEnabled(context)) {
            return false;
        }

        Map<String, Object> attributes = metadata.getAnnotationAttributes(ConditionalOnLogger.class.getName());
        if (attributes == null) {
            return false;
        }

        LoggerType loggerType = (LoggerType) attributes.get("value");
        return switch (loggerType) {
            case ASPECT -> LoggerPropertiesUtils.isAspectLoggingEnabled(context);
            case FILTER -> LoggerPropertiesUtils.isFilterLoggingEnabled(context);
            case INTERCEPTOR -> LoggerPropertiesUtils.isInterceptorLoggingEnabled(context);
        };
    }
}

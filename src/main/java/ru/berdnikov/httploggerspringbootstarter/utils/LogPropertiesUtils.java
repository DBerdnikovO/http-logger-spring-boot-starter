package ru.berdnikov.httploggerspringbootstarter.utils;

import org.springframework.context.annotation.ConditionContext;
import ru.berdnikov.httploggerspringbootstarter.logger.LogType;

import java.util.Optional;

/**
 * @author danilaberdnikov on LogPropertiesUtils.
 * @project http-logger-spring-boot-starter
 */
//+
public class LogPropertiesUtils {
    public static boolean isLoggerEnabled(ConditionContext context) {
        return Optional.ofNullable(context.getEnvironment().getProperty(LogApplicationVariables.ENABLED)).isPresent();
    }

    public static boolean isAspectLoggingEnabled(ConditionContext context) {
        String type = context.getEnvironment().getProperty(LogApplicationVariables.TYPE);
        return LogType.ASPECT.name().equalsIgnoreCase(type);
    }

    public static boolean isFilterLoggingEnabled(ConditionContext context) {
        String type = context.getEnvironment().getProperty(LogApplicationVariables.TYPE);
        return LogType.FILTER.name().equalsIgnoreCase(type);
    }

    public static boolean isInterceptorLoggingEnabled(ConditionContext context) {
        String type = context.getEnvironment().getProperty(LogApplicationVariables.TYPE);
        return LogType.INTERCEPTOR.name().equalsIgnoreCase(type);
    }
}

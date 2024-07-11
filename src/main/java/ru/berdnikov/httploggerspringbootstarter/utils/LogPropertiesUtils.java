package ru.berdnikov.httploggerspringbootstarter.utils;

import org.springframework.context.annotation.ConditionContext;

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
        return "aspect".equals(type);
    }

    public static boolean isFilterLoggingEnabled(ConditionContext context) {
        String type = context.getEnvironment().getProperty(LogApplicationVariables.TYPE);
        return "filter".equals(type);
    }

    public static boolean isInterceptorLoggingEnabled(ConditionContext context) {
        String type = context.getEnvironment().getProperty(LogApplicationVariables.TYPE);
        return "interceptor".equals(type);
    }
}

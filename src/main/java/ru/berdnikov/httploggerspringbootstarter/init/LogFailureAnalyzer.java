package ru.berdnikov.httploggerspringbootstarter.init;

import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;
import org.springframework.stereotype.Component;
import ru.berdnikov.httploggerspringbootstarter.exception.LogStartupException;

/**
 * @author danilaberdnikov on LogFailureAnalyzer.
 * @project http-logger-spring-boot-starter
 */
@Component
public class LogFailureAnalyzer extends AbstractFailureAnalyzer<LogStartupException> {
    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, LogStartupException cause) {
        return new FailureAnalysis(cause.getMessage(), "Укажите валидные значения для свойства", cause);
    }
}

package ru.berdnikov.httploggerspringbootstarter.logger;

import jakarta.servlet.ServletRequest;
import org.springframework.util.StopWatch;

/**
 * @author danilaberdnikov on HttpExecutionTiming.
 * @project http-logger-spring-boot-starter
 */
public interface HttpExecutionTiming {
    void measureExecutionTime(StopWatch stopWatch);

    void measureExecutionTime(ServletRequest request);

    void measureExecutionTime(long timeMils);
}

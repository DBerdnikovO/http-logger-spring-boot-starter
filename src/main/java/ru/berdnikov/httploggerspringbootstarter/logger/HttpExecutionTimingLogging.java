package ru.berdnikov.httploggerspringbootstarter.logger;

import jakarta.servlet.ServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import ru.berdnikov.httploggerspringbootstarter.utils.HttpHeaderAttribute;

/**
 * @author danilaberdnikov on HttpExecutionTimingLogging.
 * @project http-logger-spring-boot-starter
 */
@Slf4j
@Component
public class HttpExecutionTimingLogging implements HttpExecutionTiming {
    @Override
    public void measureExecutionTime(StopWatch stopWatch) {
        stopWatch.stop();
        long executionTime = stopWatch.getTotalTimeMillis();
        log.info("Execute with time in ms: {} ", executionTime);
    }

    @Override
    public void measureExecutionTime(ServletRequest request) {
        long startTime = (Long) request.getAttribute(HttpHeaderAttribute.START_TIME_ATTRIBUTE);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        log.info("Execute with time in ms: {} ", executionTime);
    }

    @Override
    public void measureExecutionTime(long timeMils) {
        log.info("Execute with time in ms: {} ", timeMils);
    }
}

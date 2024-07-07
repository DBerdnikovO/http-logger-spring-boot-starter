package ru.berdnikov.httploggerspringbootstarter.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import ru.berdnikov.httploggerspringbootstarter.logger.HttpLogger;

/**
 * @author danilaberdnikov on LogFilter.
 * @project http-logger-spring-boot-starter
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class LogFilter implements Filter {
    private final HttpLogger httpLogger;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        log.info("------Filter logging enable------");

        httpLogger.logRequestDetails(request);
        httpLogger.logResponseDetails(response);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            chain.doFilter(request, response);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            httpLogger.measureExecutionTime(stopWatch);
        }
    }
}

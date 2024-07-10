package ru.berdnikov.httploggerspringbootstarter.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import ru.berdnikov.httploggerspringbootstarter.exception.HttpFilterException;
import ru.berdnikov.httploggerspringbootstarter.logger.HttpLogger;

/**
 * @author danilaberdnikov on HttpFilter.
 * @project http-logger-spring-boot-starter
 */
@Slf4j
@RequiredArgsConstructor
public class HttpFilter implements Filter {
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
            throw new HttpFilterException(e);
        } finally {
            httpLogger.measureExecutionTime(stopWatch);
        }
    }
}

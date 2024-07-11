package ru.berdnikov.httploggerspringbootstarter.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import ru.berdnikov.httploggerspringbootstarter.exception.HttpFilterException;
import ru.berdnikov.httploggerspringbootstarter.logger.HttpExecutionTiming;
import ru.berdnikov.httploggerspringbootstarter.logger.HttpLogDetails;

/**
 * @author danilaberdnikov on HttpLogFilterImpl.
 * @project http-logger-spring-boot-starter
 */
//+
@Slf4j
@RequiredArgsConstructor
public class HttpLogFilterImpl implements Filter {
    private final HttpLogDetails httpRequestAndResponseLogging;
    private final HttpExecutionTiming httpExecutionTimingLogging;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        log.info("------Filter logging enable------");
        log.info("Filter logs before (chain.doFilter()) starts");

        httpRequestAndResponseLogging.logRequestAndResponseDetails(request, response);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            chain.doFilter(request, response);
        } catch (Throwable e) {
            throw new HttpFilterException(e);
        } finally {
            log.info("Filter logs after (chain.doFilter()) starts");

            httpRequestAndResponseLogging.logRequestAndResponseDetails(request, response);

            httpExecutionTimingLogging.measureExecutionTime(stopWatch);
        }
    }
}

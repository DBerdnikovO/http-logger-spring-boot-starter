package ru.berdnikov.httploggerspringbootstarter.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import ru.berdnikov.httploggerspringbootstarter.logger.HttpLogger;


/**
 * @author danilaberdnikov on RequestInterceptor.
 * @project http-logger-spring-boot-starter
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RequestInterceptor implements HandlerInterceptor {
    private static final String START_TIME_ATTRIBUTE = "startTime";
    private final HttpLogger httpLogger;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        long startTime = System.currentTimeMillis();
        request.setAttribute(START_TIME_ATTRIBUTE, startTime);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.info("------Interceptor logging enable------");

        httpLogger.logRequestDetails(request);
        httpLogger.logResponseDetails(response);
        httpLogger.measureExecutionTime(request);
    }
}

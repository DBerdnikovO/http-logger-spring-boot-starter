package ru.berdnikov.httploggerspringbootstarter.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import ru.berdnikov.httploggerspringbootstarter.logger.HttpLogger;
import ru.berdnikov.httploggerspringbootstarter.utils.EnvironmentValues;


/**
 * @author danilaberdnikov on HttpHandlerInterceptor.
 * @project http-logger-spring-boot-starter
 */
@Slf4j
@RequiredArgsConstructor
public class HttpHandlerInterceptor implements HandlerInterceptor {
    private final HttpLogger httpLogger;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("------Interceptor logging enable------");

        long startTime = System.currentTimeMillis();
        request.setAttribute(EnvironmentValues.START_TIME_ATTRIBUTE, startTime);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        httpLogger.logRequestDetails(request);
        httpLogger.logResponseDetails(response);
        httpLogger.measureExecutionTime(request);
    }
}

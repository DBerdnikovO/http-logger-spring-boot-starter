package ru.berdnikov.httploggerspringbootstarter.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import ru.berdnikov.httploggerspringbootstarter.logger.HttpLogDetails;
import ru.berdnikov.httploggerspringbootstarter.logger.HttpExecutionTiming;
import ru.berdnikov.httploggerspringbootstarter.utils.HttpHeaderAttribute;


/**
 * @author danilaberdnikov on HttpHandlerInterceptor.
 * @project http-logger-spring-boot-starter
 */
//+
@Slf4j
@RequiredArgsConstructor
public class HttpHandlerInterceptor implements HandlerInterceptor {
    private final HttpLogDetails httpRequestAndResponseLogging;
    private final HttpExecutionTiming httpExecutionTimingLogging;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("------Interceptor logging enable------");
        log.info("Interceptor logs before request,response");

        httpRequestAndResponseLogging.logRequestAndResponseDetails(request,response);

        long startTime = System.currentTimeMillis();
        request.setAttribute(HttpHeaderAttribute.START_TIME_ATTRIBUTE, startTime);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.info("Interceptor logs after request,response");

        httpRequestAndResponseLogging.logRequestAndResponseDetails(request,response);

        httpExecutionTimingLogging.measureExecutionTime(request);
    }
}

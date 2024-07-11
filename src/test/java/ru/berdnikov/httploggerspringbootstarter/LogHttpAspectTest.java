package ru.berdnikov.httploggerspringbootstarter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.berdnikov.httploggerspringbootstarter.aspect.LogHttpAspect;
import ru.berdnikov.httploggerspringbootstarter.logger.HttpExecutionTiming;
import ru.berdnikov.httploggerspringbootstarter.logger.HttpLogDetails;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

/**
 * @author danilaberdnikov on LogHttpAspectTest.
 * @project http-logger-spring-boot-starter
 */
public class LogHttpAspectTest {
    @Mock
    private HttpLogDetails mockHttpLogDetails;

    @Mock
    private HttpExecutionTiming mockHttpExecutionTiming;

    @Mock
    private ProceedingJoinPoint mockJoinPoint;

    @Mock
    private MethodSignature mockMethodSignature;

    private LogHttpAspect logHttpAspect;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        logHttpAspect = new LogHttpAspect(mockHttpLogDetails, mockHttpExecutionTiming);
    }

    @Test
    public void testLogRequestAndResponseAround() throws Throwable {
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        MockHttpServletResponse mockResponse = new MockHttpServletResponse();
        ServletRequestAttributes attributes = new ServletRequestAttributes(mockRequest, mockResponse);
        RequestContextHolder.setRequestAttributes(attributes);

        when(mockJoinPoint.getSignature()).thenReturn(mockMethodSignature);
        when(mockMethodSignature.getName()).thenReturn("testMethod");

        Object expectedResult = new Object();
        when(mockJoinPoint.proceed()).thenReturn(expectedResult);

        Object actualResult = logHttpAspect.logRequestAndResponseAround(mockJoinPoint);

        verify(mockJoinPoint, times(1)).proceed();

        verify(mockHttpLogDetails, times(2)).logRequestAndResponseDetails(any(HttpServletRequest.class), any(HttpServletResponse.class));

        assertSame(expectedResult, actualResult);
    }
}

package ru.berdnikov.httploggerspringbootstarter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import ru.berdnikov.httploggerspringbootstarter.interceptor.HttpHandlerInterceptor;
import ru.berdnikov.httploggerspringbootstarter.logger.HttpExecutionTiming;
import ru.berdnikov.httploggerspringbootstarter.logger.HttpLogDetails;
import ru.berdnikov.httploggerspringbootstarter.utils.HttpHeaderAttribute;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

/**
 * @author danilaberdnikov on HttpHandlerInterceptorTest.
 * @project http-logger-spring-boot-starter
 */
public class HttpHandlerInterceptorTest {
    @Mock
    private HttpLogDetails mockHttpLogDetails;

    @Mock
    private HttpExecutionTiming mockHttpExecutionTiming;

    private HttpHandlerInterceptor interceptor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        interceptor = new HttpHandlerInterceptor(mockHttpLogDetails, mockHttpExecutionTiming);
    }

    @Test
    public void testPreHandle() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        boolean result = interceptor.preHandle(request, response, new Object());

        verify(mockHttpLogDetails).logRequestAndResponseDetails(request, response);
        assertTrue(result);
        assertTrue(request.getAttribute(HttpHeaderAttribute.START_TIME_HEADER_ATTRIBUTE) instanceof Long);
    }

    @Test
    public void testAfterCompletion() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        interceptor.afterCompletion(request, response, new Object(), null);

        verify(mockHttpLogDetails).logRequestAndResponseDetails(request, response);
        verify(mockHttpExecutionTiming).measureExecutionTime(request);
    }
}

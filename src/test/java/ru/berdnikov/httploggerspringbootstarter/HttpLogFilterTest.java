package ru.berdnikov.httploggerspringbootstarter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.util.StopWatch;
import ru.berdnikov.httploggerspringbootstarter.filter.HttpLogFilter;
import ru.berdnikov.httploggerspringbootstarter.logger.HttpExecutionTiming;
import ru.berdnikov.httploggerspringbootstarter.logger.HttpLogDetails;

import java.io.IOException;

import static org.mockito.Mockito.*;

/**
 * @author danilaberdnikov on HttpLogFilterTest.
 * @project http-logger-spring-boot-starter
 */
public class HttpLogFilterTest {
    @Mock
    private HttpLogDetails mockHttpLogDetails;

    @Mock
    private HttpExecutionTiming mockHttpExecutionTiming;

    private HttpLogFilter httpLogFilter;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        httpLogFilter = new HttpLogFilter(mockHttpLogDetails, mockHttpExecutionTiming);
    }

    @Test
    public void testDoFilter() throws IOException, ServletException {
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        MockHttpServletResponse mockResponse = new MockHttpServletResponse();

        FilterChain mockFilterChain = mock(FilterChain.class);

        httpLogFilter.doFilter(mockRequest, mockResponse, mockFilterChain);

        verify(mockHttpLogDetails, times(2)).logRequestAndResponseDetails(mockRequest, mockResponse);
        verify(mockHttpExecutionTiming).measureExecutionTime((StopWatch) any());
        verify(mockFilterChain, times(1)).doFilter(mockRequest, mockResponse);
    }
}

//package ru.berdnikov.httploggerspringbootstarter;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import ru.berdnikov.httploggerspringbootstarter.aspect.LogHttpAspect;
//import ru.berdnikov.httploggerspringbootstarter.logger.HttpRequestAndResponseLogDetails;
//
//import static org.mockito.Mockito.*;
//
///**
// * @author danilaberdnikov on LoggerHttpAspectTest.
// * @project http-logger-spring-boot-starter
// */
//public class LoggerHttpAspectTest {
//    @Mock
//    private HttpRequestAndResponseLogDetails mockHttpLogger;
//
//    @Mock
//    private ProceedingJoinPoint mockJoinPoint;
//
//    @InjectMocks
//    private LogHttpAspect loggerHttpAspect;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testLogBefore() {
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//
//        verify(mockHttpLogger).logRequestDetails(request);
//        verify(mockHttpLogger).logResponseDetails(response);
//    }
//
//    @Test
//    public void testLogAround() throws Throwable {
//        when(mockJoinPoint.proceed()).thenReturn(null);
//
//        loggerHttpAspect.logAround(mockJoinPoint);
//
//        verify(mockJoinPoint).proceed();
//    }
//}

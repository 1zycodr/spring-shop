package kz.iitu.itse1909.amirlan.kernel.aspect;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import static org.mockito.Mockito.*;

class LoggingAspectTest {
    @Mock
    Logger logger;
    @InjectMocks
    LoggingAspect loggingAspect;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testLogPointcut() {
        loggingAspect.logPointcut();
    }

    @Test
    void testLogUserAction() {
        Assertions.assertThrowsExactly(NullPointerException.class, () -> {
            loggingAspect.logUserAction(null);
        });
    }

    @Test
    void testLogExecutionFinished() {
        Assertions.assertThrowsExactly(NullPointerException.class, () -> {
            loggingAspect.logExecutionFinished(null);
        });
    }

    @Test
    void testLogSuccessReturn() {
        Assertions.assertThrowsExactly(NullPointerException.class, () -> {
            loggingAspect.logSuccessReturn(null);
        });
    }

    @Test
    void testLogThrowing() {
        Assertions.assertThrowsExactly(NullPointerException.class, () -> {
            loggingAspect.logThrowing(null);
        });
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
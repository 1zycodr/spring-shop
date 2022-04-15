package kz.iitu.itse1909.amirlan.kernel.error.handler;

import kz.iitu.itse1909.amirlan.kernel.error.exceptions.EntityNotFoundException;
import kz.iitu.itse1909.amirlan.kernel.error.exceptions.InvalidArgumentException;
import org.apache.commons.logging.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

class CoreExceptionHandlerTest {
    @Mock
    Log pageNotFoundLogger;
    @Mock
    Log logger;
    @InjectMocks
    CoreExceptionHandler coreExceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testHandleInvalidArgumentException() {
        Assertions.assertDoesNotThrow(() -> {
            ResponseEntity<Object> result = coreExceptionHandler.handleInvalidArgumentException(new InvalidArgumentException("message"));
            Assertions.assertNotEquals(null, result);
        });
    }

    @Test
    void testHandleEntityNotFoundException() {
        Assertions.assertDoesNotThrow(() -> {
            ResponseEntity<Object> result = coreExceptionHandler.handleEntityNotFoundException(new EntityNotFoundException("entityId"));
            Assertions.assertNotEquals(null, result);
        });
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
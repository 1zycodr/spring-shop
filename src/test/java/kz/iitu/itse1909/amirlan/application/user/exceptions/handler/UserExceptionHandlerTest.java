package kz.iitu.itse1909.amirlan.application.user.exceptions.handler;

import kz.iitu.itse1909.amirlan.application.user.exceptions.UserAlreadyExistsException;
import org.apache.commons.logging.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

class UserExceptionHandlerTest {
    @Mock
    Log pageNotFoundLogger;
    @Mock
    Log logger;
    @InjectMocks
    UserExceptionHandler userExceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testHandleUserAlreadyExistsException() {
        Assertions.assertDoesNotThrow(() -> {
            ResponseEntity<Object> result = userExceptionHandler.handleUserAlreadyExistsException(new UserAlreadyExistsException());
            Assertions.assertNotEquals(null, result);
        });
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
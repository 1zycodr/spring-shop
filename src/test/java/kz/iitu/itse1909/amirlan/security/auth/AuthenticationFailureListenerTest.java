package kz.iitu.itse1909.amirlan.security.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.*;

class AuthenticationFailureListenerTest {
    @Mock
    HttpServletRequest request;
    @Mock
    LoginAttemptService loginAttemptService;
    @InjectMocks
    AuthenticationFailureListener authenticationFailureListener;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testOnApplicationEvent() {
        authenticationFailureListener.onApplicationEvent(null);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
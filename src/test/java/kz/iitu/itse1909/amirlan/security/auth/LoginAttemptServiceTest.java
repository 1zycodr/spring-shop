package kz.iitu.itse1909.amirlan.security.auth;

import com.google.common.cache.LoadingCache;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class LoginAttemptServiceTest {
    @Mock
    LoadingCache<String, Integer> attemptsCache;
    @InjectMocks
    LoginAttemptService loginAttemptService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testLoginSucceeded() {
        loginAttemptService.loginSucceeded("key");
    }

    @Test
    void testLoginFailed() {
        Assertions.assertThrowsExactly(NullPointerException.class, () -> {
            loginAttemptService.loginFailed("key");
        });
    }

    @Test
    void testIsBlocked() {
        Assertions.assertThrowsExactly(NullPointerException.class, () -> {
            boolean result = loginAttemptService.isBlocked("key");
            Assertions.assertEquals(true, result);
        });
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
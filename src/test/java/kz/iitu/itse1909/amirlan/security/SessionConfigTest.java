package kz.iitu.itse1909.amirlan.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.session.web.http.HttpSessionIdResolver;

class SessionConfigTest {
    SessionConfig sessionConfig = new SessionConfig();

    @Test
    void testHttpSessionIdResolver() {
        Assertions.assertDoesNotThrow(() -> {
            sessionConfig.httpSessionIdResolver();
        });
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
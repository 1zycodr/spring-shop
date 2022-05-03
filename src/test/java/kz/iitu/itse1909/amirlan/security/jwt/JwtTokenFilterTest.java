package kz.iitu.itse1909.amirlan.security.jwt;

import org.apache.commons.logging.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Set;

import static org.mockito.Mockito.*;

class JwtTokenFilterTest {
    @Mock
    JwtTokenProvider jwtTokenProvider;
    @Mock
    Log logger;
    @Mock
    Environment environment;
    @Mock
    ServletContext servletContext;
    @Mock
    FilterConfig filterConfig;
    @Mock
    Set<String> requiredProperties;
    @InjectMocks
    JwtTokenFilter jwtTokenFilter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testDoFilterInternal() throws ServletException, IOException {
        Assertions.assertThrowsExactly(NullPointerException.class, () -> {
            when(jwtTokenProvider.getAuthentication(anyString())).thenReturn(null);
            when(jwtTokenProvider.resolveToken(any())).thenReturn("resolveTokenResponse");
            when(jwtTokenProvider.validateToken(anyString())).thenReturn(true);

            jwtTokenFilter.doFilterInternal(null, null, null);
        });
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
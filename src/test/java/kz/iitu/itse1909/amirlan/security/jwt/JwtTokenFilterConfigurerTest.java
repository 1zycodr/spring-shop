package kz.iitu.itse1909.amirlan.security.jwt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import springfox.documentation.spi.service.contexts.SecurityContextBuilder;

import static org.mockito.Mockito.*;

class JwtTokenFilterConfigurerTest {
    @Mock
    JwtTokenProvider jwtTokenProvider;
    @Mock
    SecurityContextBuilder securityBuilder;
    //Field objectPostProcessor of type CompositeObjectPostProcessor - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @InjectMocks
    JwtTokenFilterConfigurer jwtTokenFilterConfigurer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testConfigure() throws Exception {
        Assertions.assertThrowsExactly(NullPointerException.class, () -> {
            jwtTokenFilterConfigurer.configure(null);
        });
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
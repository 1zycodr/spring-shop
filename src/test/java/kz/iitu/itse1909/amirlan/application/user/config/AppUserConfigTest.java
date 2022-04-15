package kz.iitu.itse1909.amirlan.application.user.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;

class AppUserConfigTest {
    @Mock
    PasswordEncoder passwordEncoder;
    @InjectMocks
    AppUserConfig appUserConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testConversionService() {
        ConversionServiceFactoryBean result = appUserConfig.conversionService();
        Assertions.assertNotNull(result);
    }

    @Test
    void testDemo() {
        Assertions.assertDoesNotThrow(() -> {
            appUserConfig.demo();
        });
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
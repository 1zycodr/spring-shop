package kz.iitu.itse1909.amirlan.security;

import org.apache.commons.logging.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.accept.ContentNegotiationStrategy;

import static org.mockito.Mockito.*;

class WebSecurityConfigTest {
    @Mock
    UserDetailsService userDetailsService;
    @Mock
    Log logger;
    @Mock
    ApplicationContext context;
    @Mock
    ContentNegotiationStrategy contentNegotiationStrategy;
    @Mock
    ObjectPostProcessor<Object> objectPostProcessor;
    @Mock
    AuthenticationConfiguration authenticationConfiguration;
    @Mock
    AuthenticationManagerBuilder authenticationBuilder;
    @Mock
    AuthenticationManagerBuilder localConfigureAuthenticationBldr;
    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    AuthenticationTrustResolver trustResolver;
    //Field http of type HttpSecurity - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @InjectMocks
    WebSecurityConfig webSecurityConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testPasswordEncoder() {
        Assertions.assertDoesNotThrow(() -> {
            PasswordEncoder result = webSecurityConfig.passwordEncoder();
            Assertions.assertNotEquals(null, result);
        });
    }

    @Test
    void testAnotherPasswordEncoder() {
        PasswordEncoder result = webSecurityConfig.anotherPasswordEncoder();
        Assertions.assertNotEquals(null, result);
    }

    @Test
    void testRoleHierarchy() {
        RoleHierarchy result = webSecurityConfig.roleHierarchy();
        Assertions.assertNotEquals(null, result);
    }

//    @Test
//    void testConfigure() {
//        webSecurityConfig.configure(null);
//    }
//
//    @Test
//    void testConfigure2() {
//        webSecurityConfig.configure(null);
//    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
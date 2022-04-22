package kz.iitu.itse1909.amirlan.security.jwt;

import kz.iitu.itse1909.amirlan.application.user.entity.Privilege;
import kz.iitu.itse1909.amirlan.application.user.entity.Role;
import kz.iitu.itse1909.amirlan.application.user.service.impl.AppUserDetailsService;
import kz.iitu.itse1909.amirlan.kernel.error.exceptions.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;

import java.util.Arrays;

import static org.mockito.Mockito.*;

class JwtTokenProviderTest {
    @Mock
    AppUserDetailsService myUserDetails;
    @InjectMocks
    JwtTokenProvider jwtTokenProvider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testInit() {
        Assertions.assertThrowsExactly(NullPointerException.class, () -> {
            jwtTokenProvider.init();
        });
    }

    @Test
    void testCreateToken() {
        Assertions.assertThrowsExactly(NullPointerException.class, () -> {
            String result = jwtTokenProvider.createToken("username", Arrays.<Role>asList(new Role(Long.valueOf(1), "name", Arrays.<Privilege>asList(new Privilege(Long.valueOf(1), "name", Arrays.<Role>asList(null))))));
            Assertions.assertEquals("replaceMeWithExpectedResult", result);
        });
    }

    @Test
    void testGetAuthentication() {
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> {
            when(myUserDetails.loadUserByUsername(anyString())).thenReturn(null);

            Authentication result = jwtTokenProvider.getAuthentication("token");
            Assertions.assertEquals(null, result);
        });
    }

    @Test
    void testGetUsername() {
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> {
            String result = jwtTokenProvider.getUsername("token");
            Assertions.assertEquals("replaceMeWithExpectedResult", result);
        });
    }

    @Test
    void testResolveToken() {
        Assertions.assertThrowsExactly(NullPointerException.class, () -> {
            String result = jwtTokenProvider.resolveToken(null);
            Assertions.assertEquals("replaceMeWithExpectedResult", result);
        });
    }

    @Test
    void testValidateToken() {
        Assertions.assertThrowsExactly(EntityNotFoundException.class, () -> {
            boolean result = jwtTokenProvider.validateToken("token");
            Assertions.assertEquals(true, result);
        });
    }
}

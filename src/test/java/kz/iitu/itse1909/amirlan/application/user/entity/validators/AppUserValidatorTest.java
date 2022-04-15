package kz.iitu.itse1909.amirlan.application.user.entity.validators;

import kz.iitu.itse1909.amirlan.application.user.entity.AppUser;
import kz.iitu.itse1909.amirlan.application.user.entity.Privilege;
import kz.iitu.itse1909.amirlan.application.user.entity.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class AppUserValidatorTest {
    @Mock
    List<String> prohibitedUsernames;
    @InjectMocks
    AppUserValidator appUserValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testInitialize() {
        Assertions.assertDoesNotThrow(() -> {
            appUserValidator.initialize(null);
        });
    }

    @Test
    void testIsValid() {
        boolean result = appUserValidator.isValid(
                new AppUser(
                        Long.valueOf(1), "username", "password", "avatar",
                        Arrays.<Role>asList(new Role(
                                Long.valueOf(1), "name",
                                Arrays.<Privilege>asList(new Privilege(Long.valueOf(1), "name", Arrays.<Role>asList())))
                        )
                ), null);
        boolean result2 = appUserValidator.isValid(new AppUser(
                Long.valueOf(1), "god", "password", "avatar",
                Arrays.<Role>asList(new Role(
                        Long.valueOf(1), "name",
                        Arrays.<Privilege>asList(new Privilege(Long.valueOf(1), "name", Arrays.<Role>asList())))
                )
        ), null
        );
        Assertions.assertEquals(true, result);
        Assertions.assertEquals(false, result2);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
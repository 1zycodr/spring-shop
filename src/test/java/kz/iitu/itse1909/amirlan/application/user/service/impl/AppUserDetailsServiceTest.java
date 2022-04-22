package kz.iitu.itse1909.amirlan.application.user.service.impl;

import kz.iitu.itse1909.amirlan.application.user.entity.AppUser;
import kz.iitu.itse1909.amirlan.application.user.repository.RoleRepository;
import kz.iitu.itse1909.amirlan.application.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AppUserDetailsServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    RoleRepository roleRepository;

    private AppUserDetailsService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.service = new AppUserDetailsService(userRepository, roleRepository);
    }

    @Test
    void logTests() {
        Assertions.assertDoesNotThrow( () -> {
            this.service.logPostConstruct();
            this.service.logPreDestroy();
        });
    }

    @Test
    void loadUserByUsername() {
        Assertions.assertThrowsExactly(NullPointerException.class, () -> {
            AppUser user = AppUser.builder()
                    .username("admin")
                    .password("password")
                    .build();
            when(userRepository.findUserByUsername(any())).thenReturn(user);
            assertNotNull(this.service.loadUserByUsername("admin"));
        });
    }

    @Test
    void getAuthorities() {
    }

    @Test
    void getPrivileges() {
    }
}
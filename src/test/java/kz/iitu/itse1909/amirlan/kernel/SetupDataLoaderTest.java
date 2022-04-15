package kz.iitu.itse1909.amirlan.kernel;

import kz.iitu.itse1909.amirlan.application.user.entity.Privilege;
import kz.iitu.itse1909.amirlan.application.user.entity.Role;
import kz.iitu.itse1909.amirlan.application.user.repository.PrivilegeRepository;
import kz.iitu.itse1909.amirlan.application.user.repository.RoleRepository;
import kz.iitu.itse1909.amirlan.application.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

import static org.mockito.Mockito.*;

class SetupDataLoaderTest {
    @Mock
    UserRepository userRepository;
    @Mock
    RoleRepository roleRepository;
    @Mock
    PrivilegeRepository privilegeRepository;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    Logger log;
    @InjectMocks
    SetupDataLoader setupDataLoader;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testOnApplicationEvent() {
        when(roleRepository.findByName(anyString())).thenReturn(new Role(Long.valueOf(1), "name", Arrays.<Privilege>asList(new Privilege(Long.valueOf(1), "name", Arrays.<Role>asList()))));
        when(privilegeRepository.findPrivilegeByName(anyString())).thenReturn(new Privilege(Long.valueOf(1), "name", Arrays.<Role>asList()));
        setupDataLoader.onApplicationEvent(null);
    }

    @Test
    void testCreatePrivilegeIfNotFound() {
        when(privilegeRepository.findPrivilegeByName(anyString())).thenReturn(new Privilege(Long.valueOf(1), "name", Arrays.<Role>asList()));
        Assertions.assertDoesNotThrow(() -> {
            setupDataLoader.createPrivilegeIfNotFound("name");
        });
    }

    @Test
    void testCreateRoleIfNotFound() {
        when(roleRepository.findByName(anyString())).thenReturn(new Role(Long.valueOf(1), "name", Arrays.<Privilege>asList(new Privilege(Long.valueOf(1), "name", Arrays.<Role>asList()))));
        Assertions.assertDoesNotThrow(() -> {
            setupDataLoader.createRoleIfNotFound("name", Arrays.<Privilege>asList(new Privilege(Long.valueOf(1), "name", Arrays.<Role>asList())));
        });
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
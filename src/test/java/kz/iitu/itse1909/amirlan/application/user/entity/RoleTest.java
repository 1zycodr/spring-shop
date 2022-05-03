package kz.iitu.itse1909.amirlan.application.user.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.*;

class RoleTest {
    @Mock
    Collection<Privilege> privileges;
    @InjectMocks
    Role role;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSetId() {
        role.setId(Long.valueOf(1));
    }

    @Test
    void testSetName() {
        role.setName("name");
    }

    @Test
    void testSetPrivileges() {
        Assertions.assertThrowsExactly(NullPointerException.class, () -> {
            role.setPrivileges(Arrays.<Privilege>asList(new Privilege(Long.valueOf(1), "name", Arrays.<Role>asList(new Role(Long.valueOf(1), "name", Arrays.<Privilege>asList(null))))));
        });
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
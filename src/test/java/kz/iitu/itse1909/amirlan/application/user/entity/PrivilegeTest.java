package kz.iitu.itse1909.amirlan.application.user.entity;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    AppUser user = new AppUser();

    @Test
    void getId() {
        user.setId(10L);
        assertEquals(user.getId(), 10L);
    }

    @Test
    void getName() {
        user.setUsername("test");
        assertEquals(user.getUsername(), "test");
    }

    @Test
    void getRoles() {
        user.setRoles(new ArrayList<>());
        assertNotNull(user.getRoles());
    }

    @Test
    void setId() {
        user.setId(10L);
        assertEquals(user.getId(), 10L);
    }

    @Test
    void setId2() {
        user.setId(10L);
        ReflectionTestUtils.setField(user, "id", 1L);
        assertEquals(user.getId(), 1L);
    }

    @Test
    void setName() {
        user.setUsername("test");
        assertEquals(user.getUsername(), "test");
    }

    @Test
    void setRoles() {
        user.setRoles(new ArrayList<>());
        assertEquals(user.getRoles().size(), 0);
    }
}
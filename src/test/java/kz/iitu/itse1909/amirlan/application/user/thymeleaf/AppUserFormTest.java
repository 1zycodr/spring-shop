package kz.iitu.itse1909.amirlan.application.user.thymeleaf;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AppUserFormTest {
    AppUserForm appUserForm = new AppUserForm("username", "password", "avatar");

    @Test
    void testSetUsername() {
        AppUserForm userForm = new AppUserForm();
        appUserForm.setUsername("username");
    }

    @Test
    void testSetPassword() {
        appUserForm.setPassword("password");
    }

    @Test
    void testSetAvatar() {
        appUserForm.setAvatar("avatar");
    }

    @Test
    void testEquals() {
        boolean result = appUserForm.equals("o");
        Assertions.assertEquals(false, result);
    }

    @Test
    void testCanEqual() {
        boolean result = appUserForm.canEqual("other");
        Assertions.assertEquals(false, result);
    }

    @Test
    void testHashCode() {
        int result = appUserForm.hashCode();
        Assertions.assertNotNull(result);
    }

    @Test
    void testToString() {
        String result = appUserForm.toString();
        Assertions.assertNotNull(result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
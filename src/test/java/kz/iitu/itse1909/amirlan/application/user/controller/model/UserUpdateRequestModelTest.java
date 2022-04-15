package kz.iitu.itse1909.amirlan.application.user.controller.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserUpdateRequestModelTest {
    UserUpdateRequestModel userUpdateRequestModel = new UserUpdateRequestModel();

    @Test
    void testSetUsername() {
        userUpdateRequestModel.setUsername("username");
        Assertions.assertEquals("username", userUpdateRequestModel.getUsername());
    }

    @Test
    void testSetPassword() {
        userUpdateRequestModel.setPassword("password");
        Assertions.assertEquals("password", userUpdateRequestModel.getPassword());
    }
}

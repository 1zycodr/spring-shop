package kz.iitu.itse1909.amirlan.application.user.controller.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserCreateRequestModelTest {
    UserCreateRequestModel userCreateRequestModel = new UserCreateRequestModel();

    @Test
    void testSetUsername() {
        userCreateRequestModel.setUsername("username");
        Assertions.assertEquals("username", userCreateRequestModel.getUsername());
    }

    @Test
    void testSetPassword() {
        userCreateRequestModel.setPassword("password");
        Assertions.assertEquals("password", userCreateRequestModel.getPassword());
        Assertions.assertNotNull(userCreateRequestModel.getPassword());
    }
}

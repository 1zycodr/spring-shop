package kz.iitu.itse1909.amirlan.application.user.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

class TemplateControllerTest {
    TemplateController templateController = new TemplateController();

    @Test
    void testGet() throws IOException {
        ResponseEntity<String> result = templateController.get();
        Assertions.assertNotNull(result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
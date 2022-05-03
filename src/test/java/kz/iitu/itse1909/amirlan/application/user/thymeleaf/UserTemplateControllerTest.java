package kz.iitu.itse1909.amirlan.application.user.thymeleaf;

import kz.iitu.itse1909.amirlan.application.user.entity.AppUser;
import kz.iitu.itse1909.amirlan.application.user.entity.Role;
import kz.iitu.itse1909.amirlan.application.user.service.UserService;
import kz.iitu.itse1909.amirlan.kernel.FileStorageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;

import static org.mockito.Mockito.*;

class UserTemplateControllerTest {
    @Mock
    UserService userService;
    @Mock
    FileStorageService fileStorageService;
    @InjectMocks
    UserTemplateController userTemplateController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testIndex() {
        Assertions.assertThrowsExactly(NullPointerException.class, () -> {
            String result = userTemplateController.index(null);
            Assertions.assertEquals("replaceMeWithExpectedResult", result);
        });
    }

    @Test
    void testSocket() {
        String result = userTemplateController.socket(null);
        Assertions.assertEquals("socket", result);
    }

    @Test
    void testRegister() {
        Assertions.assertThrowsExactly(NullPointerException.class, () -> {
            String result = userTemplateController.register(null);
            Assertions.assertEquals("socket", result);
        });
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
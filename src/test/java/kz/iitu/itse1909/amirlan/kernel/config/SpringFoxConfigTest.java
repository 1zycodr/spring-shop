package kz.iitu.itse1909.amirlan.kernel.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import springfox.documentation.spring.web.plugins.Docket;

class SpringFoxConfigTest {
    SpringFoxConfig springFoxConfig = new SpringFoxConfig();

    @Test
    void testApi() {
        Docket result = springFoxConfig.api();
        Assertions.assertNotEquals(null, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
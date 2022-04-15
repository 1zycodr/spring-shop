package kz.iitu.itse1909.amirlan.kernel.error;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class ApiErrorUtilsTest {

    @Test
    void testBuildResponseEntity() {
        ResponseEntity<Object> result = ApiErrorUtils.buildResponseEntity(new ApiError(HttpStatus.CONTINUE,
                "message", new NullPointerException()));
        Assertions.assertNotEquals(null, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
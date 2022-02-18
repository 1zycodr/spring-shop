package kz.iitu.itse1909.amirlan.kernel.error;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ApiErrorTest {
    ApiError apiError;

    @BeforeEach
    void setUp() {
        apiError = new ApiError(HttpStatus.BAD_REQUEST, "test", new RuntimeException());
    }

    @Test
    void testConstructors() {
        assertDoesNotThrow(() -> {
            new ApiError(HttpStatus.BAD_REQUEST);
            new ApiError(HttpStatus.BAD_REQUEST, new RuntimeException());
            new ApiError(HttpStatus.BAD_REQUEST, "test", new RuntimeException());
        });
    }

    @Test
    void getStatus() {
        assertEquals(apiError.getStatus(), HttpStatus.BAD_REQUEST);
    }

    @Test
    void getTime() {
        assertNotNull(apiError.getTime());
    }

    @Test
    void getMessage() {
        assertNotNull(apiError.getMessage());
    }

    @Test
    void getDebugMessage() {
        assertNull(apiError.getDebugMessage());
    }

    @Test
    void setStatus() {
        assertDoesNotThrow(() -> {
            apiError.setStatus(HttpStatus.BAD_REQUEST);
        });
    }

    @Test
    void setTime() {
        assertDoesNotThrow(() -> {
            apiError.setTime(LocalDateTime.MIN);
        });
    }

    @Test
    void setMessage() {
        assertDoesNotThrow(() -> {
            apiError.setMessage("HttpStatus.BAD_REQUEST");
        });
    }

    @Test
    void setDebugMessage() {
        assertDoesNotThrow(() -> {
            apiError.setDebugMessage("HttpStatus.BAD_REQUEST");
        });
    }
}
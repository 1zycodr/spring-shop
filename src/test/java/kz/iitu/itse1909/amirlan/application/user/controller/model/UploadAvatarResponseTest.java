package kz.iitu.itse1909.amirlan.application.user.controller.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UploadAvatarResponseTest {
    private UploadAvatarResponse response;

    @BeforeEach
    void setUp() {
        response = new UploadAvatarResponse();
    }

    @Test
    void testConstructor() {
        response = new UploadAvatarResponse("a", "a", "a", 5L);
        Assertions.assertEquals(response.getSize(), 5L);
    }

    @Test
    void getFileName() {
        String value = "test";
        response.setFileName(value);
        Assertions.assertEquals(response.getFileName(), value);
    }

    @Test
    void getFileDownloadUri() {
        String value = "test";
        response.setFileDownloadUri(value);
        Assertions.assertEquals(response.getFileDownloadUri(), value);
    }

    @Test
    void getFileType() {
        String value = "test";
        response.setFileType(value);
        Assertions.assertEquals(response.getFileType(), value);
    }

    @Test
    void getSize() {
        Long value = 5L;
        response.setSize(value);
        Assertions.assertEquals(response.getSize(), value);
    }

    @Test
    void setFileName() {
    }

    @Test
    void setFileDownloadUri() {
    }

    @Test
    void setFileType() {
    }

    @Test
    void setSize() {
    }
}
package kz.iitu.itse1909.amirlan.kernel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;

import java.nio.file.Path;

import static org.mockito.Mockito.*;

class FileStorageServiceTest {
    @Mock
    Path fileStorageLocation;
    @InjectMocks
    FileStorageService fileStorageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testStoreFile() {
        Assertions.assertThrowsExactly(RuntimeException.class, () -> {
            fileStorageService.storeFile(new MockMultipartFile("test", new byte[]{}));
        });
    }

    @Test
    void testLoadFileAsResource() {
        Assertions.assertThrowsExactly(RuntimeException.class, () -> {
            fileStorageService.loadFileAsResource("fileName");
        });
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
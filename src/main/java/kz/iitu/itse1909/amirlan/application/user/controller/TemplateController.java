package kz.iitu.itse1909.amirlan.application.user.controller;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Lazy
@RestController
@RequestMapping("/socket")
public class TemplateController {
//    @GetMapping
//    public ResponseEntity<String> get() throws IOException {
//        Path path = Path.of("src/main/resources/templates/socket.html");
//        return ResponseEntity.ok(Files.readString(path));
//    }
}

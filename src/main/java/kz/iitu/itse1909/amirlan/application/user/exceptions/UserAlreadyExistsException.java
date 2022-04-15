package kz.iitu.itse1909.amirlan.application.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExistsException extends RuntimeException {
    final static String message = "User with such username already exists";
    public UserAlreadyExistsException() {
        super(message);
    }
}

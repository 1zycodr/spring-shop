package kz.iitu.itse1909.amirlan.application.user.controller;

import kz.iitu.itse1909.amirlan.application.user.controller.model.UserRequestModel;
import kz.iitu.itse1909.amirlan.application.user.exceptions.InvalidArgumentException;
import kz.iitu.itse1909.amirlan.application.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequestModel user, Errors errors) {
        if (errors.hasErrors()) {
            throw new InvalidArgumentException(errors.getAllErrors().get(0).getDefaultMessage());
        }
        return ResponseEntity.ok(userService.create(user));
    }
}

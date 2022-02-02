package kz.iitu.itse1909.amirlan.application.user.controller;

import kz.iitu.itse1909.amirlan.application.user.controller.model.UserCreateRequestModel;
import kz.iitu.itse1909.amirlan.application.user.controller.model.UserUpdateRequestModel;
import kz.iitu.itse1909.amirlan.kernel.error.exceptions.InvalidArgumentException;
import kz.iitu.itse1909.amirlan.application.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> createUser(@Valid @RequestBody UserCreateRequestModel user, Errors errors) {
        processErrors(errors);
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userService.getUsersList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,
                                        @Valid @RequestBody UserUpdateRequestModel user,
                                        Errors errors
    ) {
        processErrors(errors);
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    public void processErrors(Errors errors) throws InvalidArgumentException {
        if (errors.hasErrors()) {
            List<String> errorsList = new ArrayList<>();
            for (ObjectError err: errors.getAllErrors()) { errorsList.add(err.getDefaultMessage());}
            throw new InvalidArgumentException(String.join(", ", errorsList));
        }
    }
}

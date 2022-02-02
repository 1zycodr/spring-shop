package kz.iitu.itse1909.amirlan.application.user.controller.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserUpdateRequestModel {
    @NotEmpty(message="Username cannot be empty")
    private String username;

    @Length(min = 8, message = "Password must contain at least 8 characters")
    private String password;
}

package kz.iitu.itse1909.amirlan.application.user.controller.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotEmpty;

public class UserUpdateRequestModel {
    @NotEmpty(message="Username cannot be empty")
    private String username;

    @Length(min = 8, message = "Password must contain at least 8 characters")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

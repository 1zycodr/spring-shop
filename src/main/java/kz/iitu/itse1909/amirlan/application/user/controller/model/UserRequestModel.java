package kz.iitu.itse1909.amirlan.application.user.controller.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserRequestModel {
    @NotEmpty
    @NotNull
    private String username;

    @NotEmpty
    @NotNull
    @Length(min = 8)
    private String password;
}
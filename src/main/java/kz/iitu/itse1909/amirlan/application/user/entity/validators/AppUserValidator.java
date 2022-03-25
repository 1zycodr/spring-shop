package kz.iitu.itse1909.amirlan.application.user.entity.validators;

import kz.iitu.itse1909.amirlan.application.user.entity.AppUser;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class AppUserValidator implements ConstraintValidator<AppUserValidate, AppUser> {
    private static final List<String> prohibitedUsernames = new ArrayList<String>(){
        {
            add("administrator");
            add("moderator");
            add("god");
        }
    };
    @Override
    public void initialize(AppUserValidate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(AppUser appUser, ConstraintValidatorContext constraintValidatorContext) {
        for (String username: prohibitedUsernames) {
            if (appUser.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }
}

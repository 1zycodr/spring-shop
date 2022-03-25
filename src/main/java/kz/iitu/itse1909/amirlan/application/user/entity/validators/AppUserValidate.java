package kz.iitu.itse1909.amirlan.application.user.entity.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = AppUserValidator.class)
@Documented
public @interface AppUserValidate {
    String message() default "AppUser Validation failed!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

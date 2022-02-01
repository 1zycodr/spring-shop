package kz.iitu.itse1909.amirlan.application.user.exceptions;

import kz.iitu.itse1909.amirlan.kernel.error.ApiError;
import kz.iitu.itse1909.amirlan.kernel.error.ApiErrorUtils;
import kz.iitu.itse1909.amirlan.application.user.controller.UserController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackageClasses = UserController.class)
public class UserExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    protected ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return ApiErrorUtils.buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), ex));
    }
}

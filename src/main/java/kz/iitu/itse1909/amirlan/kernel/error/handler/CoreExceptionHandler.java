package kz.iitu.itse1909.amirlan.kernel.error.handler;

import kz.iitu.itse1909.amirlan.kernel.error.exceptions.EntityNotFoundException;
import kz.iitu.itse1909.amirlan.kernel.error.exceptions.InvalidArgumentException;
import kz.iitu.itse1909.amirlan.kernel.error.ApiError;
import kz.iitu.itse1909.amirlan.kernel.error.ApiErrorUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CoreExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InvalidArgumentException.class)
    protected ResponseEntity<Object> handleInvalidArgumentException(InvalidArgumentException ex) {
        return ApiErrorUtils.buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), ex));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ApiErrorUtils.buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), ex));
    }
}

package com.bjit.raihan.api.errors;

import com.bjit.raihan.api.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiErrorHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex
            , HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ApiValidationError> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(ApiValidationError::new)
                .collect(Collectors.toList());

        return new ApiResponse("Validation Error")
                .status(HttpStatus.BAD_REQUEST)
                .errors(errors)
                .send();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity handleEntityNotFound(EntityNotFoundException ex) {
        return new ApiResponse(ex.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .send();
    }
}

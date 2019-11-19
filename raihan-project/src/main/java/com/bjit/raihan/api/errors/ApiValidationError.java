package com.bjit.raihan.api.errors;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@Data
@EqualsAndHashCode(callSuper = false)
class ApiValidationError extends ApiSubError {

    private String field;
    private Object rejectedValue;
    private String message;

    ApiValidationError(ObjectError error) {
        field = ((FieldError) error).getField();
        rejectedValue = ((FieldError) error).getRejectedValue();
        message = error.getDefaultMessage();
    }
}

package com.bjit.raihan.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
/**
 * In this class I'm trying to do
 * something like lombok.Builder does
 */
public class ApiResponse {
    private int status = HttpStatus.OK.value();
    private String message;
    private Object data;
    private Object error;
    private Object errors;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();

    public ApiResponse() { }

    public ApiResponse(String message) {
        this.message = message;
    }

    public ApiResponse status(HttpStatus status) {
        setStatus(status);
        return this;
    }

    public ApiResponse data(Object data) {
        setData(data);
        return this;
    }

    public ApiResponse error(Object error) {
        setError(error);
        return this;
    }

    public ApiResponse errors(Object errors) {
        setErrors(errors);
        return this;
    }

    public ResponseEntity<Object> created(Object data) {
        setStatus(HttpStatus.CREATED);
        setData(data);
        return send();
    }

    public ResponseEntity<Object> accepted(Object data) {
        setStatus(HttpStatus.ACCEPTED);
        setData(data);
        return send();
    }

    public ResponseEntity<Object> send(Object data) {
        setData(data);
        return send();
    }

    public ResponseEntity<Object> send() {
        return ResponseEntity.status(status).body(this);
    }

    private void setStatus(HttpStatus status) {
        this.status = status.value();
    }
}

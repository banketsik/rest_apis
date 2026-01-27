package org.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ExceptionResponse> handleException(ResponseStatusException exception) {
        return buildResponseEntity(exception, HttpStatus.valueOf(exception.getStatusCode().value()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleEsception(Exception exception) {
        return buildResponseEntity(exception, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ExceptionResponse> buildResponseEntity(Exception exception, HttpStatus httpStatus) {
        ExceptionResponse errorResponse = new ExceptionResponse();
        errorResponse.setStatus(httpStatus.value());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, httpStatus);
    }
}

package com.nobodyiam.web.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nobodyiam.web.model.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by Jason on 7/6/15.
 */
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorResult> handleWebExceptions(Exception ex,
                                               WebRequest request) throws JsonProcessingException {
        ErrorResult error = new ErrorResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(error);

    }
}

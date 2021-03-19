package com.pikulokama.kinocmstest.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionInterceptor {

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleInternalServerError(Exception exception) {
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .status(500)
                .build();
    }

    @ExceptionHandler(RestServiceException.class)
    public ErrorResponse handleRestServiceException(RestServiceException restServiceException) {
        return ErrorResponse.builder()
                .message(restServiceException.getMessage())
                .status(400)
                .build();
    }

}

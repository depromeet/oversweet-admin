package com.depromeet.common.exception;


import com.depromeet.common.exception.common.ErrorResponse;
import com.depromeet.common.exception.http.InternalServerErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleBindingException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getFieldError();
        Objects.requireNonNull(fieldError);

        log.info(String.format("MethodArgumentNotValidException %s", e.getMessage()), e);
        ErrorType errorType = ErrorType.of(fieldError.getDefaultMessage());
        return ResponseEntity.badRequest().body(ErrorResponse.of(errorType));
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ErrorResponse> handleInternalServerErrorException(InternalServerErrorException e) {
        log.info(String.format("InternalServerErrorException %s", e.getServerMessage()), e);
        return ResponseEntity.status(e.getHttpStatus()).body(ErrorResponse.of(e.getErrorType()));
    }

    @ExceptionHandler(OversweetException.class)
    public ResponseEntity<ErrorResponse> handleOversweetException(OversweetException e) {
        log.info(String.format("%s %s", e.getClass().getName(), e.getErrorType().getMessage()), e);
        return ResponseEntity.status(e.getHttpStatus()).body(ErrorResponse.of(e.getErrorType()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingParams(MissingServletRequestParameterException e) {
        log.info(String.format("MissingServletRequestParameterException %s", e.getMessage()), e);
        String message = String.format("파라미터를 입력해야 합니다.(%s)", e.getParameterName());
        return ResponseEntity.badRequest().body(ErrorResponse.of("E001", message));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.info(String.format("Exception %s", e.getMessage()), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.of(ErrorType.NOTUSED_EXCEPTION));
    }
}

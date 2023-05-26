package com.depromeet.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class OversweetException extends RuntimeException{
    private final HttpStatus httpStatus;
    private final ErrorType errorType = ErrorType.of(this.getClass());
}

package com.depromeet.common.exception;

import org.springframework.http.HttpStatus;

public class ExternalException extends OversweetException {
    public ExternalException() {
        super(HttpStatus.BAD_REQUEST);
    }
}


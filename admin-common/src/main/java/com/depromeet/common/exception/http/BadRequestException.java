package com.depromeet.common.exception.http;

import com.depromeet.common.exception.OversweetException;
import org.springframework.http.HttpStatus;

public class BadRequestException extends OversweetException {
    public BadRequestException() {
        super(HttpStatus.BAD_REQUEST);
    }
}

package com.depromeet.common.exception.http;

import com.depromeet.common.exception.OversweetException;
import org.springframework.http.HttpStatus;

public class ConflictException extends OversweetException {
    public ConflictException() {
        super(HttpStatus.CONFLICT);
    }
}

package com.depromeet.common.exception.http;

import com.depromeet.common.exception.OversweetException;
import org.springframework.http.HttpStatus;

public class ForbiddenException extends OversweetException {
    public ForbiddenException() {
        super(HttpStatus.FORBIDDEN);
    }
}

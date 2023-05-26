package com.depromeet.common.exception.http;

import com.depromeet.common.exception.OversweetException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends OversweetException {
    public NotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }
}

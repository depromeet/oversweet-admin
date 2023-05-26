package com.depromeet.common.exception.http;

import com.depromeet.common.exception.OversweetException;
import org.springframework.http.HttpStatus;

public class UnAuthorizedException extends OversweetException {
    public UnAuthorizedException() {
        super(HttpStatus.UNAUTHORIZED);
    }
}

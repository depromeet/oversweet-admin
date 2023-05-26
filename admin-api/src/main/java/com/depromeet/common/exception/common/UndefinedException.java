package com.depromeet.common.exception.common;

import com.depromeet.common.exception.http.InternalServerErrorException;

public class UndefinedException extends InternalServerErrorException {
    public UndefinedException(String serverMessage) {
        super(serverMessage);
    }
}

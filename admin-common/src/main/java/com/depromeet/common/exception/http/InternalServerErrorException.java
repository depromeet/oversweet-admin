package com.depromeet.common.exception.http;

import com.depromeet.common.exception.OversweetException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InternalServerErrorException extends OversweetException {

    private final String serverMessage;

    public InternalServerErrorException(String serverMessage) {
        super(HttpStatus.INTERNAL_SERVER_ERROR);
        this.serverMessage = serverMessage;
    }
}

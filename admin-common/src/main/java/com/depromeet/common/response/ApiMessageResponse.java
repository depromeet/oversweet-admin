package com.depromeet.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiMessageResponse extends ApiDefaultResponse{
    private ApiMessageResponse(HttpStatus status, String message) {
        super(status.value(), message);
    }
    public static ApiMessageResponse of(HttpStatus status, String message) {
        return new ApiMessageResponse(status, message);
    }
}

package com.depromeet.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiCommonResponse<T> extends ApiDefaultResponse{
    @Schema(description = "응답 데이터")
    private final T data;

    private ApiCommonResponse(HttpStatus status, String message, T data) {
        super(status.value(), message);
        this.data = data;
    }

    public static <T> ApiCommonResponse<T> of(HttpStatus status, String message, T data) {
        return new ApiCommonResponse<>(status, message, data);
    }
}

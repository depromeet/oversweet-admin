package com.depromeet.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public abstract class ApiDefaultResponse {

    @Schema(description = "응답 코드")
    protected int status;
    @Schema(description = "응답 메시지")
    protected String message;

    protected ApiDefaultResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}

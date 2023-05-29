package com.depromeet.franchise.dto.response;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record FranchiseResponseModel(
        @Schema(description = "해당 프랜차이즈 아이디") Long id,
        @Schema(description = "해당 프랜차이즈 이름") String name,
        @Schema(description = "해당 프랜차이즈 ImageUrl") String ImageUrl
) {
}

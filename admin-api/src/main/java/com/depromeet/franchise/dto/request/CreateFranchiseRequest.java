package com.depromeet.franchise.dto.request;

import com.depromeet.domain.franchise.Franchise;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.Builder;

@Builder
public record CreateFranchiseRequest(
        @Schema(description = "프랜차이즈 이름") String name,
        @Schema(description = "프랜차이즈 이미지 URL") @Nullable String imageUrl) {

    public Franchise toFranchise() {
        return Franchise.builder()
                .name(this.name)
                .imageUrl(this.imageUrl)
                .build();

    }
}

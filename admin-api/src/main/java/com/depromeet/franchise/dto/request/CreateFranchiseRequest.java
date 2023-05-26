package com.depromeet.franchise.dto.request;

import com.depromeet.domain.franchise.Franchise;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class CreateFranchiseRequest {

    @Schema(description = "프랜차이즈 이름")
    private String name;

    @Nullable
    @Schema(description = "프랜차이즈 이미지 URL")
    private String imageUrl;

    public Franchise toFranchise() {
        return Franchise.builder()
                .name(this.name)
                .imageUrl(this.imageUrl)
                .build();

    }
}

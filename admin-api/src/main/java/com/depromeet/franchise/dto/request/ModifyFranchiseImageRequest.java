package com.depromeet.franchise.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;


@Builder
public record ModifyFranchiseImageRequest(@Schema(description = "프랜차이즈 이미지 URL") String imageUrl) {
}

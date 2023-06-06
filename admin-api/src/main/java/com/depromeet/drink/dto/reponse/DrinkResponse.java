package com.depromeet.drink.dto.reponse;

import com.depromeet.domain.drink.domain.DrinkCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record DrinkResponse(
        @Schema(description = "해당 음료의 id") Long id,
        @Schema(description = "해당 음료의 이름") String name,
        @Schema(description = "해당 음료의 프랜차이즈 이름") String franchiseName,
        @Schema(description = "해당 음료의 사이즈") int size,
        @Schema(description = "해당 음료의 당") int sugar,
        @Schema(description = "해당 음료의 칼로리") int calorie,
        @Schema(description = "해당 음료의 이미지 Url") String imageUrl,
        @Schema(description = "해당 음료의 카테고리") DrinkCategory category
) {
}

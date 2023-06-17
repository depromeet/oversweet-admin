package com.depromeet.drink.dto.request;

import com.depromeet.domain.drink.domain.DrinkCategory;
import io.swagger.v3.oas.annotations.media.Schema;

public record ModifyDrinkRequest(
        @Schema(description = "음료 이름") String name,
        @Schema(description = "음료 사이즈") int size,
        @Schema(description = "음료 당") int sugar,
        @Schema(description = "음료 칼로리") int calorie,
        @Schema(description = "음료 카테고리") DrinkCategory category
) {
}

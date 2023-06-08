package com.depromeet.drink.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record ModifyDrinkImageRequest(@Schema(description = "음료 이미지 URL") String imageUrl){
}

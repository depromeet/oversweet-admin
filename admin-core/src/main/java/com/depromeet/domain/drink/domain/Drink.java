package com.depromeet.domain.drink.domain;


import com.depromeet.common.exception.drink.DrinkImageUrlUpdateNotAllowedException;
import com.depromeet.domain.franchise.domain.Franchise;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Drink {
    private Long id;
    private String name;
    private Franchise franchise;
    private int size;
    private int sugar;
    private int calorie;
    private String imageUrl;
    private DrinkCategory category;
    private boolean isMinimum;

    @Builder
    public Drink(final Long id, final String name, final Franchise franchise, final int size, final int sugar, final int calorie, final String imageUrl, final DrinkCategory category, final boolean isMinimum) {
        this.id = id;
        this.name = name;
        this.franchise = franchise;
        this.size = size;
        this.sugar = sugar;
        this.calorie = calorie;
        this.imageUrl = imageUrl;
        this.category = category;
        this.isMinimum = isMinimum;
    }
    private void validateImageUrl(final String imageUrl) {
        if (!imageUrl.startsWith("https://oversweet.s3")) {
            throw new DrinkImageUrlUpdateNotAllowedException();
        }
    }

    public void setSugar(final int sugar) {
        this.sugar = sugar;
    }
}

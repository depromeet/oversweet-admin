package com.depromeet.domain.drink.domain;


import com.depromeet.common.exception.drink.DrinkCategoryNotAllowedException;
import com.depromeet.common.exception.drink.DrinkImageUrlUpdateNotAllowedException;
import com.depromeet.common.exception.drink.DrinkIntvalueNotAllowedException;
import com.depromeet.common.exception.drink.DrinkNameIsNotEmptyException;
import com.depromeet.domain.franchise.domain.Franchise;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;

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

    @Builder
    public Drink(final Long id, final String name, final Franchise franchise, final int size, final int sugar, final int calorie, final String imageUrl, final DrinkCategory category) {
        this.id = id;
        this.name = name;
        this.franchise = franchise;
        this.size = size;
        this.sugar = sugar;
        this.calorie = calorie;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    public void modifyImageUrl(final String imageUrl) {
        validateImageUrl(imageUrl);
        this.imageUrl = imageUrl;
    }

    public void modifyName(final String name) {
        validateName(name);
        this.name = name;
    }

    public void modifySize(final int size) {
        validateIntValue(size);
        this.size = size;
    }

    public void modifySugar(final int sugar) {
        validateIntValue(sugar);
        this.sugar = sugar;
    }

    public void modifyCalorie(final int calorie) {
        validateIntValue(calorie);
        this.calorie = calorie;
    }

    public void modifyCategory(final DrinkCategory category) {
        validateDrinkCategory(category);
        this.category = category;
    }

    private void validateDrinkCategory(final DrinkCategory category){
        if (!Arrays.asList(DrinkCategory.values()).contains(category)) {
            throw new DrinkCategoryNotAllowedException();
        }
    }

    private void validateIntValue(final int value){
        if (value <= 0){
            throw new DrinkIntvalueNotAllowedException();
        }
    }

    private void validateName(final String name){
        if (name.isEmpty()){
            throw new DrinkNameIsNotEmptyException();
        }
    }

    private void validateImageUrl(final String imageUrl) {
        if (!imageUrl.startsWith("https://oversweet.s3")) {
            throw new DrinkImageUrlUpdateNotAllowedException();
        }
    }

}

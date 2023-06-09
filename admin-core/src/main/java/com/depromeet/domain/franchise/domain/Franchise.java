package com.depromeet.domain.franchise.domain;

import com.depromeet.common.exception.franchise.FranchiseImageUrlUpdateNotAllowedException;
import com.depromeet.domain.drink.domain.Drink;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Franchise {
    private Long id;

    private String name;

    private String imageUrl;

    private List<Drink> drinks;

    @Builder
    public Franchise(final Long id, final String name, final String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public void modifyImageUrl(final String imageUrl) {
        validateImageUrl(imageUrl);
        this.imageUrl = imageUrl;
    }

    private void validateImageUrl(final String imageUrl) {
        if (!imageUrl.startsWith("https://oversweet.s3")) {
            throw new FranchiseImageUrlUpdateNotAllowedException();
        }
    }
}

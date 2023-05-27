package com.depromeet.domain.franchise.domain;

import com.depromeet.common.exception.franchise.FranchiseAlreadyExistException;
import com.depromeet.domain.drink.Drink;
import com.depromeet.domain.franchise.entity.FranchiseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

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

    public boolean isSameName(final String name) {
        return  Objects.equals(this.name, name);
    }

    public static FranchiseEntity toEntity(final Franchise franchise){
        return FranchiseEntity.builder()
                .name(franchise.name)
                .imageUrl(franchise.imageUrl)
                .build();
    }
}

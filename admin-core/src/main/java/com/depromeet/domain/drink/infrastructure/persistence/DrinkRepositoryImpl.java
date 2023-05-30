package com.depromeet.domain.drink.infrastructure.persistence;

import com.depromeet.common.exception.franchise.FranchiseNotFoundException;
import com.depromeet.domain.drink.DrinkRepository;
import com.depromeet.domain.drink.domain.Drink;
import com.depromeet.domain.drink.entity.DrinkEntity;
import com.depromeet.domain.drink.entity.DrinkEntityRepository;
import com.depromeet.domain.franchise.entity.FranchiseEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DrinkRepositoryImpl implements DrinkRepository {

    private final DrinkEntityRepository drinkEntityRepository;
    private final FranchiseEntityRepository franchiseEntityRepository;

    @Override
    public void save(final Drink drink) {
        final var findFranchiseEntity = franchiseEntityRepository.findById(drink.getFranchise().getId())
                .orElseThrow(FranchiseNotFoundException::new);

        final var drinkEntity = DrinkEntity.builder()
                .name(drink.getName())
                .imageUrl(drink.getImageUrl())
                .size(drink.getSize())
                .sugar(drink.getSugar())
                .category(drink.getCategory())
                .calorie(drink.getCalorie())
                .franchise(findFranchiseEntity)
                .build();

        drinkEntityRepository.save(drinkEntity);
    }
}

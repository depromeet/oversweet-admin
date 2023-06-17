package com.depromeet.domain.drink.infrastructure.persistence;

import com.depromeet.common.exception.drink.DrinkNotFoundException;
import com.depromeet.common.exception.franchise.FranchiseNotFoundException;
import com.depromeet.domain.drink.domain.Drink;
import com.depromeet.domain.drink.entity.DrinkEntity;
import com.depromeet.domain.drink.entity.DrinkEntityRepository;
import com.depromeet.domain.franchise.domain.Franchise;
import com.depromeet.domain.franchise.entity.FranchiseEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Page<Drink> findAllByFranchise(final Franchise franchise, final List<Integer> range) {
        franchiseEntityRepository.findById(franchise.getId())
                .orElseThrow(FranchiseNotFoundException::new);

        final var pageRequest = PageRequest.of(range.get(0), range.get(1));
        final var findDrinks = drinkEntityRepository.findAllByFranchiseId(franchise.getId(), pageRequest);

        return findDrinks.map(DrinkEntity::toDomain);

    }

    @Override
    public Optional<Drink> findByFranchise(final Franchise franchise, final String name) {
        franchiseEntityRepository.findById(franchise.getId())
                .orElseThrow(FranchiseNotFoundException::new);

        final var findDrinkEntity = drinkEntityRepository.findByFranchiseIdAndName(franchise.getId(), name);
        return findDrinkEntity.map(DrinkEntity::toDomain);
    }

    @Override
    public Drink findById(final Long id) {
        final var findDrinkEntity = drinkEntityRepository.findById(id)
                .orElseThrow(DrinkNotFoundException::new);
        return findDrinkEntity.toDomain();
    }

    @Override
    public void modifyImageUrl(final Drink drink) {
        final var findDrinkEntity = drinkEntityRepository.findById(drink.getId())
                .orElseThrow(DrinkNotFoundException::new);
        findDrinkEntity.modifyImageUrl(drink.getImageUrl());
    }

    @Override
    public void modify(final Drink drink) {
        final var findDrinkEntity = drinkEntityRepository.findById(drink.getId())
                .orElseThrow(DrinkNotFoundException::new);
        findDrinkEntity.modifyCalorie(drink.getCalorie());
        findDrinkEntity.modifyName(drink.getName());
        findDrinkEntity.modifySize(drink.getSize());
        findDrinkEntity.modifySugar(drink.getSugar());
        findDrinkEntity.modifyCategory(drink.getCategory());
    }
}

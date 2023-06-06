package com.depromeet.domain.drink.infrastructure.persistence;

import com.depromeet.common.exception.drink.DrinkAlreadyExistException;
import com.depromeet.common.exception.franchise.FranchiseNotFoundException;
import com.depromeet.domain.drink.DrinkRepository;
import com.depromeet.domain.drink.domain.Drink;
import com.depromeet.domain.drink.entity.DrinkEntity;
import com.depromeet.domain.drink.entity.DrinkEntityRepository;
import com.depromeet.domain.franchise.domain.Franchise;
import com.depromeet.domain.franchise.entity.FranchiseEntityRepository;
import lombok.RequiredArgsConstructor;
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
    public List<Drink> findAllByFranchise(final Franchise franchise, final List<Integer> range) {
        franchiseEntityRepository.findById(franchise.getId())
                .orElseThrow(FranchiseNotFoundException::new);

        final var pageRequest = PageRequest.of(range.get(0), range.get(1));
        final List<DrinkEntity> findDrinkEntities = drinkEntityRepository.findAllByFranchiseId(franchise.getId(), pageRequest);

        return findDrinkEntities.stream()
                .map(DrinkEntity::toDomain)
                .toList();

    }

    @Override
    public Optional<Drink> findByFranchise(final Franchise franchise, final String name) {
        franchiseEntityRepository.findById(franchise.getId())
                .orElseThrow(FranchiseNotFoundException::new);

        final var findDrinkEntity = drinkEntityRepository.findByFranchiseIdAndName(franchise.getId(), name);
        return findDrinkEntity.map(DrinkEntity::toDomain);
    }
}
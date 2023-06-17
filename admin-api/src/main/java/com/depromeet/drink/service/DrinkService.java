package com.depromeet.drink.service;

import com.depromeet.common.PaginatedResponse;
import com.depromeet.common.exception.drink.DrinkAlreadyExistException;
import com.depromeet.domain.drink.infrastructure.persistence.DrinkRepository;
import com.depromeet.domain.drink.domain.Drink;
import com.depromeet.domain.franchise.infrastructure.persistence.FranchiseRepository;
import com.depromeet.domain.franchise.domain.Franchise;
import com.depromeet.drink.dto.reponse.DrinkResponse;
import com.depromeet.drink.dto.request.CreateDrinkRequest;
import com.depromeet.drink.dto.request.ModifyDrinkImageRequest;
import com.depromeet.drink.dto.request.ModifyDrinkRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DrinkService {
    private final FranchiseRepository franchiseRepository;
    private final DrinkRepository drinkRepository;

    @Transactional
    public void saveDrink(final Long franchiseId, final CreateDrinkRequest request) {
        final var findFranchise = franchiseRepository.findById(franchiseId);

        validateDuplicatedDrink(findFranchise, request.name());

        final var drink = Drink.builder()
                .name(request.name())
                .size(request.size())
                .sugar(request.sugar())
                .calorie(request.calorie())
                .imageUrl(request.imageUrl())
                .category(request.category())
                .franchise(findFranchise)
                .build();

        drinkRepository.save(drink);
    }

    @Transactional(readOnly = true)
    public PaginatedResponse<List<DrinkResponse>> findAllByFranchise(final Long franchiseId, final List<Integer> range) {
        final var findFranchise = franchiseRepository.findById(franchiseId);
        final var findDrinksByPage = drinkRepository.findAllByFranchise(findFranchise, range);

        final var response = findDrinksByPage.getContent().stream()
                .map(drink -> DrinkResponse.builder()
                        .id(drink.getId())
                        .name(drink.getName())
                        .franchiseName(drink.getFranchise().getName())
                        .size(drink.getSize())
                        .sugar(drink.getSugar())
                        .calorie(drink.getCalorie())
                        .imageUrl(drink.getImageUrl())
                        .category(drink.getCategory())
                        .build())
                .toList();

        return PaginatedResponse.of(findDrinksByPage.hasNext(), findDrinksByPage.getTotalPages(), (int) findDrinksByPage.getTotalElements(), response);

    }

    @Transactional
    public void modifyDrinkImage(final Long id, final ModifyDrinkImageRequest request){
        final var findDrink = drinkRepository.findById(id);
        findDrink.modifyImageUrl(request.imageUrl());
        drinkRepository.modifyImageUrl(findDrink);
    }

    @Transactional
    public void modifyDrink(final Long id, final ModifyDrinkRequest request) {
        final var findDrink = drinkRepository.findById(id);

        findDrink.modifyCalorie(request.calorie());
        findDrink.modifyName(request.name());
        findDrink.modifySize(request.size());
        findDrink.modifySugar(request.sugar());
        findDrink.modifyCategory(request.category());

        drinkRepository.modify(findDrink);
    }

    private void validateDuplicatedDrink(final Franchise franchise, final String name) {
        final var findDrink = drinkRepository.findByFranchise(franchise, name);
        if (findDrink.isPresent()) {
            throw new DrinkAlreadyExistException();
        }
    }
}

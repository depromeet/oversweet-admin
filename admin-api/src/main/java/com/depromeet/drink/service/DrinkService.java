package com.depromeet.drink.service;

import com.depromeet.domain.drink.DrinkRepository;
import com.depromeet.domain.drink.domain.Drink;
import com.depromeet.domain.franchise.FranchiseRepository;
import com.depromeet.drink.controller.dto.request.CreateDrinkRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class DrinkService {
    private final FranchiseRepository franchiseRepository;
    private final DrinkRepository drinkRepository;
    public void saveDrink(final Long franchiseId, final CreateDrinkRequest request) {
        final var findFranchise = franchiseRepository.findById(franchiseId);

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
}

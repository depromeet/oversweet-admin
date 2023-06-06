package com.depromeet.drink.service;

import com.depromeet.common.exception.drink.DrinkAlreadyExistException;
import com.depromeet.domain.drink.DrinkRepository;
import com.depromeet.domain.drink.domain.Drink;
import com.depromeet.domain.franchise.FranchiseRepository;
import com.depromeet.domain.franchise.domain.Franchise;
import com.depromeet.drink.dto.reponse.DrinkResponse;
import com.depromeet.drink.dto.request.CreateDrinkRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DrinkService {
    private final FranchiseRepository franchiseRepository;
    private final DrinkRepository drinkRepository;
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

    public List<DrinkResponse> findAllByFranchise(final Long franchiseId, final List<Integer> range) {
        final var findFranchise = franchiseRepository.findById(franchiseId);
        final var findDrinks = drinkRepository.findAllByFranchise(findFranchise, range);

        return findDrinks.stream()
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

    }
    private void validateDuplicatedDrink(final Franchise franchise, final String name) {
        final var findDrink = drinkRepository.findByFranchise(franchise, name);
        if (findDrink.isPresent()) {
            throw new DrinkAlreadyExistException();
        }
    }
}

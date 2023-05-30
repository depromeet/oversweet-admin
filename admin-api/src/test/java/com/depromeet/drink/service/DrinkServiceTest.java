package com.depromeet.drink.service;


import com.depromeet.domain.drink.DrinkRepository;
import com.depromeet.domain.drink.domain.Drink;
import com.depromeet.domain.drink.domain.DrinkCategory;
import com.depromeet.domain.franchise.FranchiseRepository;
import com.depromeet.domain.franchise.domain.Franchise;
import com.depromeet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.drink.controller.dto.request.CreateDrinkRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@DisplayName("음료 서비스 테스트")
@MockitoSettings
public class DrinkServiceTest {

    @Mock
    private DrinkRepository drinkRepository;

    @Mock
    private FranchiseRepository franchiseRepository;
    
    @InjectMocks
    DrinkService drinkService;

    private Franchise franchise;
    private FranchiseEntity franchiseEntity;

    @BeforeEach
    void setUp() {
        franchiseEntity = FranchiseEntity.builder()
                .id(1L)
                .name("test")
                .imageUrl("test")
                .build();
        franchise = franchiseEntity.toDomain();
    }

    @Test
    @DisplayName("프랜차이즈 - 성공")
    void saveFranchise() {
        // given
        var request = createDrinkRequest();
        var drink = drink(1L);
        Mockito.doNothing().when(drinkRepository).save(Mockito.any(Drink.class));

        // when
        drinkService.saveDrink(franchise.getId(), request);

        // then
        then(drinkRepository)
                .should(times(1))
                .save(any());
    }

    private Drink drink(Long id) {
        return Drink.builder()
                .id(id)
                .name("test")
                .franchise(franchise)
                .sugar(1)
                .imageUrl("test")
                .calorie(1)
                .size(355)
                .category(DrinkCategory.AMERICANO)
                .build();
    }

    private CreateDrinkRequest createDrinkRequest() {
        return CreateDrinkRequest.builder()
                .name("test")
                .sugar(1)
                .imageUrl("test")
                .calorie(1)
                .size(355)
                .category(DrinkCategory.AMERICANO)
                .build();

    }
}

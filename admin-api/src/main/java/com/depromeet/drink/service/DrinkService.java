package com.depromeet.drink.service;

import com.depromeet.domain.drink.DrinkRepository;
import com.depromeet.domain.drink.domain.Drink;
import com.depromeet.domain.drink.domain.DrinkCategory;
import com.depromeet.domain.drink.entity.DrinkEntityRepository;
import com.depromeet.domain.franchise.FranchiseRepository;
import com.depromeet.drink.controller.dto.request.CreateDrinkRequest;
import com.depromeet.drink.external.DrinkApiClient;
import com.depromeet.drink.external.dto.resposne.DrinkResponse;
import com.depromeet.drink.external.dto.resposne.Row;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DrinkService {
    private final DrinkApiClient drinkApiClient;
    private final FranchiseRepository franchiseRepository;
    private final DrinkRepository drinkRepository;
    private final DrinkEntityRepository drinkEntityRepository;

    public DrinkResponse GetDrinkDbData(final Long franchiseId, final String makerName){
        final var findFranchise = franchiseRepository.findById(franchiseId);

        final DrinkResponse drinkResponse = drinkApiClient.getDrinkDBData(makerName);
//        String[] arr = {"플랫치노"};
//        String[] arr = {"콜드브루"};
//        String[] arr = {"에스프레소"};
//        String[] arr = {"캐모마일", "티", "얼그레이", "아이스티", "루이보스"};
//
//
        final List<Row> latteRows = new ArrayList<>();
        for (final var row : drinkResponse.getI2790().getRow()) {
//            for (String item : arr) {
//                if (row.getDescKor().contains(item)) {
                    latteRows.add(row);
//                    break; // 해당 요소가 포함된 경우에만 추가하고 루프를 종료합니다.
//                }
//            }
        }

        drinkResponse.getI2790().setRow(latteRows);

        for (Row row : drinkResponse.getI2790().getRow()) {
            if (drinkEntityRepository.findByNameAndFranchiseId(row.getDescKor(), franchiseId).isPresent()) {
                continue;
            }
            if (row.getSugars() == ""){
                row.setSugars("1");
            }
            final var drink = Drink.builder()
                    .name(row.getDescKor())
                    .size((int) Double.parseDouble(row.getSize()))
                    .sugar((int) Double.parseDouble(row.getSugars()))
                    .calorie((int) Double.parseDouble(row.getKcal()))
                    .imageUrl(null)
                    .category(DrinkCategory.AMERICANO)
                    .franchise(findFranchise)
                    .isMinimum(true)
                    .build();
            drinkRepository.save(drink);
        }
        return drinkResponse;
    }

//    public void saveDrink(final Long franchiseId, final CreateDrinkRequest request) {
//        final var findFranchise = franchiseRepository.findById(franchiseId);
//
//        final var drink = Drink.builder()
//                .name(request.name())
//                .size(request.size())
//                .sugar(request.sugar())
//                .calorie(request.calorie())
//                .imageUrl(request.imageUrl())
//                .category(request.category())
//                .franchise(findFranchise)
//                .build();
//
//        drinkRepository.save(drink);
//    }
}

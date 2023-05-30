package com.depromeet.drink.controller;


import com.depromeet.common.response.ApiMessageResponse;
import com.depromeet.drink.controller.dto.request.CreateDrinkRequest;
import com.depromeet.drink.service.DrinkService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/drinks")
public class DrinkController {

    private final DrinkService drinkService;

    @Operation(summary = "음료 생성", description = "음료 생성 API입니다.")
    @PostMapping("/{franchiseId}")
    public ResponseEntity<ApiMessageResponse> drinkSave(@PathVariable final Long franchiseId,
                                                        @RequestBody final CreateDrinkRequest request) {
        drinkService.saveDrink(franchiseId, request);
        return ResponseEntity.ok()
                .body(ApiMessageResponse.of(HttpStatus.OK, "음료 저장 성공"));
    }
}

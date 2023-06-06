package com.depromeet.drink.controller;


import com.depromeet.common.response.ApiCommonResponse;
import com.depromeet.common.response.ApiMessageResponse;
import com.depromeet.drink.dto.reponse.DrinkResponse;
import com.depromeet.drink.dto.request.CreateDrinkRequest;
import com.depromeet.drink.service.DrinkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/drinks")
public class DrinkController {

    private final DrinkService drinkService;

    @Operation(summary = "음료 생성", description = "음료 생성 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공적으로 음료가 생성되었습니다."),
            @ApiResponse(responseCode = "400", description = "존재 하지 않는 프랜차이즈입니다."),
    })
    @PostMapping("/{franchiseId}")
    public ResponseEntity<ApiMessageResponse> drinkSave(@PathVariable final Long franchiseId,
                                                        @RequestBody final CreateDrinkRequest request) {
        drinkService.saveDrink(franchiseId, request);
        return ResponseEntity.ok()
                .body(ApiMessageResponse.of(HttpStatus.OK, "음료 저장 성공"));
    }

    @Operation(summary = "해당 프랜차이즈의 음료 목록을 조회합니다.", description = "음료 조회 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공적으로 음료가 목록이 조회되었습니다."),
            @ApiResponse(responseCode = "400", description = "존재 하지 않는 프랜차이즈입니다."),
    })
    @GetMapping("/{franchiseId}")
    public ResponseEntity<ApiCommonResponse<List<DrinkResponse>>> drinks(@PathVariable final Long franchiseId,
                                                                         @RequestParam(name = "range") final List<Integer> range){
        final var drinks = drinkService.findAllByFranchise(franchiseId, range);
        return ResponseEntity.ok()
                .body(ApiCommonResponse.of(HttpStatus.OK, "해당 프랜차이즈의 음료 목록 조회 성공", drinks));
    }
}

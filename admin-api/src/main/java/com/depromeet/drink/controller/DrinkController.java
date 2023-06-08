package com.depromeet.drink.controller;


import com.depromeet.common.DrinkApiResponseDescription;
import com.depromeet.common.FranchiseApiResponseDescription;
import com.depromeet.common.PaginatedResponse;
import com.depromeet.common.exception.ErrorResponse;
import com.depromeet.common.response.ApiCommonResponse;
import com.depromeet.common.response.ApiMessageResponse;
import com.depromeet.drink.dto.reponse.DrinkResponse;
import com.depromeet.drink.dto.request.CreateDrinkRequest;
import com.depromeet.drink.dto.request.ModifyDrinkImageRequest;
import com.depromeet.drink.service.DrinkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.depromeet.common.DrinkApiResponseDescription.NOT_ALLOWED_DRINK_IMAGE_URL;
import static com.depromeet.common.DrinkApiResponseDescription.NOT_FOUND_DRINK;
import static com.depromeet.common.DrinkApiResponseDescription.SUCCESS_DRINKS_FETCHED;
import static com.depromeet.common.DrinkApiResponseDescription.SUCCESS_DRINK_IMAGE_URL_MODIFIED;
import static com.depromeet.common.DrinkApiResponseDescription.SUCCESS_DRINK_SAVED;
import static com.depromeet.common.FranchiseApiResponseDescription.NOT_FOUND_FRANCHISE;

@Tag(name = "음료", description = "음료 관련 API입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/drinks")
public class DrinkController {

    private final DrinkService drinkService;

    @Operation(summary = "음료 생성", description = "음료 생성 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = SUCCESS_DRINK_SAVED),
            @ApiResponse(responseCode = "400", description = NOT_FOUND_FRANCHISE, content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PostMapping("/{franchiseId}")
    public ResponseEntity<ApiMessageResponse> drinkSave(@Parameter(name = "franchiseId", description = "해당 프랜차이즈 Id", required = true) @PathVariable final Long franchiseId,
                                                        @RequestBody final CreateDrinkRequest request) {
        drinkService.saveDrink(franchiseId, request);
        return ResponseEntity.ok()
                .body(ApiMessageResponse.of(HttpStatus.OK, "음료 저장 성공"));
    }

    @Operation(summary = "해당 프랜차이즈의 음료 목록을 조회합니다. - 페이지네이션 기능 포함.", description = "음료 조회 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = SUCCESS_DRINKS_FETCHED),
            @ApiResponse(responseCode = "400", description = NOT_FOUND_FRANCHISE, content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/{franchiseId}")
    public ResponseEntity<ApiCommonResponse<PaginatedResponse<List<DrinkResponse>>>> drinks(@Parameter(name = "franchiseId", description = "해당 프랜차이즈 Id", required = true) @PathVariable final Long franchiseId,
                                                                                            @Parameter(description = "조회할 페이지네이션 Range - (시작 페이지, 조회할 데이터 개수)", examples = @ExampleObject(name = "예시 페이징", value = "[1, 10]"), required = true)
                                                                                            @RequestParam(name = "range") final List<Integer> range){
        final var drinks = drinkService.findAllByFranchise(franchiseId, range);
        return ResponseEntity.ok()
                .body(ApiCommonResponse.of(HttpStatus.OK, "해당 프랜차이즈의 음료 목록 조회 성공", drinks));
    }

    @Operation(summary = "해당 음료의 이미지 Url을 수정합니다.", description = "음료 이미지 수정 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = SUCCESS_DRINK_IMAGE_URL_MODIFIED),
            @ApiResponse(responseCode = "400", description = NOT_FOUND_DRINK, content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = NOT_ALLOWED_DRINK_IMAGE_URL, content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PatchMapping("/{id}")
    public ResponseEntity<ApiMessageResponse> drinkModifyImage(@Parameter(name = "drinkId", description = "해당 음료 Id", required = true) @PathVariable final Long id,
                                                               @RequestBody final ModifyDrinkImageRequest request){
        drinkService.modifyDrinkImage(id, request);
        return ResponseEntity.ok()
                .body(ApiMessageResponse.of(HttpStatus.OK, "음료 이미지 수정 성공"));
    }
}

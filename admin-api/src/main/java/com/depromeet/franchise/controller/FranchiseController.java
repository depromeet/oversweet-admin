package com.depromeet.franchise.controller;


import com.depromeet.common.exception.ErrorResponse;
import com.depromeet.common.response.ApiCommonResponse;
import com.depromeet.common.response.ApiMessageResponse;
import com.depromeet.franchise.dto.request.CreateFranchiseRequest;
import com.depromeet.franchise.dto.request.ModifyFranchiseImageRequest;
import com.depromeet.franchise.dto.response.FranchiseResponse;
import com.depromeet.franchise.service.FranchiseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.depromeet.common.FranchiseApiResponseDescription.ALREADY_FRANCHISE;
import static com.depromeet.common.FranchiseApiResponseDescription.NOT_ALLOWED_FRANCHISE_IMAGE_URL;
import static com.depromeet.common.FranchiseApiResponseDescription.NOT_FOUND_FRANCHISE;
import static com.depromeet.common.FranchiseApiResponseDescription.SUCCESS_FRANCHISES_FETCHED;
import static com.depromeet.common.FranchiseApiResponseDescription.SUCCESS_FRANCHISE_IMAGE_URL_MODIFIED;
import static com.depromeet.common.FranchiseApiResponseDescription.SUCCESS_FRANCHISE_SAVED;

@Tag(name = "프랜차이즈", description = "프랜차이즈 관련 API입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/franchises")
public class FranchiseController {

    private final FranchiseService franchiseService;

    @Operation(summary = "프랜차이즈 생성", description = "프랜차이즈 생성 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = SUCCESS_FRANCHISE_SAVED),
            @ApiResponse(responseCode = "409", description = ALREADY_FRANCHISE, content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<ApiMessageResponse> franchiseSave(@RequestBody final CreateFranchiseRequest request) {
        franchiseService.saveFranchise(request);
        return ResponseEntity.ok()
                .body(ApiMessageResponse.of(HttpStatus.OK, "프랜차이즈 저장 성공"));
    }

    @Operation(summary = "프랜차이즈 이미지 변경", description = "프랜차이즈 이미지 수정 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = SUCCESS_FRANCHISE_IMAGE_URL_MODIFIED),
            @ApiResponse(responseCode = "400", description = NOT_FOUND_FRANCHISE, content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = NOT_ALLOWED_FRANCHISE_IMAGE_URL, content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PatchMapping("/{id}")
    public ResponseEntity<ApiMessageResponse> franchiseModifyImage(@Parameter(name = "franchiseId", description = "해당 프랜차이즈 Id", required = true) @PathVariable(name = "id") final Long id,
                                                                   @RequestBody final ModifyFranchiseImageRequest request){
        franchiseService.modifyFranchiseImage(id, request);
        return ResponseEntity.ok()
                .body(ApiMessageResponse.of(HttpStatus.OK, "프랜차이즈 이미지 수정 성공"));
    }

    @Operation(summary = "프랜차이즈 전체 목록 조회", description = "프랜차이즈 전체 목록 조회 API입니다.")
    @ApiResponses(@ApiResponse(responseCode = "200", description = SUCCESS_FRANCHISES_FETCHED))
    @GetMapping
    public ResponseEntity<ApiCommonResponse<List<FranchiseResponse>>> franchises(){
        final var franchises = franchiseService.findAllFranchise();
        return ResponseEntity.ok()
                .body(ApiCommonResponse.of(HttpStatus.OK, "프랜차이즈 전체 목록 조회 성공", franchises));
    }
}

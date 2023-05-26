package com.depromeet.franchise.controller;


import com.depromeet.common.response.ApiCommonResponse;
import com.depromeet.franchise.dto.request.CreateFranchiseRequest;
import com.depromeet.franchise.service.FranchiseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/franchise")
public class FranchiseController {

    private final FranchiseService franchiseService;

    @Operation(summary = "프랜차이즈 생성", description = "프랜차이즈 생성 API입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 프랜차이즈가 생성되었습니다."),
            @ApiResponse(responseCode = "409", description = "이미 존재하는 프랜차이즈입니다.")
    })
    @PostMapping
    public ResponseEntity<ApiCommonResponse<Void>> franchiseSave(@RequestBody final CreateFranchiseRequest request) {
        franchiseService.saverFranchise(request);
        return ResponseEntity.ok()
                .body(ApiCommonResponse.of(HttpStatus.OK, "프랜차이즈 저장 성공", null));
    }

}

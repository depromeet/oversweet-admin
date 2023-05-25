package com.depromeet;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "헬스체크", description = "헬스 체크 관련 API입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HealthController {

    @Operation(summary = "헬스체크 메서드", description = "헬스체크 메서드 입니다.")
    @GetMapping("/health")
    public String HealthCheck(){
        return "hello";
    }
}



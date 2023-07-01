package com.depromeet.drink.external;

import com.depromeet.drink.external.dto.resposne.DrinkResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Configuration
@FeignClient(name = "DrinkApiClient", url = "http://openapi.foodsafetykorea.go.kr/api/82f7156e7d964e8d8dd3/I2790/json/1/300")
public interface DrinkApiClient {
    @GetMapping("/MAKER_NAME={MAKER_NAME}")
    DrinkResponse getDrinkDBData(@PathVariable("MAKER_NAME") String makerName);
}

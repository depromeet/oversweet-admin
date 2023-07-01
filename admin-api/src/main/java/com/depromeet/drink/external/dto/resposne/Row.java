package com.depromeet.drink.external.dto.resposne;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Row {
    @JsonProperty("NUM")
    public String num;
    @JsonProperty("FOOD_CD")
    public String foodCd;
    @JsonProperty("MAKER_NAME")
    public String makerName;
    @JsonProperty("DESC_KOR")
    public String descKor;

    @JsonProperty("SERVING_SIZE")
    public String size;
    @JsonProperty("NUTR_CONT1")
    public String kcal;
    @JsonProperty("NUTR_CONT5")
    public String sugars;
}

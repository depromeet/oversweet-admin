package com.depromeet.drink.external.dto.resposne;

import lombok.Getter;

import java.util.List;

@Getter
public class I2790 {
    public String total_count;
    public List<Row> row;

    public void setRow(final List<Row> row) {
        this.row = row;
    }
}

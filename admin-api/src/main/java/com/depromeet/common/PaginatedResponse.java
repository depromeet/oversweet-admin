package com.depromeet.common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PaginatedResponse<T> {
    private boolean hasNext;
    private int totalPage;
    private int totalDataCount;
    private T data;

    private PaginatedResponse(final boolean hasNext, final int totalPage, final int totalDataCount, final T data) {
        this.hasNext = hasNext;
        this.totalPage = totalPage;
        this.totalDataCount = totalDataCount;
        this.data = data;
    }
    public static <T> PaginatedResponse<T> of(final boolean hasNext, final int totalPage, final int totalDataCount, final T data) {
        return new PaginatedResponse<>(hasNext, totalPage, totalDataCount, data);
    }
}

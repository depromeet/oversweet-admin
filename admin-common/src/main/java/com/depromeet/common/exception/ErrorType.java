package com.depromeet.common.exception;


import com.depromeet.common.exception.drink.DrinkAlreadyExistException;
import com.depromeet.common.exception.drink.DrinkCategoryNotAllowedException;
import com.depromeet.common.exception.drink.DrinkImageUrlUpdateNotAllowedException;
import com.depromeet.common.exception.drink.DrinkIntvalueNotAllowedException;
import com.depromeet.common.exception.drink.DrinkNameIsNotEmptyException;
import com.depromeet.common.exception.drink.DrinkNotFoundException;
import com.depromeet.common.exception.franchise.FranchiseAlreadyExistException;
import com.depromeet.common.exception.franchise.FranchiseImageUrlUpdateNotAllowedException;
import com.depromeet.common.exception.franchise.FranchiseNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum ErrorType {
    F001("F001", "이미 존재 하는 프랜차이즈입니다.", FranchiseAlreadyExistException.class),
    F002("F002", "존재 하지 않는 프랜차이즈입니다.", FranchiseNotFoundException.class),
    F003("F003", "정책에 맞지 않는 ImageUrl입니다", FranchiseImageUrlUpdateNotAllowedException.class),
    D001("D001", "해당 프랜차이즈에 이미 존재 하는 음료입니다.", DrinkAlreadyExistException.class),
    D002("D002", "존재 하지 않는 음료입니다.", DrinkNotFoundException.class),
    D003("D003", "정책에 맞지 않는 ImageUrl입니다.", DrinkImageUrlUpdateNotAllowedException.class),
    D004("D004", "음료의 이름을 설정해야 합니다.", DrinkNameIsNotEmptyException.class),
    D005("D005", "음료의 사이즈, 칼로리, 당 정보는 0 이하일 수 없습니다.", DrinkIntvalueNotAllowedException.class),
    D006("D006", "음료의 카테고리를 확인해주세요.", DrinkCategoryNotAllowedException.class),

    NOTUSED_EXCEPTION("X001", "정의되지 않은 에러", UndefinedException.class),

    ;

    private final String code;
    private final String message;
    private final Class<? extends OversweetException> classType;

    private static final Map<Class<? extends OversweetException>, ErrorType> codeMap = new HashMap<>();

    static {
        Arrays.stream(values())
                .filter(ErrorType::isNotExternalException)
                .forEach(errorType -> codeMap.put(errorType.classType, errorType));
    }

    public static ErrorType of(Class<? extends OversweetException> classType) {
        if (classType.equals(ExternalException.class)) {
            throw new UnsupportedOperationException("클래스로 ErrorType을 생성할 수 없는 예외입니다.");
        }
        return codeMap.getOrDefault(classType, ErrorType.NOTUSED_EXCEPTION);
    }

    public static ErrorType of(String code) {
        return Arrays.stream(values())
                .parallel()
                .filter(errorType -> errorType.hasSameCode(code))
                .findAny()
                .orElse(NOTUSED_EXCEPTION);
    }


    public boolean isNotExternalException() {
        return !classType.equals(ExternalException.class);
    }

    public boolean hasSameCode(String code) {
        return Objects.equals(this.code, code);
    }
}

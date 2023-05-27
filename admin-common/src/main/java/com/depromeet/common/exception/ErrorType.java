package com.depromeet.common.exception;


import com.depromeet.common.exception.franchise.FranchiseAlreadyExistException;
import com.depromeet.common.exception.franchise.FranchiseNotFoundException;
import com.depromeet.common.exception.http.ConflictException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum ErrorType {
    DUPLICATE_EXCEPTION("F001", "이미 존재 하는 프랜차이즈입니다.", FranchiseAlreadyExistException.class),
    NOTFOUND_FRANCHISE("F002", "존재 하지 않는 프랜차이즈입니다.", FranchiseNotFoundException.class),
    NOTUSED_EXCEPTION("X001", "정의되지 않은 에러", UndefinedException.class)
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

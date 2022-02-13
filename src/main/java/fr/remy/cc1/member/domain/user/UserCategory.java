package fr.remy.cc1.member.domain.user;

import fr.remy.cc1.kernel.error.ExceptionsDictionary;
import fr.remy.cc1.kernel.error.UserCategoryValidatorException;
import fr.remy.cc1.kernel.error.ValidationException;

import java.util.Arrays;

public enum UserCategory {
    TRADESMAN("tradesman"), CONTRACTOR("contractor");

    private final String code;

    UserCategory(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static UserCategory ofCode(String code) throws ValidationException {
        return Arrays.stream(UserCategory.values())
                .filter(trade -> trade.getCode().equals(code))
                .findFirst()
                .orElseThrow(
                        () -> new UserCategoryValidatorException(ExceptionsDictionary.USER_CATEGORY_NOT_VALID.getErrorCode(), ExceptionsDictionary.USER_CATEGORY_NOT_VALID.getMessage())
                );
    }
}

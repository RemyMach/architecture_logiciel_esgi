package fr.remy.cc1.member.domain.user;

import fr.remy.cc1.kernel.EnumValidator;
import fr.remy.cc1.kernel.Validator;
import fr.remy.cc1.kernel.error.ExceptionsDictionary;
import fr.remy.cc1.kernel.error.UserCategoryValidatorException;
import fr.remy.cc1.kernel.error.ValidationException;

public final class UserCategoryValidator implements Validator<String> {

    private static final UserCategoryValidator INSTANCE = new UserCategoryValidator();

    private UserCategoryValidator() { }

    public static UserCategoryValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean test(String categoryAspirant) throws ValidationException {
        EnumValidator<UserCategory> enumValidator = new EnumValidator(UserCategory.class);
        if(enumValidator.test(categoryAspirant)) {
            return true;
        }
        throw new UserCategoryValidatorException(ExceptionsDictionary.USER_CATEGORY_NOT_VALID.getErrorCode(), ExceptionsDictionary.USER_CATEGORY_NOT_VALID.getMessage());
    }
}

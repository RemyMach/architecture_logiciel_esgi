package fr.remy.cc1.domain.user;

import fr.remy.cc1.kernel.error.ExceptionsDictionary;
import fr.remy.cc1.kernel.error.UserCategoryValidatorException;
import fr.remy.cc1.kernel.error.ValidationException;

public class UserCategoryCreator {

        public static UserCategory getValueOf(String aspirant) throws ValidationException {
            if(!UserCategoryValidator.getInstance().test(aspirant)) {
                throw new UserCategoryValidatorException(ExceptionsDictionary.USER_CATEGORY_NOT_VALID.getErrorCode(), ExceptionsDictionary.USER_CATEGORY_NOT_VALID.getMessage());
            }

            return UserCategory.valueOf(aspirant);
        }
}

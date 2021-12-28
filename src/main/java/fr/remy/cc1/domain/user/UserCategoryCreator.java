package fr.remy.cc1.domain.user;

import fr.remy.cc1.kernel.error.ExceptionsDictionary;
import fr.remy.cc1.kernel.error.ValidationException;

public class UserCategoryCreator {

        private final ValidationException validationException = ExceptionsDictionary.USER_CATEGORY_NOT_VALID;

        private static final UserCategoryCreator INSTANCE = new UserCategoryCreator();


        public static UserCategoryCreator getInstance() {
        return INSTANCE;
    }

        public UserCategory getValueOf(String aspirant) {

            if(!UserCategoryValidator.getInstance().test(aspirant)) {
                throw new IllegalArgumentException(validationException);
            }

            return UserCategory.valueOf(aspirant);
        }

        public ValidationException getValidationException() {
            return validationException;
        }
}

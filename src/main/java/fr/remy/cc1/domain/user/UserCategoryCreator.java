package fr.remy.cc1.domain.user;

import fr.remy.cc1.kernel.error.ExceptionsDictionary;
import fr.remy.cc1.kernel.error.ValidationException;

public class UserCategoryCreator {

        private final ValidationException validationException = ExceptionsDictionary.USER_CATEGORY_NOT_VALID;

        private static final UserCategoryCreator INSTANCE = new UserCategoryCreator();


        public static UserCategoryCreator getInstance() {
        return INSTANCE;
    }

        public UserCategory getValueOf(String aspirant) throws ValidationException {

            if(UserCategoryValidator.getInstance().test(aspirant)) {
                return UserCategory.valueOf(aspirant);
            }

            return null;
        }

        public ValidationException getValidationException() {
            return validationException;
        }
}

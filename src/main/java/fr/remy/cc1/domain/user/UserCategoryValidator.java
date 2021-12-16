package fr.remy.cc1.domain.user;

import fr.remy.cc1.kernel.EnumValidator;
import fr.remy.cc1.kernel.Validator;

public class UserCategoryValidator implements Validator<String> {

    private static final UserCategoryValidator INSTANCE = new UserCategoryValidator();

    private UserCategoryValidator() { }

    public static UserCategoryValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean test(String categoryAspirant) {
        EnumValidator<UserCategory> enumValidator = new EnumValidator(UserCategory.class);
        return enumValidator.test(categoryAspirant);
    }
}

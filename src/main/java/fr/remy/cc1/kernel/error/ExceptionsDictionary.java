package fr.remy.cc1.kernel.error;

public class ExceptionsDictionary {
    public static final ValidationException EMAIL_NOT_VALID = new ValidationException("email_not_valid", "the email is not valid");
    public static final ValidationException PASSWORD_NOT_VALID = new ValidationException("password_not_valid", "the password is not valid");

    public static final ValidationException CURRENCY_NOT_PRESENT = new ValidationException("currency_not_supported", "the currency is not supported by the application");

    public static final ValidationException PAYMENT_NOT_PRESENT = new ValidationException("payment_method_not_supported", "the payment method is not supported by the application");

    public static final ValidationException USER_CATEGORY_NOT_VALID = new ValidationException("user_category_not_valid", "the userCategory is not valid");

    public static final ValidationException CARD_PAYMENT_VALIDATION_ERROR = new ValidationException("card_payment_process_check", "the process check has fail");

    public static final ValidationException INVALID_PROJECT_NAME = new ValidationException("invalid_project_name", "invalid project name");
    public static final ValidationException INVALID_PROJECT_DESCRIPTION = new ValidationException("invalid_project_description", "invalid project description");
}

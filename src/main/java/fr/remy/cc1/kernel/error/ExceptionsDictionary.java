package fr.remy.cc1.kernel.error;

public class ExceptionsDictionary {
    public static final ValidationException EMAIL_NOT_VALID = new ValidationException("email_not_valid", "the email is not valid");
    public static final ValidationException PASSWORD_NOT_VALID = new ValidationException("password_not_valid", "the password is not valid");

    public static final ValidationException CURRENCY_NOT_PRESENT = new ValidationException("currency_not_supported", "the currency is not supported by the application");

    public static final ValidationException PAYMENT_NOT_PRESENT = new ValidationException("payment_method_not_supported", "the payment method is not supported by the application");

    public static final ValidationException USER_CATEGORY_NOT_VALID = new ValidationException("user_category_not_valid", "the userCategory is not valid");

    public static final ValidationException CARD_PAYMENT_VALIDATION_ERROR = new ValidationException("card_payment_process_check", "the process check has fail");
    //Project
    public static final ValidationException INVALID_PROJECT_ID = new ValidationException("invalid_project_id", "the provided project id does not match any known project");
    public static final ValidationException INVALID_PROJECT_NAME = new ValidationException("invalid_project_name", "invalid project name");
    public static final ValidationException INVALID_PROJECT_DESCRIPTION = new ValidationException("invalid_project_description", "invalid project description");
    //ProjectRequirements
    public static final ValidationException PROJECT_REQUIREMENTS_ALREADY_EXISTS = new ValidationException("project_requirements_already_exists", "project requirements already exists for given project id");
    //Location
    public static final ValidationException GEOCODING_ERROR = new ValidationException("geocoding_error", "could not encoding address to valid lat lng position");
    //Duration
    public static final ValidationException UNRECOGNIZED_DURATION_UNIT = new ValidationException("unrecognized_duration_unit", "the provided code does not match any known duration unit");

    public static final ValidationException SIREN_VALIDATION_ERROR = new ValidationException("siren_validation_error", "the siren number is not valid");

    public static final ValidationException TRADES_VALIDATION_ERROR = new ValidationException("trades_validation_error", "the trades filled is not valid");

    public static final ValidationException CERTIFICATE_SKILLS_VALIDATION_ERROR = new ValidationException("certificate_skills_validation_error", "the certificate must have skills");
}

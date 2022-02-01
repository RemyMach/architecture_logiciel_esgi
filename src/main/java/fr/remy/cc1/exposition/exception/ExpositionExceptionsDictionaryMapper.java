package fr.remy.cc1.exposition.exception;

import java.util.Map;

public class ExpositionExceptionsDictionaryMapper {

    public static final Map<String, String> codeToExpositionErrors = Map.ofEntries(
            Map.entry("system_error", "System error"),
            Map.entry("fields_null", "field don't have to be null"),
            Map.entry("password_empty_null", "password field don't have to be null or blank"),
            Map.entry("firstname_empty_null", "firstname field don't have to be null or blank"),
            Map.entry("lastname_empty_null", "lastname field don't have to be null or blank"),
            Map.entry("email_empty_null", "Email field don't have to be null or blank"),
            Map.entry("user_category_empty_null", "UserCategory field don't have to be null or blank"),
            Map.entry("amount_empty_null", "amount can't be null or blank"),
            Map.entry("discountPercentage_empty_null", "discount percentage can't be null or empty"),
            Map.entry( "payment_empty_null", "payment can't be null or blank"),
            Map.entry( "userId_empty_null", "UserId can't be null or blank"),
            Map.entry( "payment_currency_empty_null", "payment Currency can't be null or blank"),
            Map.entry( "payment_name_empty_null", "payment name can't be null or blank"),
            Map.entry( "auth_failed", "your authentication has failed"),
            Map.entry( "auth_required", "an authentication is required"),
            Map.entry( "email_required", "email is required"),
            Map.entry( "password_required", "password is required"),
            Map.entry( "password_email_auth_failed" ,"your password / login is invalid"),
            Map.entry( "card_parameters_empty_or_null" ,"Card can't be null or empty"),
            Map.entry( "paypal_parameters_empty_or_null" ,"Paypal can't be null or empty"),
            Map.entry( "company_name_empty_null" ,"Company name can't be null or empty"),
            Map.entry( "company_siren_empty_null" ,"Company Siren can't be null or empty")
    );
}

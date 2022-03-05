package fr.remy.cc1.exposition.exception;

import java.util.Map;

public class DomainExceptionsDictionaryMapper {

    public static final Map<String, String> codeToExpositionErrors = Map.ofEntries(
            Map.entry("email_not_valid", "the email is not valid"),
            Map.entry("password_not_valid", "the password is not valid"),
            Map.entry("currency_not_supported", "the currency is not supported by the application"),
            Map.entry("payment_method_not_supported", "the payment method is not supported by the application"),
            Map.entry("user_category_not_valid", "the userCategory is not valid"),
            Map.entry("card_payment_process_check", "the payment has failed"),
            Map.entry("siren_validation_error", "the siren company number is not valid"),
            Map.entry("invalid_project_id", "the provided project id does not match any known project"),
            Map.entry("project_requirements_already_exists", "project requirements already exists for given project id"),
            Map.entry("unrecognized_duration_unit", "the provided code does not match any known duration unit"),
            Map.entry("tradesman_already_taken", "the tradesman is already taken with the given date range" ),
            Map.entry("wrong_dates_order", "the start date must be before the end date"),
            Map.entry("geocoding_error", "could not encoding address to valid lat lng position")
    );
}

// Domain erreur
// Exposition -> type d'erruer
// handler Exception
// mapé l'exception vers l'exception que tu veux montrer au client avec un message

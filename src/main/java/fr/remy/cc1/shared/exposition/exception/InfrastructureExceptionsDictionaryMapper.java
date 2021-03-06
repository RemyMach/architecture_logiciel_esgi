package fr.remy.cc1.shared.exposition.exception;

import java.util.Map;

public class InfrastructureExceptionsDictionaryMapper {
    public static final Map<String, String> codeToExpositionErrors = Map.ofEntries(
            Map.entry("user_not_found", "the user specified is missing"),
            Map.entry("certificate_not_found", "the certificate doesn't exist"),
            Map.entry("project_not_found", "the provided project id does not match any known project")
    );
}

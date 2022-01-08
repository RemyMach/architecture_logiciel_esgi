package fr.remy.cc1.exposition.exception;

import java.util.Map;

public class InfrastructureExceptionsDictionary {
    public static final Map<String, String> codeToExpositionErrors = Map.ofEntries(
            Map.entry("user_not_found", "the user specified is missing")
    );
}

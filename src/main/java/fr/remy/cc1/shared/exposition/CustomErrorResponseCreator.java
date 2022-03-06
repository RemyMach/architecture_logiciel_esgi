package fr.remy.cc1.shared.exposition;

import fr.remy.cc1.shared.exposition.exception.ExpositionExceptionsDictionaryMapper;

import java.util.Map;

public class CustomErrorResponseCreator {
    private final Map<String, String> codeToExpositionErrorsMap;

    public CustomErrorResponseCreator(Map<String, String> codeToExpositionErrorsMap) {
        this.codeToExpositionErrorsMap = codeToExpositionErrorsMap;
    }

    public CustomErrorResponse create(String errorCode) {
        String message;
        if(!this.codeToExpositionErrorsMap.containsKey(errorCode)) {
            errorCode = "system_error";
            message = ExpositionExceptionsDictionaryMapper.codeToExpositionErrors.get("system_error");
        }else {
            message = this.codeToExpositionErrorsMap.get(errorCode);
        }
        return new CustomErrorResponse(errorCode, message);
    }
}

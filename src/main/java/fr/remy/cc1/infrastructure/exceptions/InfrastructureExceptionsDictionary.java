package fr.remy.cc1.infrastructure.exceptions;

public class InfrastructureExceptionsDictionary {
    public static final NoSuchEntityException USER_NOT_FOUND = new NoSuchEntityException("user_not_found", "the specified userId does not match any known user");
    public static final NoSuchEntityException TOKEN_NOT_FOUND = new NoSuchEntityException("token_not_found", "the pair user token doesn't match");
    public static final NoSuchEntityException PROJECT_NOT_FOUND = new NoSuchEntityException("project_not_found", "the specified projectId does not match any known project");
    public static final NoSuchEntityException PROJECT_REQUIREMENTS_NOT_FOUND = new NoSuchEntityException("project_requirements_not_found", "the specified projectId does not match any known project requirements");
    public static final NoSuchEntityException CERTIFICATE_NOT_FOUND = new NoSuchEntityException("certificate_not_found", "the specified certificateId does not match any known certificate");

}

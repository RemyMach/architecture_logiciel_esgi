package fr.remy.cc1.member.exposition;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ContractorRequest {
    @NotNull(message = "lastname_empty_null")
    @NotBlank(message = "lastname_empty_null")
    public String lastname;

    @NotNull(message = "firstname_empty_null")
    @NotBlank(message = "firstname_empty_null")
    public String firstname;

    @NotNull(message = "email_empty_null")
    @NotBlank(message = "email_empty_null")
    public String email;

    @NotNull(message = "password_empty_null")
    @NotBlank(message = "password_empty_null")
    public String password;

    @NotNull(message = "company_siren_empty_null")
    @NotBlank(message = "company_siren_empty_null")
    public String companySiren;

    @NotNull(message = "company_name_empty_null")
    @NotBlank(message = "company_name_empty_null")
    public String companyName;
}

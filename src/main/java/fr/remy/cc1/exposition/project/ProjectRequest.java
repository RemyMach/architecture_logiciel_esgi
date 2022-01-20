package fr.remy.cc1.exposition.project;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProjectRequest {

    @NotNull(message = "name_empty_null")
    @NotBlank(message = "name_empty_null")
    public String name;

    @NotNull(message = "description_empty_null")
    @NotBlank(message = "description_empty_null")
    public String description;
}

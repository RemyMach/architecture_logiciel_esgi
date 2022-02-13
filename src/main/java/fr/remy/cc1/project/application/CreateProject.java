package fr.remy.cc1.project.application;

import fr.remy.cc1.kernel.Command;

public class CreateProject implements Command {

    public final String name;
    public final String description;

    public CreateProject(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

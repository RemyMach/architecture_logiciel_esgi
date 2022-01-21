package fr.remy.cc1.application.project;

import fr.remy.cc1.application.ProjectDTO;
import fr.remy.cc1.domain.project.ProjectState;
import fr.remy.cc1.kernel.event.Subscriber;

public class ActivateProjectEventMessengerSubscription implements Subscriber<RegisteredProjectCredentialsEvent> {

    public ActivateProjectEventMessengerSubscription() {
    }

    @Override
    public void accept(RegisteredProjectCredentialsEvent registeredProjectCredentialsEvent) {
        ProjectDTO projectDTO = registeredProjectCredentialsEvent.getProjectIdDTO();
        projectDTO.getHistory().append(ProjectState.ACTIVE);
    }
}

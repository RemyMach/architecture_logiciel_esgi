package fr.remy.cc1.project.application;

import fr.remy.cc1.kernel.event.Subscriber;
import fr.remy.cc1.project.domain.project.ProjectState;

public class ActivateProjectEventMessengerSubscription implements Subscriber<RegisteredProjectRequirementsEvent> {

    public ActivateProjectEventMessengerSubscription() {
    }

    @Override
    public void accept(RegisteredProjectRequirementsEvent registeredProjectRequirementsEvent) {
        ProjectDTO projectDTO = registeredProjectRequirementsEvent.getProjectIdDTO();
        projectDTO.getHistory().append(ProjectState.ACTIVE);
    }
}

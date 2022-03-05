package fr.remy.cc1.ProjectTradesmen.application;

import fr.remy.cc1.domain.User;
import fr.remy.cc1.infrastructure.InMemory.TradesmanSchedulesData;
import fr.remy.cc1.infrastructure.InMemory.UserSubscriptionsData;
import fr.remy.cc1.infrastructure.InMemory.UsersData;
import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;
import fr.remy.cc1.kernel.error.UserCategoryValidatorException;
import fr.remy.cc1.kernel.error.ValidationException;
import fr.remy.cc1.member.domain.user.Email;
import fr.remy.cc1.member.domain.user.Password;
import fr.remy.cc1.member.domain.user.UserCategory;
import fr.remy.cc1.member.domain.user.Users;
import fr.remy.cc1.member.infrastructure.user.InMemoryUsers;
import fr.remy.cc1.project.domain.project.Project;
import fr.remy.cc1.project.domain.project.ProjectState;
import fr.remy.cc1.project.domain.project.Projects;
import fr.remy.cc1.project.infrastructure.InMemoryProjects;
import fr.remy.cc1.projectTradesmen.application.CreateProjectTradesmen;
import fr.remy.cc1.projectTradesmen.application.CreateProjectTradesmenCommandHandler;
import fr.remy.cc1.projectTradesmen.domain.ProjectTradesmenId;
import fr.remy.cc1.projectTradesmen.domain.ProjectsTradesmen;
import fr.remy.cc1.projectTradesmen.domain.scheduler.TradesmanSchedules;
import fr.remy.cc1.projectTradesmen.infrastructure.InMemoryProjectTradesmen;
import fr.remy.cc1.projectTradesmen.infrastructure.ProjectTradesmenCreationEventBus;
import fr.remy.cc1.projectTradesmen.infrastructure.scheduler.InMemoryTradesmanSchedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

public class CreateProjectTradesmanTest {
    ProjectsTradesmen projectsTradesmen;
    Projects projects;
    TradesmanSchedules tradesmanSchedules;
    Users users;

    public String projectIdStub;
    public List<String> tradesmenIdStub;
    public List<String> tradeJobStub;
    public List<String> dailyRateStub;
    public String currencyStub;
    public List<String> startDatesStub;
    public List<String> endDatesStub;

    CreateProjectTradesmenCommandHandler createProjectTradesmenCommandHandler;

    @BeforeEach
    void initStubValues() throws ValidationException, NoSuchEntityException
    {
        TradesmanSchedulesData.setup(new ConcurrentHashMap<>());
        UsersData.setup(new ConcurrentHashMap<>());
        UserSubscriptionsData.setup(new ConcurrentHashMap<>());

        projectsTradesmen = new InMemoryProjectTradesmen();
        projects = new InMemoryProjects();
        tradesmanSchedules = new InMemoryTradesmanSchedule(TradesmanSchedulesData.getInstance().data);
        users = new InMemoryUsers(UsersData.getInstance().data, UserSubscriptionsData.getInstance().data);

        projectIdStub = "1";
        tradesmenIdStub = List.of("1", "2");
        tradeJobStub = List.of("lumberjack", "mason");
        dailyRateStub = List.of("300", "550");
        currencyStub = "EUR";
        startDatesStub = List.of("2022-01-01", "2022-02-01");
        endDatesStub = List.of("2022-01-02", "2022-02-02");

        createProjectTradesmenCommandHandler = new CreateProjectTradesmenCommandHandler(projectsTradesmen, tradesmanSchedules, projects, users, ProjectTradesmenCreationEventBus.getInstance());
        users.save(User.of(users.nextIdentity(), "Quentin", "ROUVILLE", new Email("qrouville@gmail.com"), new Password("aZertyu9?"), UserCategory.ofCode("tradesman")));
        users.save(User.of(users.nextIdentity(), "Quentin", "MOLERO", new Email("minecraft123@gmail.com"), new Password("aZertyu9?"), UserCategory.ofCode("tradesman")));
        users.save(User.of(users.nextIdentity(), "Thomas", "SAMAAN", new Email("th0mas@outlook.com"), new Password("aZertyu9?"), UserCategory.ofCode("contractor")));

        projects.save(Project.of(projects.nextIdentity(), ProjectState.CREATED, "Facade renewal", "Renovation of the facade of the house"));

        ProjectTradesmenCreationStub.initProjectTradesmenCreationTest();
    }

    @Test
    @DisplayName("Should create a project tradesmen")
    void projectsTradesmenIsCreated() throws ValidationException, NoSuchEntityException {
        assertEquals(0, projectsTradesmen.findAll().size());

        CreateProjectTradesmen createProjectTradesmen = new CreateProjectTradesmen(projectIdStub, tradesmenIdStub, tradeJobStub, dailyRateStub, currencyStub, startDatesStub, endDatesStub);
        ProjectTradesmenId projectTradesmenId = createProjectTradesmenCommandHandler.handle(createProjectTradesmen);

        assertEquals(1, projectsTradesmen.findAll().size());
    }

    @Test
    @DisplayName("Shouldn't create a project tradesmen because the user is not a tradesman")
    void projectsTradesmenIsntCreated() {
        assertEquals(0, projectsTradesmen.findAll().size());

        tradesmenIdStub = List.of("3");
        tradeJobStub = List.of("mason");
        dailyRateStub = List.of("1");
        startDatesStub = List.of("2022-01-01");
        endDatesStub = List.of("2022-01-02");

        CreateProjectTradesmen createProjectTradesmen = new CreateProjectTradesmen(projectIdStub, tradesmenIdStub, tradeJobStub, dailyRateStub, currencyStub, startDatesStub, endDatesStub);

        Exception exception = assertThrows(UserCategoryValidatorException.class, () -> {
            ProjectTradesmenId projectTradesmenId = createProjectTradesmenCommandHandler.handle(createProjectTradesmen);
        });

        String expectedMessage = "the userCategory is not valid";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}

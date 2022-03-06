package fr.remy.cc1.member.application;

import fr.remy.cc1.kernel.CommandHandler;
import fr.remy.cc1.kernel.error.ValidationException;
import fr.remy.cc1.kernel.event.Event;
import fr.remy.cc1.kernel.event.EventBus;
import fr.remy.cc1.member.domain.company.Company;
import fr.remy.cc1.member.domain.user.Email;
import fr.remy.cc1.member.domain.user.Password;
import fr.remy.cc1.member.domain.user.UserCategory;
import fr.remy.cc1.member.domain.user.Users;
import fr.remy.cc1.member.domain.user.contractor.Contractor;
import fr.remy.cc1.member.domain.user.contractor.ContractorCreationCandidate;
import fr.remy.cc1.member.domain.user.contractor.Contractors;
import fr.remy.cc1.shared.domain.User;
import fr.remy.cc1.shared.domain.UserId;

public class CreateContractorCommandHandler implements CommandHandler<CreateContractor, UserId> {

    private final Users users;
    private final Contractors contractors;
    private final EventBus<Event> eventBus;


    public CreateContractorCommandHandler(Users users, Contractors contractors, EventBus<Event> eventBus) {
        this.users = users;
        this.eventBus = eventBus;
        this.contractors = contractors;
    }

    @Override
    public UserId handle(CreateContractor createContractor) throws ValidationException {
        Company company = Company.of(createContractor.companySiren, createContractor.companyName);
        ContractorCreationCandidate contractorCreationCandidate = ContractorCreationCandidate.of(createContractor.lastname, createContractor.firstname, new Email(createContractor.email), new Password(createContractor.password), company);
        final UserId userId = users.nextIdentity();
        Contractor contractor = Contractor.of(userId, contractorCreationCandidate.lastname, contractorCreationCandidate.firstname, contractorCreationCandidate.email, contractorCreationCandidate.password, contractorCreationCandidate.company);
        this.users.save(User.of(contractor.getUserId(), contractor.getLastname(), contractor.getFirstname(), contractor.getEmail(), contractor.getPassword(), UserCategory.CONTRACTOR));
        this.contractors.save(contractor);
        this.eventBus.send(RegisteredContractorEvent.withUser(new UserDTO(userId, contractor.getLastname(), contractor.getFirstname(), contractor.getEmail())));
        return userId;
    }
}

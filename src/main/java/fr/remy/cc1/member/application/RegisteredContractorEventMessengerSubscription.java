package fr.remy.cc1.member.application;

import fr.remy.cc1.domain.mail.Content;
import fr.remy.cc1.domain.mail.EmailSender;
import fr.remy.cc1.domain.mail.Message;
import fr.remy.cc1.kernel.event.Subscriber;
import fr.remy.cc1.member.domain.user.Email;

public class RegisteredContractorEventMessengerSubscription implements Subscriber<RegisteredContractorEvent> {

    private final EmailSender emailSender;

    public RegisteredContractorEventMessengerSubscription(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void accept(RegisteredContractorEvent registeredContractorEvent) {
        UserDTO userDTO = registeredContractorEvent.getUserDTO();
        this.sendMail(userDTO);
    }

    private void sendMail(UserDTO userDTO) {
        Message message = new Message(
                userDTO.email,
                new Email("pomme@pomme.fr"),
                "Welcome to our prenium e-market",
                Content.withText("welcome " + userDTO.email + ", we are really happy to see you in our application, you can now go try to hire the best Tradesman"));
        this.emailSender.send(message);
    }
}

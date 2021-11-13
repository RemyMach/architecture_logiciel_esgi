package fr.remy.cc1.domain.mail;

import fr.remy.cc1.infrastructure.mail.EmailSender;

public class MockEmailSender extends EmailSender{

    private static MockEmailSender mockEmailSender;

    private MockMail mockMail;

    private void EmailSender() { }

    public static MockEmailSender getInstance() {
        if (mockEmailSender == null) {
            mockEmailSender = new MockEmailSender();
        }
        return mockEmailSender;
    }

    public void setMail(MockMail mockMail) {
        super.setMail(mockMail);
        this.mockMail = mockMail;
    }

    public void send(Message message) {
        super.send(message);
    }


    public Integer getCountMail() {
        return this.mockMail.getSendMailCount();
    }
}

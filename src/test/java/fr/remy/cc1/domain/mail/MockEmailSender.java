package fr.remy.cc1.domain.mail;

public class MockEmailSender extends EmailSender {

    private static MockEmailSender mockEmailSender;

    private Integer countSendMail = 0;

    public static MockEmailSender getInstance() {
        if (mockEmailSender == null) {
            mockEmailSender = new MockEmailSender();
        }
        return mockEmailSender;
    }

    public void send(Message message) {
        this.countSendMail += 1;
    }

    public Integer getCountMail() {
        return this.countSendMail;
    }

    public void resetMockEmailSenderInstance() {
        mockEmailSender = null;
    }
}

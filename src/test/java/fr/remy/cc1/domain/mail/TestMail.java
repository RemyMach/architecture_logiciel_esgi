package fr.remy.cc1.domain.mail;

public class TestMail implements MockMail{

    Integer countMail = 0;

    @Override
    public void send(Message message) {
        countMail += 1;
    }

    @Override
    public Integer getSendMailCount() {
        return this.countMail;
    }
}

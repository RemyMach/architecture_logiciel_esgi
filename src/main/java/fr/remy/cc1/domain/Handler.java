package fr.remy.cc1.domain;

public interface Handler {

    void setNext(Handler handler);

    boolean process();

    boolean checkNext();
}

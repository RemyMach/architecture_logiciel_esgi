package fr.remy.cc1.projectTradesmen.domain.scheduler;

import java.util.Date;

public enum UnavailableDatesStatus {
    PENDING(0),
    CREATED(1),
    ACTIVE(2),
    FINISHED(3);

    private final int id;
    private Date date;

    UnavailableDatesStatus(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return this.id;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "UnavailableDatesStatus{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }
}

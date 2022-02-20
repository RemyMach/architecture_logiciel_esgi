package fr.remy.cc1.projectTradesmen.domain;

import java.util.Date;

public enum ProjectTradesmenState {
    PENDING(0),
    CREATED(1),
    ACTIVE(2);

    private final int id;
    private Date date;

    ProjectTradesmenState(int id) {
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
        return "ProjectTradesmenState{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }
}

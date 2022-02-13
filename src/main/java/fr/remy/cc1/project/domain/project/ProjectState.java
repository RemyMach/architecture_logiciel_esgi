package fr.remy.cc1.project.domain.project;

import java.util.Date;

public enum ProjectState {
    PENDING(0),
    CREATED(1),
    ACTIVE(2);

    private final int id;
    private Date date;

    ProjectState(int id) {
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
        return "ProjectState{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }
}

package fr.remy.cc1.domain.project;

public enum ProjectState {
    PENDING(0),
    CREATED(1),
    ACTIVE(2);

    private final int id;

    ProjectState(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ProjectState{" +
                "id=" + id +
                '}';
    }
}

package ua.training.library.model.entity.states;

public enum Role {
    ANONYMOUS(0), LIBRARIAN(1), CLIENT(2);

    private int id;

    private Role(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

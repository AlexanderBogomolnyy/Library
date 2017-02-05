package ua.training.library.model.entity.states;

/**
 * <p> The Role enum.
 * This enum contains possible roles of an user with role ID.
 *
 * @author Alexander Bogomolnyy
 * @version 1.0 10.01.2017.
 */
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

package ua.training.library.data.test;

import ua.training.library.model.entity.User;
import ua.training.library.model.entity.states.ActivationStatus;
import ua.training.library.model.entity.states.Role;

public enum TestUser {
    LIBRARIAN(1, "librarian", "Alexander", "B", "post@allexb.kiev.ua", "698d51a19d8a121ce581499d7b701668", Role.LIBRARIAN, ActivationStatus.ACTIVE),
    CLIENT(2, "user", "Ivan", "Ivanov", "ii@post.com", "698d51a19d8a121ce581499d7b701668", Role.CLIENT, ActivationStatus.ACTIVE);

    public User user;

    private TestUser(int id, String login, String firstName, String lastName,
                     String email, String password, Role role, ActivationStatus status) {
        user = new User.Builder()
                .setId(id)
                .setLogin(login)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
                .setRole(role)
                .setStatus(status)
                .build();
    }
}

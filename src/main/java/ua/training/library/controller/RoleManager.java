package ua.training.library.controller;

import ua.training.library.controller.command.Command;
import ua.training.library.controller.command.holder.ClientCommandHolder;
import ua.training.library.controller.command.holder.CommandHolder;
import ua.training.library.controller.command.holder.LibrarianCommandHolder;
import ua.training.library.controller.command.holder.UncheckedCommandHolder;
import ua.training.library.model.entity.states.Role;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RoleManager {

    private Map<Role, CommandHolder> mapping = new HashMap<Role, CommandHolder>() {{
        put(Role.ANONYMOUS, UncheckedCommandHolder.getInstance());
        put(Role.CLIENT, ClientCommandHolder.getInstance());
        put(Role.LIBRARIAN, LibrarianCommandHolder.getInstance());
    }};

    private static class InstanceHolder {
        private static final RoleManager INSTANCE = new RoleManager();
    }

    private RoleManager() {
    }

    public static RoleManager getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public Command getCommand(String uri, Role role) {
        Objects.requireNonNull(uri);
        Objects.requireNonNull(role);
        return mapping.get(role).getCommand(uri);
    }
}

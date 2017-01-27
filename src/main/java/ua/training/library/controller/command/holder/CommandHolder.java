package ua.training.library.controller.command.holder;

import ua.training.library.controller.command.AccessDeniedCommand;
import ua.training.library.controller.command.Command;
import ua.training.library.controller.command.ErrorCommand;

import java.util.Map;

public abstract class CommandHolder {

    protected Map<String, Command> commands;

    public static final String GET_METHOD = "GET:";
    public static final String POST_METHOD = "POST:";
    public static final Command ACCESS_DENIED = new AccessDeniedCommand();

    public Command getCommand(String uri) {
        Command command = commands.get(uri);
        return command != null ? command : new ErrorCommand();
    }
}

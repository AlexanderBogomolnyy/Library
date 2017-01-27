package ua.training.library.controller.command.holder;

import ua.training.library.controller.command.AccessDeniedCommand;
import ua.training.library.controller.command.Command;
import ua.training.library.controller.command.ErrorCommand;
import ua.training.library.controller.command.HomeCommand;
import ua.training.library.controller.command.get.*;
import ua.training.library.controller.command.post.PostLoginCommand;
import ua.training.library.controller.command.post.PostRegistrationCommand;
import ua.training.library.controller.configuration.Paths;

import java.util.HashMap;

public class UncheckedCommandHolder extends CommandHolder {

    private static class InstanceHolder {
        private static final CommandHolder COMMAND_HOLDER_INSTANCE = new UncheckedCommandHolder();
    }

    private UncheckedCommandHolder() {
        commands = new HashMap<String, Command>() {{

            put(GET_METHOD + Paths.HOME, new HomeCommand());
            put(GET_METHOD + Paths.LOGIN, new GetLoginCommand());
            put(GET_METHOD + Paths.REGISTRATION, new GetRegistrationCommand());
            put(GET_METHOD + Paths.LOGOUT, CommandHolder.ACCESS_DENIED);
            put(GET_METHOD + Paths.CLIENT_PROFILE, CommandHolder.ACCESS_DENIED);
            put(GET_METHOD + Paths.CLIENTS, CommandHolder.ACCESS_DENIED);
            put(GET_METHOD + Paths.LANG_EN, new ChangeLanguageCommand());
            put(GET_METHOD + Paths.LANG_UA, new ChangeLanguageCommand());
            put(GET_METHOD + Paths.LANG_RU, new ChangeLanguageCommand());
            put(GET_METHOD + Paths.ERROR, new ErrorCommand());
            put(GET_METHOD + Paths.ACCESS_DENIED, CommandHolder.ACCESS_DENIED);

            put(POST_METHOD + Paths.HOME, new HomeCommand());
            put(POST_METHOD + Paths.LOGIN, new PostLoginCommand());
            put(POST_METHOD + Paths.REGISTRATION, new PostRegistrationCommand());
            put(POST_METHOD + Paths.ERROR, new ErrorCommand());
            put(POST_METHOD + Paths.ACCESS_DENIED, CommandHolder.ACCESS_DENIED);

        }};

    }

    public static CommandHolder getInstance() {
        return InstanceHolder.COMMAND_HOLDER_INSTANCE;
    }

}

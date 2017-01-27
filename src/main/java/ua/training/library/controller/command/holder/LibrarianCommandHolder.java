package ua.training.library.controller.command.holder;

import ua.training.library.controller.command.Command;
import ua.training.library.controller.command.ErrorCommand;
import ua.training.library.controller.command.HomeCommand;
import ua.training.library.controller.command.get.*;
import ua.training.library.controller.command.post.PostLoginCommand;
import ua.training.library.controller.command.post.PostRegistrationCommand;
import ua.training.library.controller.configuration.Paths;

import java.util.HashMap;

public class LibrarianCommandHolder extends CommandHolder {

    private static class InstanceHolder {
        private static final CommandHolder COMMAND_HOLDER_INSTANCE = new LibrarianCommandHolder();
    }

    private LibrarianCommandHolder() {
        commands = new HashMap<String, Command>() {{
            put(GET_METHOD + Paths.HOME, new HomeCommand());
            put(GET_METHOD + Paths.LOGIN, new GetLoginCommand());
            put(GET_METHOD + Paths.REGISTRATION, new GetRegistrationCommand());
            put(GET_METHOD + Paths.LOGOUT, new GetLogoutCommand());
            put(GET_METHOD + Paths.CLIENT_PROFILE, new GetProfileCommand());
            put(GET_METHOD + Paths.CLIENTS, new GetAllClientsCommand());
            put(GET_METHOD + Paths.CATALOG, new GetCatalogCommand());
            put(GET_METHOD + Paths.LANG_EN, new ChangeLanguageCommand());
            put(GET_METHOD + Paths.LANG_UA, new ChangeLanguageCommand());
            put(GET_METHOD + Paths.LANG_RU, new ChangeLanguageCommand());
            put(GET_METHOD + Paths.ERROR, new ErrorCommand());

            put(POST_METHOD + Paths.HOME, new HomeCommand());
            put(POST_METHOD + Paths.LOGIN, new PostLoginCommand());
            put(POST_METHOD + Paths.REGISTRATION, new PostRegistrationCommand());
            put(POST_METHOD + Paths.ERROR, new ErrorCommand());
        }};
    }

    public static CommandHolder getInstance() {
        return InstanceHolder.COMMAND_HOLDER_INSTANCE;
    }

}

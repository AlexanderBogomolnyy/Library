package ua.training.library.controller.command.holder;

import ua.training.library.controller.command.Command;
import ua.training.library.controller.command.get.*;
import ua.training.library.controller.command.post.PostLoginCommand;
import ua.training.library.controller.command.post.PostOrderCommand;
import ua.training.library.controller.command.post.PostRegistrationCommand;
import ua.training.library.controller.configuration.Paths;

import java.util.HashMap;
import java.util.Map;

public class CommandHolder {

    private static final Map<String, Command> COMMANDS = new HashMap<String, Command>() {{
        put(GET_METHOD + Paths.HOME, new GetHomeCommand());
        put(GET_METHOD + Paths.LOGIN, new GetLoginCommand());
        put(GET_METHOD + Paths.LOGOUT, new GetLogoutCommand());
        put(GET_METHOD + Paths.REGISTRATION, new GetRegistrationCommand());
        put(GET_METHOD + Paths.LANG_EN, new GetChangeLanguageCommand());
        put(GET_METHOD + Paths.LANG_UA, new GetChangeLanguageCommand());
        put(GET_METHOD + Paths.LANG_RU, new GetChangeLanguageCommand());
        put(GET_METHOD + Paths.PROFILE, new GetProfileCommand());
        put(GET_METHOD + Paths.CATALOG, new GetCatalogCommand());
        put(GET_METHOD + Paths.ORDER_FORM, new GetOrderCommand());
        put(GET_METHOD + Paths.ORDERS, new GetAllOrdersCommand());
        put(GET_METHOD + Paths.CLIENTS, new GetAllClientsCommand());
        put(GET_METHOD + Paths.CUT_ERROR, new GetErrorCommand());
        put(GET_METHOD + Paths.CUT_ACCESS_DENIED, new GetAccessDeniedCommand());

        put(POST_METHOD + Paths.LOGIN, new PostLoginCommand());
        put(POST_METHOD + Paths.REGISTRATION, new PostRegistrationCommand());
        put(POST_METHOD + Paths.ORDER_FORM, new PostOrderCommand());
    }};

    public static final String GET_METHOD = "GET:";
    public static final String POST_METHOD = "POST:";

    private CommandHolder() {
    }

    private static class Holder {
        private static final CommandHolder COMMAND_HOLDER_INSTANCE = new CommandHolder();
    }

    public static CommandHolder getInstance() {
        return Holder.COMMAND_HOLDER_INSTANCE;
    }

    public Command getCommand(String uri) {
        Command command = COMMANDS.get(uri);
        return command != null ? command : new GetErrorCommand();
    }
}

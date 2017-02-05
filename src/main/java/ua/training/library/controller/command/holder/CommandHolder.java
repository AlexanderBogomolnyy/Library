package ua.training.library.controller.command.holder;

import ua.training.library.controller.command.Command;
import ua.training.library.controller.command.ErrorCommand;
import ua.training.library.controller.command.get.*;
import ua.training.library.controller.command.post.*;
import ua.training.library.controller.configuration.Paths;

import java.util.HashMap;
import java.util.Map;

public class CommandHolder {

    public static final String GET_METHOD = "GET:";
    public static final String POST_METHOD = "POST:";

    private static final Command ERROR_COMMAND = new ErrorCommand();

    private static final Map<String, Command> COMMANDS = new HashMap<String, Command>() {{
        put(GET_METHOD + Paths.HOME, new GetHomeCommand());
        put(GET_METHOD + Paths.LOGIN, new GetLoginCommand());
        put(GET_METHOD + Paths.LOGOUT, new GetLogoutCommand());
        put(GET_METHOD + Paths.REGISTRATION, new GetRegistrationCommand());
        put(GET_METHOD + Paths.LANG_EN, new ChangeLanguageCommand());
        put(GET_METHOD + Paths.LANG_UA, new ChangeLanguageCommand());
        put(GET_METHOD + Paths.LANG_RU, new ChangeLanguageCommand());
        put(GET_METHOD + Paths.PROFILE, new GetProfileCommand());
        put(GET_METHOD + Paths.CATALOG, new GetCatalogCommand());
        put(GET_METHOD + Paths.ORDER_FORM, new GetOrderCommand());
        put(GET_METHOD + Paths.ORDERS, new GetAllOrdersCommand());
        put(GET_METHOD + Paths.CLIENTS, new GetAllClientsCommand());
        put(GET_METHOD + Paths.LIBRARY_RESPONSE_FORM, new GetLibraryResponseCommand());
        put(GET_METHOD + Paths.LIBRARY_RESPONSES, new GetAllLibraryResponsesCommand());
        put(GET_METHOD + Paths.LIBRARY_RESPONSE_CLOSING, new GetLibraryResponseClosingCommand());

        put(POST_METHOD + Paths.LOGIN, new PostLoginCommand());
        put(POST_METHOD + Paths.REGISTRATION, new PostRegistrationCommand());
        put(POST_METHOD + Paths.ORDER_FORM, new PostOrderCommand());
        put(POST_METHOD + Paths.LIBRARY_RESPONSE_FORM, new PostLibraryResponseCommand());
        put(POST_METHOD + Paths.LIBRARY_RESPONSE_CLOSING, new PostLibraryResponseCompletingCommand());
        put(POST_METHOD + Paths.ORDER_COMPLETING, new PostOrderCompletingCommand());

        put(GET_METHOD + Paths.ERROR_403, ERROR_COMMAND);
        put(GET_METHOD + Paths.ERROR_404, ERROR_COMMAND);
        put(GET_METHOD + Paths.ERROR_500, ERROR_COMMAND);

        put(POST_METHOD + Paths.ERROR_403, ERROR_COMMAND);
        put(POST_METHOD + Paths.ERROR_404, ERROR_COMMAND);
        put(POST_METHOD + Paths.ERROR_500, ERROR_COMMAND);

    }};

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
        return command != null ? command : ERROR_COMMAND;
    }
}

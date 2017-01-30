package ua.training.library.config;

public interface LoggingMessages {

    String JSTL_ERROR = " Exception in JSTL tag. \n";

    String DAO_USER_EXCEPTION_GET_BY_ID = "Error with getting user by ID.";
    String DAO_USER_EXCEPTION_GET_ALL_BY_ROLE = "Error with getting list of users by role.";

    String DAO_CATALOG_EXCEPTION_GET_BY_ID = "Error with getting catalog by ID.";

    String DAO_ORDER_EXCEPTION_CREATE_NEW_ORDER = "New order creating failed.";
    String DAO_ORDER_EXCEPTION_GET_BY_ID = "Error with getting order by ID.";
    String DAO_ORDER_EXCEPTION_GET_BY_USER_ID = "Error with getting order by user ID.";
    String DAO_ORDER_EXCEPTION_GET_ALL = "Error with getting all orders.";

    String CONTROLLER_GET_REQUEST = "GET request received. URL: ";
    String CONTROLLER_POST_REQUEST = "POST request received. URL: ";
    String CONTROLLER_ERROR_IN_REDIRECTION = "Something wrong happened during redirection.";
    String CONTROLLER_ACCESS_DENIED = "Access denied. Request URI : ";
    String CONTROLLER_ERROR_PAGE = "Error page called. Request URI: ";

    String COMMAND_ERROR_IN_ORDER_CREATING = "Fail in order creating";

    String INFO_ENCODING_FILTER = "Encoding Filter called.";
    String INFO_DEFAULT_LOCALIZATION = "Default locale set. Locale: ";

    String CLIENT_LIST_EXTRACTED = "Client list successfully extracted.";
    String CLIENT_LIST_ERROR = "Error during client list extracting.";

    String CATALOG_EXTRACTED = "Catalog successfully extracted.";
    String CATALOG_ERROR = "Error during catalog extracting.";

    String ORDER_LIST_EXTRACTED = "Order list successfully extracted.";
    String ORDER_LIST_ERROR = "Error during order list extracting.";

    String ERROR_WITH_DAO_CREATING = "Error with data access object creating.";

    String SUCCESSFUL_USER_CREATING = "User creates successfully.";
    String ERROR_WITH_USER_CREATING = "User creating is failed.";

    String ERROR_BEGINNING_OF_TRANSACTION = "Error with beginning of transaction.";

    String ERROR_IN_ROLLBACK = "Error with rollback transaction.";

    String ERROR_WITH_COMMIT = "Error with transaction commit.";

    String ERROR_WITH_CLOSING = "Error with transaction closing";
}

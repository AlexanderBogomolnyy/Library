package ua.training.library.config;

public interface LoggingMessages {

    String JSTL_ERROR = " Exception in JSTL tag. \n";

    String CONTROLLER_GET_REQUEST = "GET request received. URL: ";
    String CONTROLLER_POST_REQUEST = "POST request received. URL: ";
    String CONTROLLER_ERROR_IN_REDIRECTION = "Something wrong happened during redirection.";
    String CONTROLLER_ACCESS_DENIED = "Access denied. Request URI : ";

    String INFO_ENCODING_FILTER = "Encoding Filter called.";
    String INFO_DEFAULT_LOCALIZATION = "Default locale set. Locale: ";

    String CLIENT_LIST_EXTRACTED = "Client list successfully extracted.";
    String CLIENT_LIST_ERROR = "Error during client list extracting.";

    String ERROR_WITH_DAO_CREATING = "Error with data access object creating.";

    String SUCCESSFUL_USER_CREATING = "User creates successfully.";
    String ERROR_WITH_USER_CREATING = "User creating is failed.";

    String ERROR_BEGINNING_OF_TRANSACTION = "Error with beginning of transaction.";

    String ERROR_IN_ROLLBACK = "Error with rollback transaction.";

    String ERROR_WITH_COMMIT = "Error with transaction commit.";

    String ERROR_WITH_CLOSING = "Error with transaction closing";
}

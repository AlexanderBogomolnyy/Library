package ua.training.library.messages;

/**
 * <p> The LoggingMessages interface.
 * This interface provides constants with messages for logger.
 *
 * @author Alexander Bogomolnyy
 * @version 1.0 10.01.2017.
 */
public interface LoggingMessages {

    /**
     * Logging massages for DAO
     */
    String DAO_USER_EXCEPTION_GET_BY_ID = "Error with getting user by ID.";
    String DAO_USER_EXCEPTION_GET_ALL_BY_ROLE = "Error with getting list of users by role.";
    String DAO_USER_EXCEPTION_GET_BY_LOGIN_AND_PASSWORD = "Error with getting user by login and password.";
    String DAO_USER_EXCEPTION_GET_BY_LOGIN_AND_EMAIL = "Login or e-mail is already present.";
    String DAO_USER_EXCEPTION_USER_CREATING = "User creating is failed.";

    String DAO_CATEGORY_EXCEPTION_GET_BY_ID = "Error with getting by id.";
    String DAO_CATEGORY_EXCEPTION_GET_BY_NAME = "Error with getting category by name.";
    String DAO_CATEGORY_EXCEPTION_GET_ALL = "Error with getting all categories.";

    String DAO_CATALOG_EXCEPTION_GET_BY_ID = "Error with getting catalog by ID.";
    String DAO_CATALOG_EXCEPTION_GET_BY_STATUS = "Error with getting catalog by it's element status.";
    String DAO_CATALOG_EXCEPTION_GET_BY_CATEGORY_ID = "Error with getting catalog by it's element category.";
    String DAO_CATALOG_EXCEPTION_GET_ALL = "Error with getting all catalog elements.";

    String DAO_BOOK_EXCEPTION_GET_LOCATED_IN_LIBRARY = "Error with getting books located in library by catalog ID.";
    String DAO_BOOK_EXCEPTION_GET_BY_ID = "Error with getting book by id.";
    String DAO_BOOK_EXCEPTION_GET_ALL = "Error with getting all books.";

    String DAO_ORDER_EXCEPTION_CREATE_NEW_ORDER = "New order creating failed.";
    String DAO_ORDER_EXCEPTION_GET_BY_ID = "Error with getting order by ID.";
    String DAO_ORDER_EXCEPTION_GET_BY_USER_ID = "Error with getting order by user ID.";
    String DAO_ORDER_EXCEPTION_GET_ALL = "Error with getting all orders.";
    String DAO_ORDER_EXCEPTION_UPDATE_AND_CLOSE = "Error with order updating or closing ";

    String DAO_LIBRARY_RESPONSE_EXCEPTION_CREATE_NEW_RESPONSE = "New library response creating failed.";
    String DAO_LIBRARY_RESPONSE_EXCEPTION_GET_BY_ID = "Error with getting library response by ID.";
    String DAO_LIBRARY_RESPONSE_EXCEPTION_GET_BY_LIBRARIAN_ID = "Error with getting library response by librarian ID.";
    String DAO_LIBRARY_RESPONSE_EXCEPTION_GET_BY_ORDER_ID = "Error with getting library response by order ID.";
    String DAO_LIBRARY_RESPONSE_EXCEPTION_GET_ALL = "Error with getting all library responses";

    /**
     * Logging massage in DAO Factory
     */
    String ERROR_WITH_DAO_CREATING = "Error with data access object creating.";

    /**
     * Logging messages in filters
     */
    String INFO_ENCODING_FILTER = "Encoding Filter called.";
    String INFO_DEFAULT_LOCALIZATION = "Default locale set. Locale: ";

    /**
     * Logging messages for controller
     */
    String CONTROLLER_GET_REQUEST = "GET request received. URL: ";
    String CONTROLLER_POST_REQUEST = "POST request received. URL: ";
    String CONTROLLER_ERROR_PAGE = "Error page called. Request URI: ";

    /**
     * Logging messages in commands
      */
    String COMMAND_ERROR_IN_ORDER_CREATING = "Fail in order creating";
    String COMMAND_ERROR_IN_LIBRARY_RESPONSE_CREATING = "Fail in library response creating";

    String CLIENT_LIST_EXTRACTED = "Client list successfully extracted.";
    String CLIENT_LIST_ERROR = "Error during client list extracting.";

    String CATALOG_EXTRACTED = "Catalog successfully extracted.";
    String CATALOG_ERROR = "Error during catalog extracting.";

    String ORDER_LIST_EXTRACTED = "Order list successfully extracted.";
    String ORDER_LIST_ERROR = "Error during order list extracting.";

    String LIBRARIAN_RESPONSE_LIST_EXTRACTED = "Library response list successfully extracted.";
    String LIBRARIAN_RESPONSE_LIST_ERROR = "Error during library response list extracting.";

    /**
     * Logging messages class of abstract connection implementation
     */
    String ERROR_BEGINNING_OF_TRANSACTION = "Error with beginning of transaction.";
    String ERROR_IN_ROLLBACK = "Error with rollback transaction.";
    String ERROR_WITH_COMMIT = "Error with transaction commit.";
    String ERROR_WITH_CLOSING = "Error with transaction closing";
}

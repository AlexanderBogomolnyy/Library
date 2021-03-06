package ua.training.library.messages;

public interface ServiceMessages {

    String ERROR_IN_LOGIN = "Invalid login or password. Please, try again.";
    String ERROR_WITH_CREATING_USER = "Login or e-mail is already present. Use another one.";

    String NO_PERMISSION = "You have no permission for this action";

    String ERROR_WITH_CREATING_NEW_ORDER = "This catalog card is unavailable now or have been deleted from the catalog.";
    String ERROR_WITH_UPDATING_AND_COMPLETING_IN_ORDER = "This order have been closed already.";

    String SUCCESS_IN_CREATING_USER = "Your account successfully created.";
    String SUCCESS_IN_CREATING_LIBRARY_RESPONSE = "Library response successfully created.";
    String SUCCESS_IN_LOGIN = "You are our user. We know you.";

}

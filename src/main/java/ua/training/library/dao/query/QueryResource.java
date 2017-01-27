package ua.training.library.dao.query;

public interface QueryResource {

    String ID = "id";
    String STATUS = "status";

    String USER_ROLE_ID = "user_role.id";
    String USER_ROLE_NAME = "user_role.name";
    String USER_ROLE_STATUS = "user_role.status";

    String USER_LOGIN = "user.login";
    String USER_FIRST_NAME = "user.f_name";
    String USER_LAST_NAME = "user.l_name";
    String USER_EMAIL = "user.email";
    String USER_PASSWORD = "user.password";
    String USER_STATUS = "user.status";

    String USER_GET_ALL = "SELECT * FROM `library_db`.`user` " +
            "JOIN `library_db`.`user_role` ON `user`.`role_id` = `user_role`.`id` ";
    String USER_GET_BY_LOGIN_AND_PASSWORD = USER_GET_ALL + "WHERE `login` = ? AND `password` = ?";
    String USER_GET_BY_ID = USER_GET_ALL + "WHERE `id` = ?";
    String USER_GET_BY_LOGIN_OR_EMAIL = USER_GET_ALL + "WHERE `login` = ? OR `email` = ?";
    String USER_CREATE_USER = "INSERT INTO `user` (`login`, `f_name`, `l_name`, `email`, `password`, `role_id`, `status`) " +
            "  VALUE(?, ?, ?, ?, ?, ?, ?)";

    String CATEGORY_NAME = "name";

    String CATEGORY_GET_ALL = "SELECT * FROM `library_db`.`category` ";
    String CATEGORY_GET_BY_NAME = CATEGORY_GET_ALL + "WHERE `category`.`name` = ?";
    String CATEGORY_GET_BY_ID = CATEGORY_GET_ALL + "WHERE `category`.`id` = ?";

    String CATALOG_TITLE= "title";
    String CATALOG_AUTHOR = "author";
    String CATALOG_YEAR_OF_PUBLICATION = "year_of_publication";
    String CATALOG_ISBN = "ISBN";
    String CATALOG_CATEGORY_ID = "category_id";
    String CATALOG_AMOUNT_ALL = "amount_all";
    String CATALOG_AMOUNT_AVAILABLE = "amount_available";
    String CATALOG_STATUS = "catalog.status";

    String CATALOG_GET_ALL = "SELECT * FROM `library_db`.`catalog` " +
            "JOIN `library_db`.`category` ON `catalog`.`category_id` = `category`.`id` ";
    String CATALOG_GET_BY_ID = CATALOG_GET_ALL +"WHERE `catalog`.`id` = ?";
    String CATALOG_GET_BY_STATUS = CATALOG_GET_ALL + "WHERE `catalog`.`status` = ?";
    String CATALOG_GET_BY_CATEGORY_ID = CATALOG_GET_ALL + "WHERE `catalog`.`category_id` = ?";

    String BOOK_ID = "book.id";
    String BOOK_CATALOG_ID = "catalog_id";
    String BOOK_LIBRARY_IDENTIFIER = "lib_identifier";
    String BOOK_STATE = "state";
    String BOOK_LOCATION = "location";
    String BOOK_STATUS = "book.status";

    String BOOK_GET_ALL = "SELECT * FROM `library_db`.`book` " +
            "JOIN `library_db`.`catalog` ON `book`.`catalog_id` = `catalog`.`id` " +
            "JOIN `library_db`.`category` ON `catalog`.`category_id` = `category`.`id` ";
    String BOOK_GET_BY_CATALOG_ID = BOOK_GET_ALL + "WHERE `book`.`catalog_id` = ?";
    String BOOK_GET_BY_ID = BOOK_GET_ALL + "WHERE `book`.`id` = ?";

    String ORDER_ID = "order.id";
    String ORDER_USER_ID = "user_id";
    String ORDER_CATALOG_ID = "catalog_id";
    String ORDER_DATE_OF_ISSUE = "date_of_issue";
    String ORDER_EXPECTED_DATE_OF_RETURN = "expected_date_of_return";
    String ORDER_DATE_OF_RETURN = "date_of_return";
    String ORDER_ORDER_TYPE = "order_type";

    String ORDER_GET_ALL = "SELECT * FROM `library_db`.`order` " +
            "JOIN `library_db`.`user` ON `order`.`user_id = `user`.`id` " +
            "JOIN `library_db`.`user_role` ON `user`.`role_id = `user_role`.`id` " +
            "JOIN `library_db`.`catalog` ON `order`.`catalog_id` = `catalog`.`id` " +
            "JOIN `library_db`.`category` ON `catalog`.`category_id` = `category`.`id` ";
    String ORDER_GET_BY_ID = ORDER_GET_ALL + "WHERE `order`.`id` = ?";
    String ORDER_GET_BY_USER_ID = ORDER_GET_ALL + "WHERE `order`.`user_id` = ?";

    String LIBRARY_RESPONSE_ORDER_ID = "order_id";
    String LIBRARY_RESPONSE_PROCESSING_DATE = "processing_date";
    String LIBRARY_RESPONSE_LIBRARIAN_ID = "librarian_id";
    String LIBRARY_RESPONSE_CATALOG_ID = "catalog_id";
    String LIBRARY_RESPONSE_BOOK_ID = "book_id";
    String LIBRARY_RESPONSE_BOOK_LOCATION = "book_location";
    String LIBRARY_RESPONSE_DATE_OF_RETURN = "date_of_return";

    String CLIENT_ID = "client.id";
    String CLIENT_LOGIN = "client.login";
    String CLIENT_FIRST_NAME = "client.f_name";
    String CLIENT_LAST_NAME = "client.l_name";
    String CLIENT_EMAIL = "client.email";
    String CLIENT_PASSWORD = "client.password";
    String CLIENT_STATUS = "client.status";


    String LIBRARY_RESPONSE_GET_ALL = "SELECT * FROM `library_db`.`lib_response` " +
            "JOIN `library_db`.`order` ON `lib_response`.`order_id` = `order`.`id` " +
            "JOIN `library_db`.`user` ON `lib_response`.`librarian_id` = `user`.`id` " +
            "JOIN `library_db`.`catalog` ON `lib_response`.`catalog_id` = `catalog`.`id` " +
            "JOIN `library_db`.`book` ON `lib_response`.`book_id` = `book`.`id` " +
            "JOIN `library_db`.`user` AS `client` ON `order`.`user_id` = `user`.`id` " +
            "JOIN `library_db`.`category` ON `catalog`.`category_id` = `category`.`id` " ;
    String LIBRARY_RESPONSE_GET_BY_ID = LIBRARY_RESPONSE_GET_ALL + "WHERE `id` = ?";
    String LIBRARY_RESPONSE_GET_BY_CLIENT_ID = LIBRARY_RESPONSE_GET_ALL + "WHERE `client`.`id` = ?";
    String LIBRARY_RESPONSE_GET_BY_ORDER_ID = LIBRARY_RESPONSE_GET_ALL + "WHERE `order`.`id` = ?";

}

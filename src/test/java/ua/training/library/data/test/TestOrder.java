package ua.training.library.data.test;


import ua.training.library.model.entity.Catalog;
import ua.training.library.model.entity.Order;
import ua.training.library.model.entity.User;
import ua.training.library.model.entity.states.BookLocation;
import ua.training.library.model.entity.states.OrderType;
import ua.training.library.model.util.DateHelper;

import static ua.training.library.data.test.TestUser.*;
import static ua.training.library.data.test.TestCatalog.*;

import java.time.LocalDate;

public enum TestOrder {
    ORDER_1(1, CLIENT.user.getId(), CLIENT.user, CATALOG_1.catalog.getId(), CATALOG_1.catalog,
            DateHelper.getLocalDateFromString("2017-01-25"), DateHelper.getLocalDateFromString("2017-02-12"), null,
            OrderType.IN_PROCESSING, BookLocation.ON_HAND),
    ORDER_2(2, CLIENT.user.getId(), CLIENT.user, CATALOG_2.catalog.getId(), CATALOG_2.catalog,
            DateHelper.getLocalDateFromString("2017-01-28"), DateHelper.getLocalDateFromString("2017-01-28"), null,
            OrderType.COMPLETED, BookLocation.READING_ROOM);

    public Order order;

    private TestOrder(int id, int userId, User user, int catalogId, Catalog catalog,
                      LocalDate dateOfIssue, LocalDate expectedDateOfReturn, LocalDate dateOfReturn,
                      OrderType type, BookLocation expectedBookLocation) {
        order = new Order.Builder()
                .setId(id)
                .setUserID(userId)
                .setUser(user)
                .setCatalogID(catalogId)
                .setCatalog(catalog)
                .setDateOfIssue(dateOfIssue)
                .setExpectedDateOfReturn(expectedDateOfReturn)
                .setDateOfReturn(dateOfReturn)
                .setType(type)
                .setExpectedBookLocation(expectedBookLocation)
                .build();
    }

}

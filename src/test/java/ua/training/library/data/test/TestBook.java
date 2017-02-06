package ua.training.library.data.test;

import ua.training.library.model.entity.Book;
import ua.training.library.model.entity.Catalog;
import ua.training.library.model.entity.states.BookLocation;
import ua.training.library.model.entity.states.BookStatus;

import static ua.training.library.data.test.TestCatalog.*;

public enum TestBook {
    BOOK_1(1, CATALOG_1.catalog, "C1:S2:I1", "New book.", BookLocation.LIBRARY, BookStatus.ACTIVE),
    BOOK_2(2, CATALOG_1.catalog, "C1:S2:I2", "New book.", BookLocation.LIBRARY, BookStatus.ACTIVE),
    BOOK_3(3, CATALOG_2.catalog, "C2:S1:I3", "New book.", BookLocation.LIBRARY, BookStatus.ACTIVE);

    public Book book;

    private TestBook(int id, Catalog catalog, String libraryIdentifier, String state,
                     BookLocation location, BookStatus status) {
        book = new Book.Builder()
                .setId(id)
                .setCatalog(catalog)
                .setLibraryIdentifier(libraryIdentifier)
                .setState(state)
                .setLocation(location)
                .setStatus(status)
                .build();
    }
}

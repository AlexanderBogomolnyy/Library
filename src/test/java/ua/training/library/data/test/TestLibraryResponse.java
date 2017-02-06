package ua.training.library.data.test;

import ua.training.library.model.entity.*;
import ua.training.library.model.entity.states.BookLocation;
import ua.training.library.model.util.DateHelper;

import java.time.LocalDate;

import static ua.training.library.data.test.TestCatalog.*;
import static ua.training.library.data.test.TestOrder.*;
import static ua.training.library.data.test.TestUser.*;
import static ua.training.library.data.test.TestBook.*;

public enum TestLibraryResponse {
        LIBRARY_RESPONSE_1(1, ORDER_1.order.getId(), ORDER_1.order, DateHelper.getLocalDateFromString("2017-01-25"),
                LIBRARIAN.user.getId(), LIBRARIAN.user, CATALOG_1.catalog.getId(), CATALOG_1.catalog,
                BOOK_1.book.getId(), BOOK_1.book.getLibraryIdentifier(), BOOK_1.book, BookLocation.ON_HAND,
                DateHelper.getLocalDateFromString("2017-01-25")),
        LIBRARY_RESPONSE_2(2, ORDER_2.order.getId(), ORDER_2.order, DateHelper.getLocalDateFromString("2017-01-28"),
            LIBRARIAN.user.getId(), LIBRARIAN.user, CATALOG_2.catalog.getId(), CATALOG_2.catalog,
            BOOK_3.book.getId(), BOOK_3.book.getLibraryIdentifier(), BOOK_3.book, BookLocation.READING_ROOM, null);

    public LibraryResponse libraryResponse;

    private TestLibraryResponse(int id, int orderId, Order order, LocalDate processingDate, int librarianID,
                                User librarian, int catalogId, Catalog catalog, int bookId,
                                String bookLibraryIdentifier, Book book, BookLocation location,
                                LocalDate dateOfReturn){
        libraryResponse = new LibraryResponse.Builder()
                .setId(id)
                .setOrderId(orderId)
                .setOrder(order)
                .setProcessingDate(processingDate)
                .setLibrarianId(librarianID)
                .setLibrarian(librarian)
                .setCatalogId(catalogId)
                .setCatalog(catalog)
                .setBookId(bookId)
                .setBookLibraryIdentifier(bookLibraryIdentifier)
                .setBook(book)
                .setLocation(location)
                .setDateOfReturn(dateOfReturn)
                .build();
    }

}

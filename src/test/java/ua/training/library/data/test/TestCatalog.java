package ua.training.library.data.test;

import ua.training.library.model.entity.Catalog;
import ua.training.library.model.entity.Category;
import ua.training.library.model.entity.states.BookStatus;

import static ua.training.library.data.test.TestCategory.*;

public enum TestCatalog {
    CATALOG_1(1, "Treasure island", "Robert Stevenson", 2010, FICTION.category.getId(), FICTION.category, "978-5-379-01591-6", 2, 1, BookStatus.ACTIVE),
    CATALOG_2(2, "Thinking in Java (4th Edition)", "Bruce Eckel", 2006, SCIENTIFIC.category.getId(), SCIENTIFIC.category, " 978-0-131-87248-6", 1, 1, BookStatus.ACTIVE);

    public Catalog catalog;

    private TestCatalog(int id, String title, String author, int yearOfPublication, int categoryId,
                        Category category, String isbn, int amountAll, int amountAvailable, BookStatus status) {
        catalog = new Catalog.Builder()
                .setId(id)
                .setTitle(title)
                .setAuthor(author)
                .setYearOfPublication(yearOfPublication)
                .setCategoryId(categoryId)
                .setCategory(category)
                .setIsbn(isbn)
                .setAmountAll(amountAll)
                .setAmountAvailable(amountAvailable)
                .setStatus(status)
                .build();
    }
}

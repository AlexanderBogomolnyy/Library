package ua.training.library.model.entity;

import ua.training.library.model.entity.states.BookStatus;

/**
 * <p> The Catalog class.
 * This class contains general information about book,
 * like title, authors, year of publication, isbn and category.
 * Also this class provides information about total and available amount of
 * book items, and catalog element status.
 *
 * @author Alexander Bogomolnyy
 * @version 1.0 10.01.2017.
 */
public class Catalog {

    private int id;
    private String title;
    private String author;
    private int yearOfPublication;
    private Category category;
    private String isbn;
    private int amountAll;
    private int amountAvailable;
    private BookStatus status;

    public static class Builder {

        private int id;
        private String title;
        private String author;
        private int yearOfPublication;
        private Category category;
        private String isbn;
        private int amountAll;
        private int amountAvailable;
        private BookStatus status;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setAuthor(String author) {
            this.author = author;
            return this;
        }

        public Builder setYearOfPublication(int yearOfPublication) {
            this.yearOfPublication = yearOfPublication;
            return this;
        }

        public Builder setCategory(Category category) {
            this.category = category;
            return this;
        }

        public Builder setIsbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Builder setAmountAll(int amountAll) {
            this.amountAll = amountAll;
            return this;
        }

        public Builder setAmountAvailable(int amountAvailable) {
            this.amountAvailable = amountAvailable;
            return this;
        }

        public Builder setStatus(BookStatus status) {
            this.status = status;
            return this;
        }

        public Catalog build() {
            Catalog catalog = new Catalog();
            catalog.setId(id);
            catalog.setTitle(title);
            catalog.setAuthor(author);
            catalog.setYearOfPublication(yearOfPublication);
            catalog.setCategory(category);
            catalog.setIsbn(isbn);
            catalog.setAmountAll(amountAll);
            catalog.setAmountAvailable(amountAvailable);
            catalog.setStatus(status);
            return catalog;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getAmountAll() {
        return amountAll;
    }

    public void setAmountAll(int amountAll) {
        this.amountAll = amountAll;
    }

    public int getAmountAvailable() {
        return amountAvailable;
    }

    public void setAmountAvailable(int amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Catalog)) return false;

        Catalog catalog = (Catalog) o;

        if (amountAll != catalog.amountAll) return false;
        if (amountAvailable != catalog.amountAvailable) return false;
        if (id != catalog.id) return false;
        if (yearOfPublication != catalog.yearOfPublication) return false;
        if (author != null ? !author.equals(catalog.author) : catalog.author != null) return false;
        if (category != null ? !category.equals(catalog.category) : catalog.category != null) return false;
        if (isbn != null ? !isbn.equals(catalog.isbn) : catalog.isbn != null) return false;
        if (title != null ? !title.equals(catalog.title) : catalog.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + yearOfPublication;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + amountAll;
        result = 31 * result + amountAvailable;
        return result;
    }
}

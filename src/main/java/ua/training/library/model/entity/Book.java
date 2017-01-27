package ua.training.library.model.entity;

import ua.training.library.model.entity.states.BookLocation;
import ua.training.library.model.entity.states.BookStatus;

public class Book {

    private int id;
    private Catalog catalog;
    private String libraryIdentifier;
    private String state;
    private BookLocation location;
    private BookStatus status;

    public static class Builder {

        private int id;
        private Catalog catalog;
        private String libraryIdentifier;
        private String state;
        private BookLocation location;
        private BookStatus status;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setCatalog(Catalog catalog) {
            this.catalog = catalog;
            return this;
        }

        public Builder setLibraryIdentifier(String libraryIdentifier) {
            this.libraryIdentifier = libraryIdentifier;
            return this;
        }

        public Builder setState(String state) {
            this.state = state;
            return this;
        }

        public Builder setLocation(BookLocation location) {
            this.location = location;
            return this;
        }

        public Builder setStatus(BookStatus status) {
            this.status = status;
            return this;
        }

        public Book build() {
            Book book = new Book();
            book.setId(id);
            book.setCatalog(catalog);
            book.setLibraryIdentifier(libraryIdentifier);
            book.setState(state);
            book.setLocation(location);
            book.setStatus(status);
            return book;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public String getLibraryIdentifier() {
        return libraryIdentifier;
    }

    public void setLibraryIdentifier(String libraryIdentifier) {
        this.libraryIdentifier = libraryIdentifier;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BookLocation getLocation() {
        return location;
    }

    public void setLocation(BookLocation location) {
        this.location = location;
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
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        if (id != book.id) return false;
        if (catalog != null ? !catalog.equals(book.catalog) : book.catalog != null) return false;
        if (libraryIdentifier != null ? !libraryIdentifier.equals(book.libraryIdentifier) : book.libraryIdentifier != null)
            return false;
        if (location != book.location) return false;
        if (state != null ? !state.equals(book.state) : book.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (catalog != null ? catalog.hashCode() : 0);
        result = 31 * result + (libraryIdentifier != null ? libraryIdentifier.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }
}

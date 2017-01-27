package ua.training.library.model.entity;

import ua.training.library.model.entity.states.BookLocation;

import java.time.LocalDate;

public class LibraryResponse {

    private int id;
    private Order order;
    private LocalDate processingDate;
    private User librarian;
    private Catalog catalog;
    private Book book;
    private BookLocation location;
    private LocalDate dateOfReturn;

    public static class Builder {

        private int id;
        private Order order;
        private LocalDate processingDate;
        private User librarian;
        private Catalog catalog;
        private Book book;
        private BookLocation location;
        private LocalDate dateOfReturn;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setOrder(Order order) {
            this.order = order;
            return this;
        }

        public Builder setProcessingDate(LocalDate processingDate) {
            this.processingDate = processingDate;
            return this;
        }

        public Builder setLibrarian(User librarian) {
            this.librarian = librarian;
            return this;
        }

        public Builder setCatalog(Catalog catalog) {
            this.catalog = catalog;
            return this;
        }

        public Builder setBook(Book book) {
            this.book = book;
            return this;
        }

        public Builder setLocation(BookLocation location) {
            this.location = location;
            return this;
        }

        public Builder setDateOfReturn(LocalDate dateOfReturn) {
            this.dateOfReturn = dateOfReturn;
            return this;
        }

        public LibraryResponse build() {
            LibraryResponse response = new LibraryResponse();
            response.setId(id);
            response.setOrder(order);
            response.setProcessingDate(processingDate);
            response.setLibrarian(librarian);
            response.setCatalog(catalog);
            response.setBook(book);
            response.setLocation(location);
            response.setDateOfReturn(dateOfReturn);
            return response;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public LocalDate getProcessingDate() {
        return processingDate;
    }

    public void setProcessingDate(LocalDate processingDate) {
        this.processingDate = processingDate;
    }

    public User getLibrarian() {
        return librarian;
    }

    public void setLibrarian(User librarian) {
        this.librarian = librarian;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public BookLocation getLocation() {
        return location;
    }

    public void setLocation(BookLocation location) {
        this.location = location;
    }

    public LocalDate getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(LocalDate dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LibraryResponse)) return false;

        LibraryResponse that = (LibraryResponse) o;

        if (id != that.id) return false;
        if (book != null ? !book.equals(that.book) : that.book != null) return false;
        if (catalog != null ? !catalog.equals(that.catalog) : that.catalog != null) return false;
        if (dateOfReturn != null ? !dateOfReturn.equals(that.dateOfReturn) : that.dateOfReturn != null) return false;
        if (librarian != null ? !librarian.equals(that.librarian) : that.librarian != null) return false;
        if (location != that.location) return false;
        if (order != null ? !order.equals(that.order) : that.order != null) return false;
        if (processingDate != null ? !processingDate.equals(that.processingDate) : that.processingDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (order != null ? order.hashCode() : 0);
        result = 31 * result + (processingDate != null ? processingDate.hashCode() : 0);
        result = 31 * result + (librarian != null ? librarian.hashCode() : 0);
        result = 31 * result + (catalog != null ? catalog.hashCode() : 0);
        result = 31 * result + (book != null ? book.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (dateOfReturn != null ? dateOfReturn.hashCode() : 0);
        return result;
    }
}

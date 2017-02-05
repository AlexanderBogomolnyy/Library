package ua.training.library.model.entity;

import ua.training.library.model.entity.states.BookLocation;

import java.time.LocalDate;

/**
 * <p> The Library Response class.
 * This class contains information about library response for user order.
 *
 * @author Alexander Bogomolnyy
 * @version 1.0 10.01.2017.
 */
public class LibraryResponse {

    /**
     * The library response ID
     */
    private int id;

    /**
     * The order ID
     */
    private int orderId;

    /**
     * The order which formed the basis of library response
     */
    private Order order;

    /**
     * The date of library response
     */
    private LocalDate processingDate;

    /**
     * The librarian ID
     */
    private int librarianID;

    /**
     * The librarian who made the library response
     */
    private User librarian;

    /**
     * The catalog element ID
     */
    private int catalogId;

    /**
     * The catalog element which formed the basis of library response
     */
    private Catalog catalog;

    /**
     * The book example ID
     */
    private int bookId;

    /**
     * The library identifier for book example
     */
    private String bookLibraryIdentifier;

    /**
     * The book example which was given by the library response
     */
    private Book book;

    /**
     * The location of the book after the library response have been formed
     */
    private BookLocation location;

    /**
     * The date of closing the library response
     */
    private LocalDate dateOfReturn;

    /**
     * The inner class for helping in library response instance building
     */
    public static class Builder {

        private int id;
        private int orderId;
        private Order order;
        private LocalDate processingDate;
        private int librarianId;
        private User librarian;
        private int catalogId;
        private Catalog catalog;
        private int bookId;
        private String bookLibraryIdentifier;
        private Book book;
        private BookLocation location;
        private LocalDate dateOfReturn;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setOrderId(int orderId) {
            this.orderId = orderId;
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

        public Builder setLibrarianId(int librarianId) {
            this.librarianId = librarianId;
            return this;
        }

        public Builder setLibrarian(User librarian) {
            this.librarian = librarian;
            return this;
        }

        public Builder setCatalogId(int catalogId) {
            this.catalogId = catalogId;
            return this;
        }

        public Builder setCatalog(Catalog catalog) {
            this.catalog = catalog;
            return this;
        }

        public Builder setBookId(int bookId) {
            this.bookId = bookId;
            return this;
        }

        public Builder setBookLibraryIdentifier(String bookLibraryIdentifier) {
            this.bookLibraryIdentifier = bookLibraryIdentifier;
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
            response.setOrderId(orderId);
            response.setOrder(order);
            response.setProcessingDate(processingDate);
            response.setLibrarianID(librarianId);
            response.setLibrarian(librarian);
            response.setCatalogId(catalogId);
            response.setCatalog(catalog);
            response.setBookId(bookId);
            response.setBookLibraryIdentifier(bookLibraryIdentifier);
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

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    public int getLibrarianID() {
        return librarianID;
    }

    public void setLibrarianID(int librarianID) {
        this.librarianID = librarianID;
    }

    public User getLibrarian() {
        return librarian;
    }

    public void setLibrarian(User librarian) {
        this.librarian = librarian;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public String getBookLibraryIdentifier() {
        return bookLibraryIdentifier;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setBookLibraryIdentifier(String bookLibraryIdentifier) {
        this.bookLibraryIdentifier = bookLibraryIdentifier;
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

        if (bookId != that.bookId) return false;
        if (catalogId != that.catalogId) return false;
        if (id != that.id) return false;
        if (librarianID != that.librarianID) return false;
        if (orderId != that.orderId) return false;
        if (book != null ? !book.equals(that.book) : that.book != null) return false;
        if (bookLibraryIdentifier != null ? !bookLibraryIdentifier.equals(that.bookLibraryIdentifier) : that.bookLibraryIdentifier != null)
            return false;
        if (catalog != null ? !catalog.equals(that.catalog) : that.catalog != null) return false;
        if (librarian != null ? !librarian.equals(that.librarian) : that.librarian != null) return false;
        if (order != null ? !order.equals(that.order) : that.order != null) return false;
        if (processingDate != null ? !processingDate.equals(that.processingDate) : that.processingDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + orderId;
        result = 31 * result + (order != null ? order.hashCode() : 0);
        result = 31 * result + (processingDate != null ? processingDate.hashCode() : 0);
        result = 31 * result + librarianID;
        result = 31 * result + (librarian != null ? librarian.hashCode() : 0);
        result = 31 * result + catalogId;
        result = 31 * result + (catalog != null ? catalog.hashCode() : 0);
        result = 31 * result + bookId;
        result = 31 * result + (bookLibraryIdentifier != null ? bookLibraryIdentifier.hashCode() : 0);
        result = 31 * result + (book != null ? book.hashCode() : 0);
        return result;
    }
}

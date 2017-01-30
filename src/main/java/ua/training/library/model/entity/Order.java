package ua.training.library.model.entity;

import ua.training.library.model.entity.states.BookLocation;
import ua.training.library.model.entity.states.OrderType;

import java.time.LocalDate;

/**
 * <p> The Order class.
 * This class contains general information order for taken book.
 *
 * @author Alexander Bogomolnyy
 * @version 1.0 10.01.2017.
 */
public class Order {

    /**
     * Order id
     */
    private int id;

    /**
     * User id for current order
     */
    private int userId;

    /**
     * User instance for current order
     */
    private User user;

    /**
     * Catalog id for current order
     */
    private int catalogId;

    /**
     * Catalog instance for current order
     */
    private Catalog catalog;

    /**
     * The date of issue for the order
     */
    private LocalDate dateOfIssue;

    /**
     * The expected date of return the book to the library
     */
    private LocalDate expectedDateOfReturn;

    /**
     * The real date of return the book to the library
     */
    private LocalDate dateOfReturn;

    /**
     * The type of current order (for example: NEW, IN PROCESSING, FINISHED)
     */
    private OrderType type;

    /**
     * The book location expected by the user
     */
    private BookLocation expectedBookLocation;

    /**
     * The inner class for helping in building book instance
     */
    public static class Builder {

        private int id;
        private int userID;
        private User user;
        private int catalogID;
        private Catalog catalog;
        private LocalDate dateOfIssue;
        private LocalDate expectedDateOfReturn;
        private LocalDate dateOfReturn;
        private OrderType type;
        private BookLocation expectedBookLocation;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setUserID(int userID) {
            this.userID = userID;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setCatalogID(int catalogID) {
            this.catalogID = catalogID;
            return this;
        }

        public Builder setCatalog(Catalog catalog) {
            this.catalog = catalog;
            return this;
        }

        public Builder setDateOfIssue(LocalDate dateOfIssue) {
            this.dateOfIssue = dateOfIssue;
            return this;
        }

        public Builder setExpectedDateOfReturn(LocalDate expectedDateOfReturn) {
            this.expectedDateOfReturn = expectedDateOfReturn;
            return this;
        }

        public Builder setDateOfReturn(LocalDate dateOfReturn) {
            this.dateOfReturn = dateOfReturn;
            return this;
        }

        public Builder setType(OrderType type) {
            this.type = type;
            return this;
        }

        public Builder setExpectedBookLocation(BookLocation expectedBookLocation) {
            this.expectedBookLocation = expectedBookLocation;
            return this;
        }

        public Order build() {
            Order order = new Order();
            order.setId(id);
            order.setUserId(userID);
            order.setUser(user);
            order.setCatalogId(catalogID);
            order.setCatalog(catalog);
            order.setDateOfIssue(dateOfIssue);
            order.setExpectedDateOfReturn(expectedDateOfReturn);
            order.setDateOfReturn(dateOfReturn);
            order.setType(type);
            order.setExpectedBookLocation(expectedBookLocation);
            return order;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public LocalDate getExpectedDateOfReturn() {
        return expectedDateOfReturn;
    }

    public void setExpectedDateOfReturn(LocalDate expectedDateOfReturn) {
        this.expectedDateOfReturn = expectedDateOfReturn;
    }

    public LocalDate getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(LocalDate dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public BookLocation getExpectedBookLocation() {
        return expectedBookLocation;
    }

    public void setExpectedBookLocation(BookLocation expectedBookLocation) {
        this.expectedBookLocation = expectedBookLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (catalog != null ? !catalog.equals(order.catalog) : order.catalog != null) return false;
        if (dateOfIssue != null ? !dateOfIssue.equals(order.dateOfIssue) : order.dateOfIssue != null) return false;
        if (dateOfReturn != null ? !dateOfReturn.equals(order.dateOfReturn) : order.dateOfReturn != null) return false;
        if (expectedDateOfReturn != null ? !expectedDateOfReturn.equals(order.expectedDateOfReturn) : order.expectedDateOfReturn != null)
            return false;
        if (user != null ? !user.equals(order.user) : order.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (catalog != null ? catalog.hashCode() : 0);
        result = 31 * result + (dateOfIssue != null ? dateOfIssue.hashCode() : 0);
        result = 31 * result + (expectedDateOfReturn != null ? expectedDateOfReturn.hashCode() : 0);
        result = 31 * result + (dateOfReturn != null ? dateOfReturn.hashCode() : 0);
        return result;
    }
}

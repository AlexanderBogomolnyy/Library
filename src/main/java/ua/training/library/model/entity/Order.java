package ua.training.library.model.entity;

import ua.training.library.model.entity.states.OrderType;

import java.time.LocalDate;

public class Order {

    private int id;
    private User user;
    private Catalog catalog;
    private LocalDate dateOfIssue;
    private LocalDate expectedDateOfReturn;
    private LocalDate dateOfReturn;
    private OrderType type;

    public static class Builder {

        private int id;
        private User user;
        private Catalog catalog;
        private LocalDate dateOfIssue;
        private LocalDate expectedDateOfReturn;
        private LocalDate dateOfReturn;
        private OrderType type;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
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

        public Order build() {
            Order order = new Order();
            order.setId(id);
            order.setUser(user);
            order.setCatalog(catalog);
            order.setDateOfIssue(dateOfIssue);
            order.setExpectedDateOfReturn(expectedDateOfReturn);
            order.setDateOfReturn(dateOfReturn);
            order.setType(type);
            return order;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

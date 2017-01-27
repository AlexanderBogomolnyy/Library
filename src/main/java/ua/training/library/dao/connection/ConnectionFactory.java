package ua.training.library.dao.connection;

public interface ConnectionFactory {
    AbstractConnection getMySQLConnection();
}

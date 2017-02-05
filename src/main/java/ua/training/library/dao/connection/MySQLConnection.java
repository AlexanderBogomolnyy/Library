package ua.training.library.dao.connection;

import org.apache.log4j.Logger;
import ua.training.library.config.LoggingMessages;
import ua.training.library.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.SQLException;

public class MySQLConnection implements AbstractConnection {

    private static final Logger logger = Logger.getLogger(MySQLConnection.class);

    private Connection connection;

    private boolean isTransactionStarted = false;

    public MySQLConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Object getConnection() {
        return connection;
    }

    @Override
    public void beginTransaction() {
    try {
        isTransactionStarted = true;
        connection.setAutoCommit(false);
        connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
    } catch (SQLException e) {
        logger.error(LoggingMessages.ERROR_BEGINNING_OF_TRANSACTION, e);
        throw new DAOException(LoggingMessages.ERROR_BEGINNING_OF_TRANSACTION, e);
    }
}

    @Override
    public void rollback() {
        try {
            connection.rollback();
            connection.setAutoCommit(true);
            isTransactionStarted = false;
        } catch (SQLException e) {
            logger.error(LoggingMessages.ERROR_IN_ROLLBACK, e);
            throw new DAOException(LoggingMessages.ERROR_IN_ROLLBACK, e);
        }
    }

    @Override
    public void commit() {
        try {
            connection.commit();
            connection.setAutoCommit(true);
            isTransactionStarted = false;
        } catch (SQLException e) {
            logger.error(LoggingMessages.ERROR_WITH_COMMIT, e);
            throw new DAOException(LoggingMessages.ERROR_WITH_COMMIT, e);
        }
    }

    @Override
    public void close() {
        try {
            if (isTransactionStarted) {
                rollback();
            }
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            connection.close();
        } catch (SQLException e) {
            logger.error(LoggingMessages.ERROR_WITH_CLOSING, e);
            throw new DAOException(LoggingMessages.ERROR_WITH_CLOSING, e);
        }
    }
}

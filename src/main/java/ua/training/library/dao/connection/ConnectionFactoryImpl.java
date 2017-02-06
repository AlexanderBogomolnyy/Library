package ua.training.library.dao.connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import ua.training.library.controller.configuration.ResourceNames;
import org.apache.log4j.Logger;

public class ConnectionFactoryImpl implements ConnectionFactory {

    private static final Logger logger = Logger.getLogger(ConnectionFactoryImpl.class);

    private ConnectionFactoryImpl() {
    }

    private static class Holder {
        private static final ConnectionFactoryImpl INSTANCE = new ConnectionFactoryImpl();
    }

    public static ConnectionFactoryImpl getInstance(){
        return Holder.INSTANCE;
    }

    @Override
    public AbstractConnection getMySQLConnection() {
        try {
            Context initialContext = new InitialContext();
            Context environmentContext = (Context) initialContext.lookup(ResourceNames.ENVIRONMENT_CONTEXT);
            logger.info("Environment context created.");
            DataSource dataSource = (DataSource) environmentContext.lookup(ResourceNames.JNDI_DB_NAME);
            logger.info("Data source acquired.");
            return new MySQLConnection(dataSource.getConnection());
        } catch (Exception ex) {
            logger.error("Error in the data source or environment processes");
            throw new RuntimeException(ex);
        }
    }
}

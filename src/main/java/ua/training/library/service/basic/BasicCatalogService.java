package ua.training.library.service.basic;

import org.apache.log4j.Logger;
import ua.training.library.dao.CatalogDAO;
import ua.training.library.dao.connection.AbstractConnection;
import ua.training.library.dao.connection.ConnectionFactory;
import ua.training.library.dao.connection.ConnectionFactoryImpl;
import ua.training.library.dao.factory.DAOFactory;
import ua.training.library.dao.factory.MySqlDAOFactory;
import ua.training.library.model.entity.Catalog;
import ua.training.library.model.entity.states.BookStatus;
import ua.training.library.service.CatalogService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BasicCatalogService implements CatalogService{

    private static final Logger logger = Logger.getLogger(BasicCatalogService.class);

    private ConnectionFactory connectionFactory;
    private DAOFactory daoFactory;

    BasicCatalogService(ConnectionFactory connectionFactory, DAOFactory daoFactory) {
        this.connectionFactory = connectionFactory;
        this.daoFactory = daoFactory;
    }

    private static class Holder {
        private static final CatalogService INSTANCE = new BasicCatalogService(ConnectionFactoryImpl.getInstance(),
                MySqlDAOFactory.getInstance());
    }

    public static CatalogService getInstance(){
        return Holder.INSTANCE;
    }


    @Override
    public Optional<Catalog> getById(int id) {
        try(AbstractConnection connection = connectionFactory.getMySQLConnection()) {
            CatalogDAO catalogDAO= daoFactory.getCatalogDAO(connection);
            return catalogDAO.getById(id);
        }
    }

    @Override
    public List<Catalog> getAll() {
        try(AbstractConnection connection = connectionFactory.getMySQLConnection()) {
            CatalogDAO catalogDAO= daoFactory.getCatalogDAO(connection);
            return catalogDAO.getAll();
        }
    }

    @Override
    public List<Catalog> getAllActive() {
        try(AbstractConnection connection = connectionFactory.getMySQLConnection()) {

            // TODO перенести выполнение филтрации в дао на уровень БД

            CatalogDAO catalogDAO= daoFactory.getCatalogDAO(connection);
            return catalogDAO.getAll()
                    .stream()
                    .filter(x -> x.getStatus() == BookStatus.ACTIVE)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public void create(Catalog catalog) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Catalog catalog) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }
}

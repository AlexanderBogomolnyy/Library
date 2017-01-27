package ua.training.library.dao.impl;

import ua.training.library.config.LoggingMessages;
import ua.training.library.dao.UserDAO;
import ua.training.library.dao.query.QueryResource;
import ua.training.library.model.entity.User;
import ua.training.library.model.entity.states.ActivationStatus;
import ua.training.library.model.entity.states.Role;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {

    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    private Connection connection;

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> getByLoginAndPassword(String login, String password) {
        Optional<User> result = Optional.empty();
        try(PreparedStatement query = connection.prepareStatement(QueryResource.USER_GET_BY_LOGIN_AND_PASSWORD) ){
            query.setString(1, login.toLowerCase());
            query.setString(2, password);
            ResultSet rs = query.executeQuery();
            if( rs.next() ){
                User user = getUserFromResultSet(rs);
                result = Optional.of(user);
            }
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public Optional<User> getByLoginAndEmail(String login, String email) {
        Optional<User> result = Optional.empty();
        try(PreparedStatement query = connection.prepareStatement(QueryResource.USER_GET_BY_LOGIN_OR_EMAIL) ){
            query.setString(1, login);
            query.setString(2, email.toLowerCase());
            ResultSet rs = query.executeQuery();
            if( rs.next() ){
                User user = getUserFromResultSet(rs);
                result = Optional.of(user);
            }
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public Optional<User> getById(int id) {
        Optional<User> result = Optional.empty();
        try(PreparedStatement query = connection.prepareStatement(QueryResource.USER_GET_BY_ID) ){
            query.setInt(1, id);
            ResultSet rs = query.executeQuery();
            if( rs.next() ){
                User user = getUserFromResultSet(rs);
                result = Optional.of(user);
            }
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try(PreparedStatement query = connection.prepareStatement(QueryResource.USER_GET_ALL) ){
            ResultSet rs = query.executeQuery();
            while (rs.next()){
                User user = getUserFromResultSet(rs);
                result.add(user);
            }
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public void create(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QueryResource.USER_CREATE_USER);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setInt(6, user.getRole().getId());
            preparedStatement.setString(7, user.getStatus().name());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            logger.info(LoggingMessages.SUCCESSFUL_USER_CREATING);
        } catch (Exception e){
            logger.error(LoggingMessages.ERROR_WITH_USER_CREATING, e);
            throw new RuntimeException(LoggingMessages.ERROR_WITH_USER_CREATING, e);
        }
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(int id) {

    }

    private User getUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User.Builder().
                setId(rs.getInt(QueryResource.ID))
                .setLogin(rs.getString(QueryResource.USER_LOGIN))
                .setFirstName(rs.getString(QueryResource.USER_FIRST_NAME))
                .setLastName(rs.getString(QueryResource.USER_LAST_NAME))
                .setEmail(rs.getString(QueryResource.USER_EMAIL))
                .setPassword(rs.getString(QueryResource.USER_PASSWORD))
                .setRole(Role.valueOf(rs.getString(QueryResource.USER_ROLE_NAME)))
                .setStatus(ActivationStatus.valueOf(rs.getString(QueryResource.STATUS)))
                .build();
        return user;
    }

}

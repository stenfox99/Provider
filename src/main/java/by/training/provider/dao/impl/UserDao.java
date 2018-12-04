package by.training.provider.dao.impl;

import by.training.provider.dao.Dao;
import by.training.provider.encrypt.Encrypt;
import by.training.provider.entity.User;
import by.training.provider.exception.DaoException;
import by.training.provider.pool.ConnectionPool;
import by.training.provider.pool.ProxyConnection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao implements Dao<User> {
    private static final String ADD_USER = "INSERT INTO Users(login, password, userTypeId) VALUES(?,?,?);";
    private static final String REMOVE_USER = "DELETE FROM Users WHERE Users.userId = ?;";
    private static final String CHANGE_PASSWORD = "UPDATE Users SET Users.password = ? WHERE Users.login = ?;";
    private static final String SELECT_ALL_USER = "SELECT Users.userId, Users.login, Users.userTypeId, UserTypes.userType FROM Users INNER JOIN UserTypes ON users.userTypeId = usertypes.userTypeId;";
    private static final String SELECT_USER_BY_LOGIN = "SELECT Users.userId, Users.login, Users.userTypeId, UserTypes.userType FROM Users INNER JOIN UserTypes ON users.userTypeId = usertypes.userTypeId WHERE Users.login = ?;";
    private static final String SELECT_USER_BY_ID = "SELECT Users.userId, Users.login, Users.userTypeId, UserTypes.userType FROM Users INNER JOIN UserTypes ON users.userTypeId = usertypes.userTypeId WHERE Users.userId = ?;";
    private static final String SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT Users.userId, Users.login, Users.userTypeId, UserTypes.userType FROM Users INNER JOIN UserTypes ON users.userTypeId = usertypes.userTypeId WHERE  Users.login = ? AND Users.password = ?;";
    private static UserDao instance = new UserDao();

    private UserDao() {
    }

    public static UserDao getInstance() {
        return instance;
    }

    @Override
    public void add(User element) throws DaoException {                     //TODO ENCRYPTION PASSWORD  TRANSFER IN SERVICE
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(ADD_USER);
            statement.setString(1, element.getLogin());
            String encryptionPassword = Encrypt.encrypt(element.getPassword());
            statement.setString(2, encryptionPassword);
            statement.setInt(3, element.getUserType().getUserTypeId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void remove(User element) throws DaoException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(REMOVE_USER);
            statement.setInt(1, element.getUserId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(User element) throws DaoException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(CHANGE_PASSWORD);
            statement.setString(1, element.getPassword());
            statement.setString(2, element.getLogin());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<User> findAll() throws DaoException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        List<User> users;
        try {
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(SELECT_ALL_USER);
            ResultSet resultSet = statement.executeQuery();
            users = Creator.createUsers(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return users;
    }

    public List<User> findUserByLogin(String login) throws DaoException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        List<User> users;
        try {
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(SELECT_USER_BY_LOGIN);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            users = Creator.createUsers(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return users;
    }

    public List<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        List<User> users;
        try {
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(SELECT_USER_BY_LOGIN_AND_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            users = Creator.createUsers(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return users;
    }
}

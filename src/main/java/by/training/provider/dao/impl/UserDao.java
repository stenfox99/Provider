package by.training.provider.dao.impl;

import by.training.provider.connection.ConnectionPool;
import by.training.provider.connection.ProxyConnection;
import by.training.provider.creator.UserCreator;
import by.training.provider.dao.Dao;
import by.training.provider.encrypt.Encrypt;
import by.training.provider.entity.User;
import by.training.provider.exception.DaoException;
import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class UserDao implements Dao<User> {
    private static final String ADD_USER = "INSERT INTO Users(login, password, userTypeId, userDataId) VALUES(?,?,?,?);";
    private static final String REMOVE_USER = "DELETE FROM Users WHERE Users.userId = ?;";
    private static final String UPDATE_USER = "UPDATE Users SET Users.password = ? WHERE Users.userId = ?;";
    private static final String SELECT_ALL_USER = "SELECT userId, login, password, userTypeId, userDataId FROM Users;";
    private static final String SELECT_USER_BY_LOGIN = "SELECT userId, login, password, userTypeId, userDataId FROM Users WHERE Users.login = ?;";
    private static UserDao instance;
    private static ReentrantLock locker = new ReentrantLock();
    private static boolean isCreated;

    private UserDao() {
    }

    public static UserDao getInstance() {
        if (!isCreated) {
            locker.lock();
            try {
                if (instance == null) {
                    instance = new UserDao();
                    isCreated = true;
                }
            } finally {
                locker.unlock();
            }
        }
        return instance;
    }

    @Override
    public void add(User element) throws DaoException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(ADD_USER);
            statement.setString(0, element.getLogin());
            String encryptionPassword = Encrypt.encrypt(element.getPassword());
            statement.setString(1, encryptionPassword);
            statement.setInt(2, element.getUserTypeId());
            statement.setInt(3, element.getUserDataId());
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
            statement.setInt(0, element.getUserId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(User element) throws DaoException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(UPDATE_USER);
            statement.setString(0, element.getPassword());
            statement.setInt(1, element.getUserId());
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
            users = UserCreator.createUsers(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return users;
    }

    public User findUserByLogin(String login) throws DaoException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        List<User> users;
        try {
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(SELECT_USER_BY_LOGIN);
            ResultSet resultSet = statement.executeQuery();
            users = UserCreator.createUsers(resultSet);
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return users;
    }

}

package by.training.provider.dao.impl;

import by.training.provider.pool.ConnectionPool;
import by.training.provider.pool.ProxyConnection;
import by.training.provider.dao.Dao;
import by.training.provider.entity.UserType;
import by.training.provider.exception.DaoException;
import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class UserTypeDao implements Dao<UserType> {
    private static final String SELECT_ALL_USER_TYPE = "SELECT userTypeId, userType FROM UserTypes;";
    private static UserTypeDao instance = new UserTypeDao();

    private UserTypeDao() {
    }

    public static UserTypeDao getInstance() {
        return instance;
    }

    @Override
    public void add(UserType element) {
        return;
    }       //TODO BODY

    @Override
    public void remove(UserType element) {
        return;
    }

    @Override
    public void update(UserType element) {
        return;
    }

    @Override
    public List<UserType> findAll() throws DaoException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        List<UserType> userTypes;
        try {
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(SELECT_ALL_USER_TYPE);
            ResultSet resultSet = statement.executeQuery();
            userTypes = Creator.createUserTypes(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return userTypes;
    }
}

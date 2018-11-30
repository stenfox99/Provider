package by.training.provider.dao.impl;

import by.training.provider.connection.ConnectionPool;
import by.training.provider.connection.ProxyConnection;
import by.training.provider.creator.Creator;
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
    private static UserTypeDao instance;
    private static ReentrantLock locker = new ReentrantLock();
    private static boolean isCreated;

    private UserTypeDao() {
    }

    public static UserTypeDao getInstance() {
        if (!isCreated) {
            locker.lock();
            try {
                if (instance == null) {
                    instance = new UserTypeDao();
                    isCreated = true;
                }
            } finally {
                locker.unlock();
            }
        }
        return instance;
    }

    @Override
    public void add(UserType element) {
        return;
    }

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

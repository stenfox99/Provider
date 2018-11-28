package by.training.provider.dao.impl;

import by.training.provider.connection.ConnectionPool;
import by.training.provider.connection.ProxyConnection;
import by.training.provider.dao.Dao;
import by.training.provider.entity.User;
import by.training.provider.exception.DaoException;
import com.mysql.jdbc.PreparedStatement;

import java.sql.SQLException;
import java.util.concurrent.locks.ReentrantLock;

public class UserDao implements Dao<User> {
    private static UserDao instance;
    private static ReentrantLock locker = new ReentrantLock();
    private static boolean isCreated;
    private static final String ADD_USER = "INSERT INTO Users(login, password, userTypeId, userDataId) VALUES(?,?,?,?);";

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

    public void add(User element) throws DaoException{
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(ADD_USER);
            statement.setString(0, element.getLogin());
            statement.setString(1, element.getPassword());
            statement.setInt(2, element.getUserTypeId());
            statement.setInt(3, element.getUserDataId());
        }catch (SQLException e){
            throw new DaoException(e);
        }
    }

    public void remove(User element) {
    }

    public void update(User element) {
    }
}

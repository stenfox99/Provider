package by.training.provider.dao.impl;

import by.training.provider.connection.ConnectionPool;
import by.training.provider.connection.ProxyConnection;
import by.training.provider.dao.Dao;
import by.training.provider.encrypt.Encrypt;
import by.training.provider.entity.UserData;
import by.training.provider.exception.DaoException;
import com.mysql.jdbc.PreparedStatement;

import java.sql.SQLException;
import java.util.List;

public class UserDataDao implements Dao<UserData> {
    private static final String ADD_USER_DATA = "INSERT INTO UserData;";

    @Override
    public void add(UserData element) throws DaoException{
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(ADD_USER_DATA);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void remove(UserData element) throws DaoException{

    }

    @Override
    public void update(UserData element) throws DaoException{

    }

    @Override
    public List<UserData> findAll() throws DaoException {
        return null;
    }
}

package by.training.provider.dao.impl;

import by.training.provider.dao.UserTypeDaoable;
import by.training.provider.pool.ConnectionPool;
import by.training.provider.pool.ProxyConnection;
import by.training.provider.dao.DaoBase;
import by.training.provider.entity.UserType;
import by.training.provider.exception.DaoException;
import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserTypeDao implements UserTypeDaoable{
    private static final String SELECT_ALL_USER_TYPE = "SELECT userTypeId, userType FROM UserTypes;";
    private static UserTypeDao instance = new UserTypeDao();

    private UserTypeDao() {
    }

    public static UserTypeDao getInstance() {
        return instance;
    }

    @Override
    public List<UserType> findAll() throws DaoException {
        List<UserType> userTypes;
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = (PreparedStatement) connection.prepareStatement(SELECT_ALL_USER_TYPE)){
            ResultSet resultSet = statement.executeQuery();
            userTypes = Creator.createUserTypes(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return userTypes;
    }
}

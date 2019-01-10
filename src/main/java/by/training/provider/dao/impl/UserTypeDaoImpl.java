package by.training.provider.dao.impl;

import by.training.provider.dao.UserTypeDao;
import by.training.provider.pool.ConnectionPool;
import by.training.provider.pool.ProxyConnection;
import by.training.provider.entity.UserType;
import by.training.provider.exception.DaoException;
import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserTypeDaoImpl implements UserTypeDao {
    private static final String SELECT_ALL_USER_TYPE = "SELECT userTypeId, userType FROM UserTypes;";

    private static UserTypeDaoImpl instance = new UserTypeDaoImpl();

    private UserTypeDaoImpl() {
    }

    public static UserTypeDaoImpl getInstance() {
        return instance;
    }

    @Override
    public List<UserType> findAll() throws DaoException {
        List<UserType> userTypes;
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = (PreparedStatement) connection.prepareStatement(SELECT_ALL_USER_TYPE)){
            ResultSet resultSet = statement.executeQuery();
            userTypes = ResultSetTransformer.createUserTypes(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return userTypes;
    }
}

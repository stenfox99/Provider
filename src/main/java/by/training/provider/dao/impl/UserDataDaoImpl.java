package by.training.provider.dao.impl;

import by.training.provider.dao.UserDataDao;
import by.training.provider.entity.UserData;
import by.training.provider.exception.DaoException;
import by.training.provider.pool.ConnectionPool;
import by.training.provider.pool.ProxyConnection;
import com.mysql.jdbc.PreparedStatement;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDataDaoImpl implements UserDataDao {
    private static final String ADD_USER_DATA = "INSERT INTO UserData(userDataId, userId, balance) VALUES(?,?,?);";
    private static final String REMOVE_USER_DATA = "DELETE FROM UserData WHERE UserData.userDataId = ?;";
    private static final String UPDATE_USER_DATA = "UPDATE UserData SET UserData.firstName = ?, " +
            "UserData.lastName = ?, UserData.patronymic = ?," +
            "UserData.email = ?, UserData.phone = ? WHERE UserData.userId = ?;";
    private static final String SELECT_ALL_USER_DATA = "SELECT U.UserDataId, U.firstName, U.lastName, U.patronymic," +
            "U.email, U.phone, U.balance, U.traffic," +
            "U.photo, U.userId, T.tariffName, T.price, T.monthTraffic FROM UserData U LEFT JOIN Tariffs T ON U.tariffId = T.tariffId;";
    private static final String SELECT_USER_DATA_BY_ID = "SELECT U.UserDataId, U.firstName, U.lastName, U.patronymic," +
            "U.email, U.phone, U.balance, U.traffic," +
            "U.photo, U.userId, T.tariffName, T.price, T.monthTraffic FROM UserData U LEFT JOIN Tariffs T ON U.tariffId = T.tariffId WHERE U.userId = ?;";
    private static final String UPDATE_BALANCE = "UPDATE UserData U SET U.balance = ? WHERE U.userId = ?;";
    private static final String CHANGE_TARIFF = "UPDATE UserData U SET U.tariffId = ? WHERE U.userId = ?;";
    private static UserDataDaoImpl instance = new UserDataDaoImpl();

    private UserDataDaoImpl() {
    }

    public static UserDataDaoImpl getInstance() {
        return instance;
    }

    @Override
    public void add(UserData element) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = (PreparedStatement) connection.prepareStatement(ADD_USER_DATA)) {
            int userDataId = element.getUserDataId();
            statement.setInt(1, userDataId);
            statement.setInt(2, userDataId);
            statement.setBigDecimal(3, new BigDecimal(0));
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void remove(UserData element) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = (PreparedStatement) connection.prepareStatement(REMOVE_USER_DATA)) {
            statement.setInt(1, element.getUserDataId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(UserData element) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = (PreparedStatement) connection.prepareStatement(UPDATE_USER_DATA)) {
            statement.setString(1, element.getFirstName());
            statement.setString(2, element.getLastName());
            statement.setString(3, element.getPatronymic());
            statement.setString(4, element.getEmail());
            statement.setString(5, element.getPhone());
            statement.setInt(6, element.getUserId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<UserData> findAll() throws DaoException {
        List<UserData> userData;
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = (PreparedStatement) connection.prepareStatement(SELECT_ALL_USER_DATA)) {
            ResultSet resultSet = statement.executeQuery();
            userData = ResultSetTransformer.createUserData(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return userData;
    }

    @Override
    public List<UserData> findUserDataByUserId(int userId) throws DaoException {
        List<UserData> userData;
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = (PreparedStatement) connection.prepareStatement(SELECT_USER_DATA_BY_ID)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            userData = ResultSetTransformer.createUserData(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return userData;
    }

    @Override
    public void updateBalance(int userId, BigDecimal balance) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = (PreparedStatement) connection.prepareStatement(UPDATE_BALANCE)) {
            statement.setBigDecimal(1, balance);
            statement.setInt(2, userId);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void changeTariff(int tariffId, int userId) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = (PreparedStatement) connection.prepareStatement(CHANGE_TARIFF)) {
            statement.setInt(1, tariffId);
            statement.setInt(2, userId);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}

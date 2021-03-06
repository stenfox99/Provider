package by.training.provider.dao.impl;

import by.training.provider.dao.UserDao;
import by.training.provider.entity.User;
import by.training.provider.exception.DaoException;
import by.training.provider.pool.ConnectionPool;
import by.training.provider.pool.ProxyConnection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final String ADD_USER = "INSERT INTO Users(login, password, userTypeId, ban) VALUES(?,?,?,FALSE);";

    private static final String REMOVE_USER = "DELETE FROM Users WHERE Users.userId = ?;";

    private static final String CHANGE_PASSWORD = "UPDATE Users SET Users.password = ? WHERE Users.userId = ?;";
    private static final String BAN_USER = "UPDATE Users SET Users.ban = TRUE WHERE Users.login = ?;";
    private static final String UNBAN_USER = "UPDATE Users SET Users.ban = FALSE WHERE Users.login = ?;";

    private static final String SELECT_ALL_USER = "SELECT Users.userId, Users.login, Users.userTypeId, UserTypes.userType, Users.ban FROM Users INNER JOIN UserTypes ON users.userTypeId = usertypes.userTypeId;";
    private static final String SELECT_USER_BY_LOGIN = "SELECT Users.userId, Users.login, Users.userTypeId, UserTypes.userType, Users.ban FROM Users INNER JOIN UserTypes ON users.userTypeId = usertypes.userTypeId WHERE Users.login = ?;";
    private static final String SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT Users.userId, Users.login, Users.userTypeId, UserTypes.userType, Users.ban FROM Users INNER JOIN UserTypes ON users.userTypeId = UserTypes.userTypeId WHERE Users.login = ? AND Users.password = ?;";

    private static UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public void add(User element) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = (PreparedStatement) connection.prepareStatement(ADD_USER)) {
            statement.setString(1, element.getLogin());
            statement.setString(2, element.getPassword());
            statement.setInt(3, element.getUserType().getUserTypeId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void remove(User element) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = (PreparedStatement) connection.prepareStatement(REMOVE_USER)) {
            statement.setInt(1, element.getUserId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(User element) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = (PreparedStatement) connection.prepareStatement(CHANGE_PASSWORD)) {
            statement.setString(1, element.getPassword());
            statement.setString(2, element.getLogin());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users;
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = (PreparedStatement) connection.prepareStatement(SELECT_ALL_USER)) {
            ResultSet resultSet = statement.executeQuery();
            users = ResultSetTransformer.createUsers(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return users;
    }

    @Override
    public List<User> findUserByLogin(String login) throws DaoException {
        List<User> users;
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = (PreparedStatement) connection.prepareStatement(SELECT_USER_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            users = ResultSetTransformer.createUsers(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return users;
    }

    @Override
    public List<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        List<User> users;
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = (PreparedStatement) connection.prepareStatement(SELECT_USER_BY_LOGIN_AND_PASSWORD)) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            users = ResultSetTransformer.createUsers(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return users;
    }

    @Override
    public void changePassword(int userId, String password) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = (PreparedStatement) connection.prepareStatement(CHANGE_PASSWORD)) {
            statement.setString(1, password);
            statement.setInt(2, userId);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void banUser(String login) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = (PreparedStatement) connection.prepareStatement(BAN_USER)) {
            statement.setString(1, login);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void unbanUser(String login) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = (PreparedStatement) connection.prepareStatement(UNBAN_USER)) {
            statement.setString(1, login);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}

package by.training.provider.dao.impl;

import by.training.provider.dao.BreachDaoable;
import by.training.provider.entity.Breach;
import by.training.provider.exception.DaoException;
import by.training.provider.pool.ConnectionPool;
import by.training.provider.pool.ProxyConnection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BreachDao implements BreachDaoable {
    private static final String ADD_BREACH = "INSERT INTO Breaches(description, userId) VALUES(?,?);";
    private static final String REMOVE_BREACH = "DELETE FROM Breaches WHERE Breaches.breachId = ?;";
    private static final String UPDATE_BREACH = "UPDATE Breaches SET Breaches.description = ?, Breaches.userId = ? WHERE Breaches.breachId = ?;";
    private static final String SELECT_ALL_BREACH = "SELECT Breaches.breachId, Breaches.userId, Breaches.description FROM Breaches;";
    private static BreachDao instance = new BreachDao();

    private BreachDao() {
    }

    public static BreachDao getInstance() {
        return instance;
    }

    @Override
    public void add(Breach element) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = (PreparedStatement) connection.prepareStatement(ADD_BREACH)) {
            statement.setString(1, element.getDescription());
            statement.setInt(2, element.getUserId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void remove(Breach element) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = (PreparedStatement) connection.prepareStatement(REMOVE_BREACH)) {
            statement.setInt(1, element.getBreachId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Breach element) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = (PreparedStatement) connection.prepareStatement(UPDATE_BREACH)) {
            statement.setString(1, element.getDescription());
            statement.setInt(2, element.getUserId());
            statement.setInt(3, element.getBreachId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Breach> findAll() throws DaoException {
        List<Breach> breaches;
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = (PreparedStatement) connection.prepareStatement(SELECT_ALL_BREACH)) {
            ResultSet resultSet = statement.executeQuery();
            breaches = Creator.createBreaches(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return breaches;
    }
}

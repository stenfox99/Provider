package by.training.provider.dao.impl;

import by.training.provider.connection.ConnectionPool;
import by.training.provider.connection.ProxyConnection;
import by.training.provider.creator.Creator;
import by.training.provider.dao.Dao;
import by.training.provider.entity.Breach;
import by.training.provider.entity.Tariff;
import by.training.provider.exception.DaoException;
import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class BreachDao implements Dao<Breach> {
    private static final String ADD_BREACH = "INSERT INTO Breaches(description, userId) VALUES(?,?);";
    private static final String REMOVE_BREACH = "DELETE FROM Breaches WHERE Breaches.breachId = ?;";
    private static final String UPDATE_BREACH = "UPDATE Breaches SET Breaches.description = ?, Breaches.userId = ? WHERE Breaches.breachId = ?;";
    private static final String SELECT_ALL_BREACH = "SELECT Breaches.breachId, Breaches.userId, Breaches.description FROM Breaches;";
    private static BreachDao instance;
    private static ReentrantLock locker = new ReentrantLock();
    private static boolean isCreated;

    private BreachDao() {
    }

    public static BreachDao getInstance() {
        if (!isCreated) {
            locker.lock();
            try {
                if (instance == null) {
                    instance = new BreachDao();
                    isCreated = true;
                }
            } finally {
                locker.unlock();
            }
        }
        return instance;
    }

    @Override
    public void add(Breach element) throws DaoException{
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(ADD_BREACH);
            statement.setString(0, element.getDescription());
            statement.setInt(1, element.getUserId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void remove(Breach element) throws DaoException{
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(REMOVE_BREACH);
            statement.setInt(0, element.getBreachId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Breach element) throws DaoException{
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(UPDATE_BREACH);
            statement.setString(0, element.getDescription());
            statement.setInt(1, element.getUserId());
            statement.setInt(2, element.getBreachId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Breach> findAll() throws DaoException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        List<Breach> breaches;
        try {
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(SELECT_ALL_BREACH);
            ResultSet resultSet = statement.executeQuery();
            breaches = Creator.createBreaches(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return breaches;
    }
}

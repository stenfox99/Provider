package by.training.provider.dao.impl;

import by.training.provider.connection.ConnectionPool;
import by.training.provider.connection.ProxyConnection;
import by.training.provider.creator.Creator;
import by.training.provider.dao.Dao;
import by.training.provider.encrypt.Encrypt;
import by.training.provider.entity.Tariff;
import by.training.provider.entity.User;
import by.training.provider.exception.DaoException;
import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class TariffDao implements Dao<Tariff> {
    private static final String ADD_TARIFF = "INSERT INTO Tariffs(tariffName, price, description) VALUES(?,?,?);";
    private static final String REMOVE_TARIFF = "DELETE FROM Tariffs WHERE Tariffs.tariffId = ?;";
    private static final String UPDATE_TARIFF = "UPDATE Tariffs SET Tariffs.tariffName = ?, Tariffs.price = ?, Tariffs.description = ? WHERE Tariffs.tariffId = ?;";
    private static final String SELECT_ALL_TARIFF = "SELECT Tariffs.tariffId, Tariffs.tariffName, Tariffs.price, Tariffs.description FROM Tariffs;";
    private static TariffDao instance;
    private static ReentrantLock locker = new ReentrantLock();
    private static boolean isCreated;

    private TariffDao() {
    }

    public static TariffDao getInstance() {
        if (!isCreated) {
            locker.lock();
            try {
                if (instance == null) {
                    instance = new TariffDao();
                    isCreated = true;
                }
            } finally {
                locker.unlock();
            }
        }
        return instance;
    }

    @Override
    public void add(Tariff element) throws DaoException{
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(ADD_TARIFF);
            statement.setString(0, element.getName());
            statement.setBigDecimal(1, element.getPrice());
            statement.setString(2, element.getDescription());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void remove(Tariff element) throws DaoException{
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(REMOVE_TARIFF);
            statement.setInt(0, element.getTariffId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Tariff element) throws DaoException{
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(UPDATE_TARIFF);
            statement.setString(0, element.getName());
            statement.setBigDecimal(1, element.getPrice());
            statement.setString(2, element.getDescription());
            statement.setInt(3, element.getTariffId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Tariff> findAll() throws DaoException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        List<Tariff> tariffs;
        try {
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(SELECT_ALL_TARIFF);
            ResultSet resultSet = statement.executeQuery();
            tariffs = Creator.createTariffs(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return tariffs;
    }
}

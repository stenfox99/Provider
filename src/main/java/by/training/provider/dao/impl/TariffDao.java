package by.training.provider.dao.impl;

import by.training.provider.dao.TariffDaoable;
import by.training.provider.entity.Tariff;
import by.training.provider.exception.DaoException;
import by.training.provider.pool.ConnectionPool;
import by.training.provider.pool.ProxyConnection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TariffDao implements TariffDaoable {
    private static final String ADD_TARIFF = "INSERT INTO Tariffs(tariffName, price, description) VALUES(?,?,?);";
    private static final String REMOVE_TARIFF = "DELETE FROM Tariffs WHERE Tariffs.tariffName = ?;";
    private static final String UPDATE_TARIFF = "UPDATE Tariffs SET Tariffs.tariffName = ?, Tariffs.price = ?, Tariffs.description = ? WHERE Tariffs.tariffId = ?;";
    private static final String SELECT_ALL_TARIFF = "SELECT Tariffs.tariffId, Tariffs.tariffName, Tariffs.price, Tariffs.description FROM Tariffs;";
    private static final String SELECT_BY_NAME = "SELECT Tariffs.tariffId, Tariffs.tariffName, Tariffs.price, Tariffs.description FROM Tariffs WHERE Tariffs.tariffName = ?;";
    private static TariffDao instance = new TariffDao();

    private TariffDao() {
    }

    public static TariffDao getInstance() {
        return instance;
    }

    @Override
    public void add(Tariff element) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = (PreparedStatement) connection.prepareStatement(ADD_TARIFF)) {
            statement.setString(1, element.getName());
            statement.setBigDecimal(2, element.getPrice());
            statement.setString(3, element.getDescription());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void remove(Tariff element) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = (PreparedStatement) connection.prepareStatement(REMOVE_TARIFF);) {
            statement.setString(1, element.getName());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Tariff element) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = (PreparedStatement) connection.prepareStatement(UPDATE_TARIFF)) {
            statement.setString(1, element.getName());
            statement.setBigDecimal(2, element.getPrice());
            statement.setString(3, element.getDescription());
            statement.setInt(4, element.getTariffId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Tariff> findAll() throws DaoException {
        List<Tariff> tariffs;
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = (PreparedStatement) connection.prepareStatement(SELECT_ALL_TARIFF)) {
            ResultSet resultSet = statement.executeQuery();
            tariffs = Creator.createTariffs(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return tariffs;
    }

    public List<Tariff> findTariffByName(String name) throws DaoException {
        List<Tariff> tariffs;
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = (PreparedStatement) connection.prepareStatement(SELECT_BY_NAME)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            tariffs = Creator.createTariffs(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return tariffs;
    }
}

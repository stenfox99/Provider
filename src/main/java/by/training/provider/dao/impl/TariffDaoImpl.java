package by.training.provider.dao.impl;

import by.training.provider.dao.TariffDao;
import by.training.provider.entity.Tariff;
import by.training.provider.exception.DaoException;
import by.training.provider.pool.ConnectionPool;
import by.training.provider.pool.ProxyConnection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TariffDaoImpl implements TariffDao {
    private static final String ADD_TARIFF = "INSERT INTO Tariffs(tariffName, price, monthTraffic, description) VALUES(?,?,?,?);";
    private static final String REMOVE_TARIFF = "DELETE FROM Tariffs WHERE Tariffs.tariffName = ?;";
    private static final String UPDATE_TARIFF = "UPDATE Tariffs SET Tariffs.price = ?, Tariffs.monthTraffic = ?, Tariffs.description = ? WHERE Tariffs.tariffName = ?;";
    private static final String SELECT_ALL_TARIFF = "SELECT Tariffs.tariffId, Tariffs.tariffName, Tariffs.price, Tariffs.monthTraffic, Tariffs.description FROM Tariffs;";
    private static final String SELECT_BY_NAME = "SELECT Tariffs.tariffId, Tariffs.tariffName, Tariffs.price, Tariffs.monthTraffic, Tariffs.description FROM Tariffs WHERE Tariffs.tariffName = ?;";
    private static TariffDaoImpl instance = new TariffDaoImpl();

    private TariffDaoImpl() {
    }

    public static TariffDaoImpl getInstance() {
        return instance;
    }

    @Override
    public void add(Tariff element) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = (PreparedStatement) connection.prepareStatement(ADD_TARIFF)) {
            statement.setString(1, element.getName());
            statement.setBigDecimal(2, element.getPrice());
            statement.setInt(3, element.getMonthTraffic());
            statement.setString(4, element.getDescription());
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
            statement.setBigDecimal(1, element.getPrice());
            statement.setInt(2, element.getMonthTraffic());
            statement.setString(3, element.getDescription());
            statement.setString(4, element.getName());
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

    @Override
    public List<Tariff> findByName(String name) throws DaoException {
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

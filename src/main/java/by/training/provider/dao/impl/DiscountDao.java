package by.training.provider.dao.impl;

import by.training.provider.dao.DiscountDaoable;
import by.training.provider.entity.Discount;
import by.training.provider.exception.DaoException;
import by.training.provider.pool.ConnectionPool;
import by.training.provider.pool.ProxyConnection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DiscountDao implements DiscountDaoable {
    private static final String ADD_DISCOUNT = "INSERT INTO Discounts(discountName, tariffId, discount, description, beginningDate, endDate) VALUES(?,?,?,?,?,?);";
    private static final String REMOVE_DISCOUNT = "DELETE FROM Discounts WHERE Discounts.discountName = ?;";
    private static final String UPDATE_DISCOUNT = "UPDATE Discounts SET Discounts.tariffId = ?, Discounts.discount = ?, Discounts.description = ?, Discounts.beginningDate = ?, Discounts.endDate = ? WHERE Discounts.discountName = ?;";
    private static final String SELECT_ALL_DISCOUNT = "SELECT Discounts.discountName, Discounts.discount, Discounts.description, Discounts.beginningDate, Discounts.endDate, Tariffs.tariffId, Tariffs.tariffName FROM Discounts INNER JOIN Tariffs on Discounts.tariffId = Tariffs.tariffId;";
    private static DiscountDao instance = new DiscountDao();

    private DiscountDao() {
    }

    public static DiscountDao getInstance() {
        return instance;
    }

    @Override
    public void add(Discount element) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = (PreparedStatement) connection.prepareStatement(ADD_DISCOUNT)) {
            statement.setString(1, element.getName());
            statement.setInt(2, element.getTariff().getTariffId());
            statement.setInt(3, element.getDiscount());
            statement.setString(4, element.getDescription());
            statement.setDate(5, element.getBeginningDate());
            statement.setDate(6, element.getEndDate());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void remove(Discount element) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = (PreparedStatement) connection.prepareStatement(REMOVE_DISCOUNT)) {
            statement.setString(1, element.getName());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Discount element) throws DaoException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = (PreparedStatement) connection.prepareStatement(UPDATE_DISCOUNT)) {
            statement.setInt(1, element.getTariff().getTariffId());
            statement.setInt(2, element.getDiscount());
            statement.setString(3, element.getDescription());
            statement.setDate(4, element.getBeginningDate());
            statement.setDate(5, element.getEndDate());
            statement.setString(6, element.getName());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Discount> findAll() throws DaoException {
        List<Discount> discounts;
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = (PreparedStatement) connection.prepareStatement(SELECT_ALL_DISCOUNT)) {
            ResultSet resultSet = statement.executeQuery();
            discounts = Creator.createDiscount(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return discounts;
    }
}

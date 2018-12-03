package by.training.provider.dao.impl;

import by.training.provider.dao.Dao;
import by.training.provider.entity.Discount;
import by.training.provider.exception.DaoException;
import by.training.provider.pool.ConnectionPool;
import by.training.provider.pool.ProxyConnection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DiscountDao implements Dao<Discount> {
    private static final String ADD_DISCOUNT = "INSERT INTO Discounts(tariffId, discount, description) VALUES(?,?,?);";
    private static final String REMOVE_DISCOUNT = "DELETE FROM Discounts WHERE Discounts.discountId = ?;";
    private static final String UPDATE_DISCOUNT = "UPDATE Discounts SET Discounts.tariffId = ?, Discounts.discount = ?, Discounts.description = ? WHERE Discounts.discountId = ?;";
    private static final String SELECT_ALL_DISCOUNT = "SELECT Discounts.discountId, Discounts.tariffId, Discounts.discount, Discounts.description FROM Discounts;";
    private static DiscountDao instance = new DiscountDao();

    private DiscountDao() {
    }

    public static DiscountDao getInstance() {
        return instance;
    }

    @Override
    public void add(Discount element) throws DaoException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(ADD_DISCOUNT);
            statement.setInt(1, element.getTariffId());
            statement.setInt(2, element.getDiscount());
            statement.setString(3, element.getDescription());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void remove(Discount element) throws DaoException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(REMOVE_DISCOUNT);
            statement.setInt(1, element.getDiscountId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Discount element) throws DaoException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(UPDATE_DISCOUNT);
            statement.setInt(1, element.getTariffId());
            statement.setInt(2, element.getDiscount());
            statement.setString(3, element.getDescription());
            statement.setInt(4, element.getDiscountId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Discount> findAll() throws DaoException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        List<Discount> discounts;
        try {
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(SELECT_ALL_DISCOUNT);
            ResultSet resultSet = statement.executeQuery();
            discounts = Creator.createDiscount(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return discounts;
    }
}

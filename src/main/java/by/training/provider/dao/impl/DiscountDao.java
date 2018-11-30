package by.training.provider.dao.impl;

import by.training.provider.connection.ConnectionPool;
import by.training.provider.connection.ProxyConnection;
import by.training.provider.creator.Creator;
import by.training.provider.dao.Dao;
import by.training.provider.entity.Breach;
import by.training.provider.entity.Discount;
import by.training.provider.exception.DaoException;
import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class DiscountDao implements Dao<Discount> {
    private static final String ADD_DISCOUNT = "INSERT INTO Discounts(tariffId, discount, description) VALUES(?,?,?);";
    private static final String REMOVE_DISCOUNT = "DELETE FROM Discounts WHERE Discounts.discountId = ?;";
    private static final String UPDATE_DISCOUNT = "UPDATE Discounts SET Discounts.tariffId = ?, Discounts.discount = ?, Discounts.description = ? WHERE Discounts.discountId = ?;";
    private static final String SELECT_ALL_DISCOUNT = "SELECT Discounts.discountId, Discounts.tariffId, Discounts.discount, Discounts.description FROM Discounts;";
    private static DiscountDao instance;
    private static ReentrantLock locker = new ReentrantLock();
    private static boolean isCreated;

    private DiscountDao() {
    }

    public static DiscountDao getInstance() {
        if (!isCreated) {
            locker.lock();
            try {
                if (instance == null) {
                    instance = new DiscountDao();
                    isCreated = true;
                }
            } finally {
                locker.unlock();
            }
        }
        return instance;
    }

    @Override
    public void add(Discount element) throws DaoException{
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(ADD_DISCOUNT);
            statement.setInt(0, element.getTariffId());
            statement.setInt(1, element.getDiscount());
            statement.setString(2, element.getDescription());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void remove(Discount element) throws DaoException{
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(REMOVE_DISCOUNT);
            statement.setInt(0, element.getDiscountId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Discount element) throws DaoException{
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(UPDATE_DISCOUNT);
            statement.setInt(0, element.getTariffId());
            statement.setInt(1, element.getDiscount());
            statement.setString(2, element.getDescription());
            statement.setInt(3, element.getDiscountId());
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

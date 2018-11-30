package by.training.provider.creator;

import by.training.provider.encrypt.Encrypt;
import by.training.provider.entity.*;
import by.training.provider.exception.DaoException;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Creator {
    public static List<User> createUsers(ResultSet resultUsers) throws DaoException {
        List<User> users = new ArrayList<>();
        try {
            while (resultUsers.next()) {
                int id = resultUsers.getInt(1);
                String login = resultUsers.getString(2);
                String password = Encrypt.decrypt(resultUsers.getString(3));
                int userTypeId = resultUsers.getInt(4);
                int userDataId = resultUsers.getInt(5);
                User user = new User(id, login, password, userTypeId, userDataId);
                users.add(user);
            }
        }catch (SQLException e){
            throw new DaoException("Can't interpret result set to user", e);
        }
        return users;
    }

    public static List<UserType> createUserTypes(ResultSet resultUserTypes) throws DaoException {
        List<UserType> userTypes = new ArrayList<>();
        try {
            while (resultUserTypes.next()) {
                int id = resultUserTypes.getInt(1);
                String userTypeName = resultUserTypes.getString(2);
                UserType userType = new UserType(id, userTypeName);
                userTypes.add(userType);
            }
        }catch (SQLException e){
            throw new DaoException("Can't interpret result set to user type", e);
        }
        return userTypes;
    }

    public static List<Tariff> createTariffs(ResultSet resultTariffs) throws DaoException {
        List<Tariff> tariffs = new ArrayList<>();
        try {
            while (resultTariffs.next()) {
                int id = resultTariffs.getInt(1);
                String tariffName = resultTariffs.getString(2);
                BigDecimal price = resultTariffs.getBigDecimal(3);
                String description = resultTariffs.getString(4);
                Tariff tariff = new Tariff(id, tariffName, price, description);
                tariffs.add(tariff);
            }
        }catch (SQLException e){
            throw new DaoException("Can't interpret result set to tariff", e);
        }
        return tariffs;
    }

    public static List<Breach> createBreaches(ResultSet resultBreaches) throws DaoException {
        List<Breach> breaches = new ArrayList<>();
        try {
            while (resultBreaches.next()) {
                int id = resultBreaches.getInt(1);
                String description = resultBreaches.getString(2);
                int userId = resultBreaches.getInt(3);
                Breach breach = new Breach(id, description, userId);
                breaches.add(breach);
            }
        }catch (SQLException e){
            throw new DaoException("Can't interpret result set to breach", e);
        }
        return breaches;
    }

    public static List<Discount> createDiscount(ResultSet resultDiscount) throws DaoException {
        List<Discount> discounts = new ArrayList<>();
        try {
            while (resultDiscount.next()) {
                int id = resultDiscount.getInt(1);
                int tariffId = resultDiscount.getInt(2);
                int discount = resultDiscount.getInt(3);
                String description = resultDiscount.getString(4);
                Discount newDiscount = new Discount(id, tariffId, discount, description);
                discounts.add(newDiscount);
            }
        }catch (SQLException e){
            throw new DaoException("Can't interpret result set to discount", e);
        }
        return discounts;
    }
}

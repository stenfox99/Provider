package by.training.provider.dao.impl;

import by.training.provider.entity.*;
import by.training.provider.exception.DaoException;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

class ResultSetTransformer {
    private ResultSetTransformer(){}

    static List<User> createUsers(ResultSet resultUsers) throws DaoException {
        List<User> users = new ArrayList<>();
        try {
            while (resultUsers.next()) {
                int userId = resultUsers.getInt(1);
                String login = resultUsers.getString(2);
                int userTypeId = resultUsers.getInt(3);
                String userType = resultUsers.getString(4);
                boolean ban = resultUsers.getBoolean(5);
                User user = new User(userId, login, new UserType(userTypeId, userType), ban);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't interpret result set to user", e);
        }
        return users;
    }

    static List<UserType> createUserTypes(ResultSet resultUserTypes) throws DaoException {
        List<UserType> userTypes = new ArrayList<>();
        try {
            while (resultUserTypes.next()) {
                int id = resultUserTypes.getInt(1);
                String userTypeName = resultUserTypes.getString(2);
                UserType userType = new UserType(id, userTypeName);
                userTypes.add(userType);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't interpret result set to user type", e);
        }
        return userTypes;
    }

    static List<Tariff> createTariffs(ResultSet resultTariffs) throws DaoException {
        List<Tariff> tariffs = new ArrayList<>();
        try {
            while (resultTariffs.next()) {
                int id = resultTariffs.getInt(1);
                String tariffName = resultTariffs.getString(2);
                BigDecimal price = resultTariffs.getBigDecimal(3);
                int discount = resultTariffs.getInt(4);
                Date beginningDate = resultTariffs.getDate(5);
                Date endDate = resultTariffs.getDate(6);
                BigDecimal priceWithDiscount = price;
                java.util.Date currentDate = new java.util.Date();
                if (discount != 0 && beginningDate.compareTo(new Date(currentDate.getTime())) <= 0
                        && endDate.compareTo(new Date(currentDate.getTime())) >= 0) {
                    priceWithDiscount = price.subtract(price.multiply(new BigDecimal(discount)).divide(new BigDecimal(100)));
                }
                int monthTraffic = resultTariffs.getInt(7);
                String description = resultTariffs.getString(8);
                Tariff tariff = new Tariff(id, tariffName, price, priceWithDiscount, monthTraffic, description);
                tariffs.add(tariff);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't interpret result set to tariff", e);
        }
        return tariffs;
    }

    static List<Discount> createDiscount(ResultSet resultDiscount) throws DaoException {
        List<Discount> discounts = new ArrayList<>();
        try {
            while (resultDiscount.next()) {
                String discountName = resultDiscount.getString(1);
                int discount = resultDiscount.getInt(2);
                String description = resultDiscount.getString(3);
                Date beginningDate = resultDiscount.getDate(4);
                Date endDate = resultDiscount.getDate(5);
                int tariffId = resultDiscount.getInt(6);
                String tariffName = resultDiscount.getString(7);
                Tariff tariff = new Tariff(tariffId, tariffName);

                Discount newDiscount = new Discount(discountName, discount, description, beginningDate, endDate, tariff);
                discounts.add(newDiscount);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't interpret result set to discount", e);
        }
        return discounts;
    }

    static List<UserData> createUserData(ResultSet resultUserData) throws DaoException {
        List<UserData> userData = new ArrayList<>();
        try {
            while (resultUserData.next()) {
                int userDataId = resultUserData.getInt(1);
                String firstName = resultUserData.getString(2);
                String lastName = resultUserData.getString(3);
                String patronymic = resultUserData.getString(4);
                String email = resultUserData.getString(5);
                String phone = resultUserData.getString(6);
                BigDecimal balance = resultUserData.getBigDecimal(7);
                int traffic = resultUserData.getInt(8);
                String photo = "";
                if (resultUserData.getBlob(9) != null) {
                    Blob photoBlob = resultUserData.getBlob(9);
                    byte[] avatar = photoBlob.getBytes(1, (int) photoBlob.length());
                    photo = Base64.getEncoder().encodeToString(avatar);
                }
                int userId = resultUserData.getInt(10);
                String tariffName = resultUserData.getString(11);
                BigDecimal price = resultUserData.getBigDecimal(12);
                int discount = resultUserData.getInt(13);
                Date beginningDate = resultUserData.getDate(14);
                Date endDate = resultUserData.getDate(15);
                BigDecimal priceWithDiscount = price;
                java.util.Date currentDate = new java.util.Date();
                if (discount != 0 && beginningDate.compareTo(new Date(currentDate.getTime())) <= 0
                        && endDate.compareTo(new Date(currentDate.getTime())) >= 0) {
                    priceWithDiscount = price.subtract(price.multiply(new BigDecimal(discount)).divide(new BigDecimal(100)));
                }
                int monthTraffic = resultUserData.getInt(16);
                Tariff tariff = new Tariff(tariffName, price, priceWithDiscount, monthTraffic);
                UserData newUserData = new UserData(userDataId, firstName, lastName, patronymic, email, phone, tariff, balance, traffic, photo, userId);
                userData.add(newUserData);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't interpret result set to user data", e);
        }
        return userData;
    }
}

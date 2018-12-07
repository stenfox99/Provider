package by.training.provider.dao.impl;

import by.training.provider.entity.*;
import by.training.provider.exception.DaoException;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class Creator {
    static List<User> createUsers(ResultSet resultUsers) throws DaoException {
        List<User> users = new ArrayList<>();
        try {
            while (resultUsers.next()) {
                int userId = resultUsers.getInt(1);
                String login = resultUsers.getString(2);
                int userTypeId = resultUsers.getInt(3);
                String userType = resultUsers.getString(4);
                User user = new User(userId, login, new UserType(userTypeId, userType));
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
                int monthTraffic = resultTariffs.getInt(4);
                String description = resultTariffs.getString(5);
                Tariff tariff = new Tariff(id, tariffName, price, monthTraffic, description);
                tariffs.add(tariff);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't interpret result set to tariff", e);
        }
        return tariffs;
    }

    static List<Breach> createBreaches(ResultSet resultBreaches) throws DaoException {
        List<Breach> breaches = new ArrayList<>();
        try {
            while (resultBreaches.next()) {
                int id = resultBreaches.getInt(1);
                String description = resultBreaches.getString(2);
                int userId = resultBreaches.getInt(3);
                Breach breach = new Breach(id, description, userId);
                breaches.add(breach);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't interpret result set to breach", e);
        }
        return breaches;
    }

    static List<Discount> createDiscount(ResultSet resultDiscount) throws DaoException {
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
                int tariffId = resultUserData.getInt(7);
                BigDecimal balance = resultUserData.getBigDecimal(8);
                int traffic = resultUserData.getInt(9);
                boolean ban = resultUserData.getBoolean(10);
                Blob photo = resultUserData.getBlob(11);
                int userId = resultUserData.getInt(12);
                UserData newUserData = new UserData(userDataId, firstName, lastName, patronymic, email, phone, tariffId, balance, traffic, ban, photo, userId);
                userData.add(newUserData);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't interpret result set to user data", e);
        }
        return userData;
    }
}

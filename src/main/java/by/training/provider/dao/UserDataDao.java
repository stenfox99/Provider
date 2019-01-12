package by.training.provider.dao;

import by.training.provider.entity.UserData;
import by.training.provider.exception.DaoException;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

public interface UserDataDao extends DaoBase<UserData> {
    List<UserData> findUserDataByUserId(int userId) throws DaoException;

    void updateBalance(int userId, BigDecimal balance) throws DaoException;

    void changeTariff(int tariffId, int userId) throws DaoException;

    void updateBalanceAndTraffic(UserData userData) throws DaoException;

    void updateImage(int userId, InputStream image) throws DaoException;

    void updateTraffic(UserData data) throws DaoException;
}

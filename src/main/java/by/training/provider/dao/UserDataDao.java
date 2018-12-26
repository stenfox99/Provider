package by.training.provider.dao;

import by.training.provider.entity.UserData;
import by.training.provider.exception.DaoException;

import javax.servlet.http.Part;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.List;

public interface UserDataDao extends DaoBase<UserData> {
    List<UserData> findUserDataByUserId(int userId) throws DaoException;
    void updateBalance(int userId, BigDecimal balance) throws DaoException;
    void changeTariff(int tariffId, int userId) throws DaoException;
    void uploadImage(int userId, Part image) throws DaoException;
    void updateBalanceAndTraffic(UserData userData) throws DaoException;
}

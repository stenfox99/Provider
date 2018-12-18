package by.training.provider.dao;

import by.training.provider.entity.UserData;
import by.training.provider.exception.DaoException;

import java.math.BigDecimal;
import java.util.List;

public interface UserDataDao extends DaoBase<UserData> {
    List<UserData> findUserDataByUserId(int userId) throws DaoException;
    void updateBalance(int userId, BigDecimal balance) throws DaoException;
}

package by.training.provider.dao;

import by.training.provider.entity.UserData;
import by.training.provider.exception.DaoException;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

/**
 * The Interface UserDataDao.
 */
public interface UserDataDao extends DaoBase<UserData> {
    
    /**
     * Find user data by user id.
     *
     * @param userId the user id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<UserData> findUserDataByUserId(int userId) throws DaoException;

    /**
     * Update balance.
     *
     * @param userId the user id
     * @param balance the balance
     * @throws DaoException the dao exception
     */
    void updateBalance(int userId, BigDecimal balance) throws DaoException;

    /**
     * Change tariff.
     *
     * @param tariffId the tariff id
     * @param userId the user id
     * @throws DaoException the dao exception
     */
    void changeTariff(int tariffId, int userId) throws DaoException;

    /**
     * Update balance and traffic.
     *
     * @param userData the user data
     * @throws DaoException the dao exception
     */
    void updateBalanceAndTraffic(UserData userData) throws DaoException;

    /**
     * Update image.
     *
     * @param userId the user id
     * @param image the image
     * @throws DaoException the dao exception
     */
    void updateImage(int userId, InputStream image) throws DaoException;

    /**
     * Update traffic.
     *
     * @param data the data
     * @throws DaoException the dao exception
     */
    void updateTraffic(UserData data) throws DaoException;
}

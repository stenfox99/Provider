package by.training.provider.dao;

import by.training.provider.entity.User;
import by.training.provider.exception.DaoException;

import java.util.List;

/**
 * The Interface UserDao.
 */
public interface UserDao extends DaoBase<User> {
    
    /**
     * Find user by login.
     *
     * @param login the login
     * @return the list
     * @throws DaoException the dao exception
     */
    List<User> findUserByLogin(String login) throws DaoException;

    /**
     * Find user by login and password.
     *
     * @param login the login
     * @param password the password
     * @return the list
     * @throws DaoException the dao exception
     */
    List<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

    /**
     * Change password.
     *
     * @param userId the user id
     * @param password the password
     * @throws DaoException the dao exception
     */
    void changePassword(int userId, String password) throws DaoException;

    /**
     * Ban user.
     *
     * @param login the login
     * @throws DaoException the dao exception
     */
    void banUser(String login) throws DaoException;

    /**
     * Unban user.
     *
     * @param login the login
     * @throws DaoException the dao exception
     */
    void unbanUser(String login) throws DaoException;

}

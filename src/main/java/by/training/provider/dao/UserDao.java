package by.training.provider.dao;

import by.training.provider.entity.User;
import by.training.provider.exception.DaoException;

import java.io.InputStream;
import java.util.List;

public interface UserDao extends DaoBase<User> {
    List<User> findUserByLogin(String login) throws DaoException;
    List<User> findUserByLoginAndPassword(String login, String password) throws DaoException;
    void changePassword(int userId, String password)throws DaoException;
    void banUser(String login)throws DaoException;
    void unbanUser(String login)throws DaoException;

}

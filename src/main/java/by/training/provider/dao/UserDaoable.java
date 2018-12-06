package by.training.provider.dao;

import by.training.provider.entity.User;
import by.training.provider.exception.DaoException;

import java.util.List;

public interface UserDaoable extends DaoBase<User> {
    List<User> findUserByLogin(String login) throws DaoException;
    List<User> findUserByLoginAndPassword(String login, String password) throws DaoException;
}

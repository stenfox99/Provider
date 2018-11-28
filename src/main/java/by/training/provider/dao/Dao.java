package by.training.provider.dao;

import by.training.provider.exception.DaoException;

public interface Dao<T> {

    void add(T element) throws DaoException;
    void remove(T element) throws DaoException;
    void update(T element) throws DaoException;
}

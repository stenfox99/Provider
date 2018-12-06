package by.training.provider.dao;

import by.training.provider.entity.Entity;
import by.training.provider.exception.DaoException;

import java.util.List;

public interface DaoBase<T extends Entity> {
    void add(T element) throws DaoException;
    void remove(T element) throws DaoException;
    void update(T element) throws DaoException;
    List<T> findAll() throws DaoException;
}

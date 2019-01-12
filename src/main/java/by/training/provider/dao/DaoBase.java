package by.training.provider.dao;

import by.training.provider.entity.Entity;
import by.training.provider.exception.DaoException;

import java.util.List;

/**
 * The Interface DaoBase.
 *
 * @param <T> the generic type
 */
public interface DaoBase<T extends Entity> {
    
    /**
     * Adds the.
     *
     * @param element the element
     * @throws DaoException the dao exception
     */
    void add(T element) throws DaoException;
    
    /**
     * Removes the.
     *
     * @param element the element
     * @throws DaoException the dao exception
     */
    void remove(T element) throws DaoException;
    
    /**
     * Update.
     *
     * @param element the element
     * @throws DaoException the dao exception
     */
    void update(T element) throws DaoException;
    
    /**
     * Find all.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<T> findAll() throws DaoException;
}

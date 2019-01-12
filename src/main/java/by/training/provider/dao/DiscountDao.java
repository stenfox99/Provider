package by.training.provider.dao;

import by.training.provider.entity.Discount;
import by.training.provider.exception.DaoException;

import java.util.List;

/**
 * The Interface DiscountDao.
 */
public interface DiscountDao extends DaoBase<Discount> {
    
    /**
     * Find by name.
     *
     * @param name the name
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Discount> findByName(String name)throws DaoException;
    
    /**
     * Find by tariff name.
     *
     * @param name the name
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Discount> findByTariffName(String name) throws DaoException;
}

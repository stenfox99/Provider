package by.training.provider.dao;

import by.training.provider.entity.Tariff;
import by.training.provider.exception.DaoException;

import java.util.List;

/**
 * The Interface TariffDao.
 */
public interface TariffDao extends DaoBase<Tariff> {
    
    /**
     * Find by name.
     *
     * @param name the name
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Tariff> findByName(String name)throws DaoException;
}

package by.training.provider.dao;

import by.training.provider.entity.Discount;
import by.training.provider.exception.DaoException;

import java.util.List;

public interface DiscountDao extends DaoBase<Discount> {
    List<Discount> findByName(String name)throws DaoException;
    List<Discount> findByTariffName(String name) throws DaoException;
}

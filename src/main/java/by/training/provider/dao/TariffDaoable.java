package by.training.provider.dao;

import by.training.provider.entity.Tariff;
import by.training.provider.exception.DaoException;

import java.util.List;

public interface TariffDaoable extends DaoBase<Tariff> {
    List<Tariff> findByName(String name)throws DaoException;
}

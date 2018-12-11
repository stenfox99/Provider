package by.training.provider.dao;

import by.training.provider.entity.Discount;
import by.training.provider.exception.DaoException;

import java.util.List;

public interface DiscountDaoable extends DaoBase<Discount> {    //TODO ABLE
    List<Discount> findByName(String name)throws DaoException;
}

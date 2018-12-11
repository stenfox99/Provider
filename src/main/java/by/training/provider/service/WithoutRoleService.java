package by.training.provider.service;

import by.training.provider.dao.impl.DiscountDao;
import by.training.provider.dao.impl.TariffDao;
import by.training.provider.entity.Discount;
import by.training.provider.entity.Tariff;
import by.training.provider.exception.DaoException;

import java.util.List;

public class WithoutRoleService {  //TODO NAME

    public List<Tariff> findAllTariffs() throws DaoException {
        List<Tariff> tariffs;
        try {
            tariffs = TariffDao.getInstance().findAll();
        } catch (DaoException e) {
            throw new DaoException(e);
        }
        return tariffs;
    }

    //TODO NAME METHOD
//    public List<Tariff> getListOnPage(List<Tariff> list, int pageNumber) {
//        List<Tariff> returnedList = new ArrayList<>();
//        for (int i = pageNumber * FieldConst.COUNT_ON_PAGE, j = 0; j < FieldConst.COUNT_ON_PAGE; i++, j++) {
//            if (i >= list.size()) {
//                break;
//            }
//            returnedList.add(list.get(i));
//        }
//        return returnedList;
//    }

    public List<Discount> findAllDiscounts() throws DaoException {
        List<Discount> discounts;
        try {
            discounts = DiscountDao.getInstance().findAll();
        } catch (DaoException e) {
            throw new DaoException(e);
        }
        return discounts;
    }
}

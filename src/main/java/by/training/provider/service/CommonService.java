package by.training.provider.service;

import by.training.provider.command.ParameterName;
import by.training.provider.dao.impl.DiscountDaoImpl;
import by.training.provider.dao.impl.TariffDaoImpl;
import by.training.provider.dao.impl.UserDataDaoImpl;
import by.training.provider.entity.Discount;
import by.training.provider.entity.Entity;
import by.training.provider.entity.Tariff;
import by.training.provider.entity.UserData;
import by.training.provider.exception.DaoException;
import by.training.provider.exception.LogicException;

import java.util.List;

public class CommonService {

    public List<Tariff> findAllTariffs() throws LogicException {
        List<Tariff> tariffs;
        try {
            tariffs = TariffDaoImpl.getInstance().findAll();
        } catch (DaoException e) {
            throw new LogicException(e);
        }
        return tariffs;
    }

    public List<?> divideListOnPage(List<? extends Entity> list, int pageNumber) { //TODO RETURNED TYPE
        List<? extends Entity> returnedList;
        if (pageNumber * ParameterName.COUNT_ON_PAGE + ParameterName.COUNT_ON_PAGE >= list.size()) {
            returnedList = list.subList(pageNumber * ParameterName.COUNT_ON_PAGE, list.size());
        } else {
            returnedList = list.subList(pageNumber * ParameterName.COUNT_ON_PAGE, pageNumber
                    * ParameterName.COUNT_ON_PAGE + ParameterName.COUNT_ON_PAGE);
        }
        return returnedList;
    }

    public List<Discount> findAllDiscounts() throws LogicException {
        List<Discount> discounts;
        try {
            discounts = DiscountDaoImpl.getInstance().findAll();
        } catch (DaoException e) {
            throw new LogicException(e);
        }
        return discounts;
    }

    public int pageCount(List<? extends Entity> list){
        int pageCount;
        if (list.size() % ParameterName.COUNT_ON_PAGE == 0){
            pageCount = list.size() / ParameterName.COUNT_ON_PAGE;
        }else {
            pageCount = list.size() / ParameterName.COUNT_ON_PAGE + 1;
        }
        return pageCount;
    }

    public UserData findUserData(int userId) throws LogicException{
        UserData userData;
        try {
            List<UserData> userDataList = UserDataDaoImpl.getInstance().findUserDataByUserId(userId);
            userData = userDataList.get(0);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
        return userData;
    }
}

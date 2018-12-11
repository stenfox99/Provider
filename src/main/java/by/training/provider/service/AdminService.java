package by.training.provider.service;

import by.training.provider.dao.impl.DiscountDao;
import by.training.provider.dao.impl.TariffDao;
import by.training.provider.dao.impl.UserDao;
import by.training.provider.dao.impl.UserDataDao;
import by.training.provider.entity.Discount;
import by.training.provider.util.DiscountValidator;
import by.training.provider.util.Encrypt;
import by.training.provider.entity.Tariff;
import by.training.provider.entity.User;
import by.training.provider.entity.UserData;
import by.training.provider.exception.LogicException;
import by.training.provider.exception.DaoException;
import by.training.provider.util.TariffValidator;
import by.training.provider.util.UserValidator;

import java.util.List;

public class AdminService {

    public void addUser(User user) throws LogicException {
        if (!UserValidator.validLogin(user.getLogin()) || !UserValidator.validPassword(user.getPassword())){
            throw new LogicException("The incorrect input data");
        }
        try {
            List<User> users = UserDao.getInstance().findUserByLogin(user.getLogin());
            if (users.isEmpty()){
                user.setPassword(Encrypt.encrypt(user.getPassword()));
                UserDao.getInstance().add(user);
                List<User> dbUser = UserDao.getInstance().findUserByLogin(user.getLogin());
                UserData userData = new UserData(dbUser.get(0).getUserId());
                UserDataDao.getInstance().add(userData);
            }else{
                throw new LogicException("This login already exists");
            }
        }catch (DaoException e){
            throw new LogicException(e);
        }

    }

    public void addAdmin(User user) throws LogicException {
        if (!UserValidator.validLogin(user.getLogin())|| !UserValidator.validPassword(user.getPassword())){
            throw new LogicException("The incorrect input data");
        }
        try {
            List<User> users = UserDao.getInstance().findUserByLogin(user.getLogin());
            if (users.isEmpty()){
                user.setPassword(Encrypt.encrypt(user.getPassword()));
                UserDao.getInstance().add(user);
            }else{
                throw new LogicException("This login already exists");
            }
        }catch (DaoException e){
            throw new LogicException(e);
        }
    }

    public void addTariff(Tariff tariff) throws LogicException {
        if (!TariffValidator.validTariffName(tariff.getName()) || !TariffValidator.validPrice(tariff.getPrice()) ||
                !TariffValidator.validDescription(tariff.getDescription()) || !TariffValidator.validTraffic(tariff.getMonthTraffic())){
            throw new LogicException("Incorrect input data");
        }
        try{
            List<Tariff> existedTariff = TariffDao.getInstance().findByName(tariff.getName());
            if (existedTariff.isEmpty()){
                TariffDao.getInstance().add(tariff);
            }else{
                throw new LogicException("This name already exists");
            }
        }catch (DaoException e){
            throw new LogicException(e);
        }
    }

    public void removeTariff(String tariffName)throws LogicException {
        Tariff tariff = new Tariff(tariffName);
        try {
            TariffDao.getInstance().remove(tariff);
        }catch (DaoException e){
            throw new LogicException(e);
        }
    }

    public void updateTariff(Tariff tariff)throws LogicException {
        if (!TariffValidator.validTariffName(tariff.getName()) || !TariffValidator.validPrice(tariff.getPrice()) ||
                !TariffValidator.validDescription(tariff.getDescription()) || !TariffValidator.validTraffic(tariff.getMonthTraffic())){
            throw new LogicException("Incorrect input data");
        }
        try{
            TariffDao.getInstance().update(tariff);
        }catch (DaoException e){
            throw new LogicException(e);
        }
    }

    public void addDiscount(Discount discount) throws LogicException{
        if (!DiscountValidator.validDiscountName(discount.getName()) || !DiscountValidator.validDescription(discount.getDescription())
                || !DiscountValidator.validDiscountValue(discount.getDiscount()) || discount.getBeginningDate().compareTo(discount.getEndDate()) > 0){
            throw new LogicException("Incorrect input data");
        }
        try{
            List<Discount> existedDiscounts = DiscountDao.getInstance().findByName(discount.getName());
            if (existedDiscounts.isEmpty()){
                List<Tariff> tariff = TariffDao.getInstance().findByName(discount.getTariff().getName());
                discount.getTariff().setTariffId(tariff.get(0).getTariffId());
                DiscountDao.getInstance().add(discount);
            }else{
                throw new LogicException("This name already exists");
            }
        }catch (DaoException e){
            throw new LogicException(e);
        }
    }

    public void removeDiscount(Discount discount) throws LogicException {
        try {
            DiscountDao.getInstance().remove(discount);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public void updateDiscount(Discount discount) throws LogicException{
        if (!DiscountValidator.validDiscountName(discount.getName()) || !DiscountValidator.validDescription(discount.getDescription())
                || !DiscountValidator.validDiscountValue(discount.getDiscount()) || discount.getBeginningDate().compareTo(discount.getEndDate()) > 0){
            throw new LogicException("Incorrect input data");
        }
        try{
            List<Tariff> tariff = TariffDao.getInstance().findByName(discount.getTariff().getName());
            discount.getTariff().setTariffId(tariff.get(0).getTariffId());
            DiscountDao.getInstance().update(discount);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }
}

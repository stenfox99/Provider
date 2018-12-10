package by.training.provider.service;

import by.training.provider.dao.impl.TariffDao;
import by.training.provider.dao.impl.UserDao;
import by.training.provider.dao.impl.UserDataDao;
import by.training.provider.util.Encrypt;
import by.training.provider.entity.Tariff;
import by.training.provider.entity.User;
import by.training.provider.entity.UserData;
import by.training.provider.exception.BusinessLogicException;
import by.training.provider.exception.DaoException;
import by.training.provider.util.TariffValidator;
import by.training.provider.util.UserValidator;

import java.util.List;

public class AdminService {

    public void addUser(User user) throws BusinessLogicException {
        if (!UserValidator.validLogin(user.getLogin()) || !UserValidator.validPassword(user.getPassword())){
            throw new BusinessLogicException("The incorrect input data");
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
                throw new BusinessLogicException("This login already exists");
            }
        }catch (DaoException e){
            throw new BusinessLogicException(e);
        }

    }

    public void addAdmin(User user) throws BusinessLogicException{
        if (!UserValidator.validLogin(user.getLogin())|| !UserValidator.validPassword(user.getPassword())){
            throw new BusinessLogicException("The incorrect input data");
        }
        try {
            List<User> users = UserDao.getInstance().findUserByLogin(user.getLogin());
            if (users.isEmpty()){
                user.setPassword(Encrypt.encrypt(user.getPassword()));
                UserDao.getInstance().add(user);
            }else{
                throw new BusinessLogicException("This login already exists");
            }
        }catch (DaoException e){
            throw new BusinessLogicException(e);
        }
    }

    public void addTariff(Tariff tariff) throws BusinessLogicException{
        if (!TariffValidator.validTariff(tariff.getName()) || !TariffValidator.validPrice(tariff.getPrice()) ||
                !TariffValidator.validDescription(tariff.getDescription()) || !TariffValidator.validTraffic(tariff.getMonthTraffic())){
            throw new BusinessLogicException("Incorrect input data");
        }
        try{
            List<Tariff> existedTariff = TariffDao.getInstance().findTariffByName(tariff.getName());
            if (existedTariff.isEmpty()){
                TariffDao.getInstance().add(tariff);
            }else{
                throw new BusinessLogicException("This name already exists");
            }
        }catch (DaoException e){
            throw new BusinessLogicException(e);
        }
    }

    public void removeTariff(String tariffName)throws BusinessLogicException{
        Tariff tariff = new Tariff(tariffName);
        try {
            TariffDao.getInstance().remove(tariff);
        }catch (DaoException e){
            throw new BusinessLogicException(e);
        }
    }

    public void updateTariff(Tariff tariff)throws BusinessLogicException{
        if (!TariffValidator.validTariff(tariff.getName()) || !TariffValidator.validPrice(tariff.getPrice()) ||
                !TariffValidator.validDescription(tariff.getDescription()) || !TariffValidator.validTraffic(tariff.getMonthTraffic())){
            throw new BusinessLogicException("Incorrect input data");
        }
        try{
            TariffDao.getInstance().update(tariff);
        }catch (DaoException e){
            throw new BusinessLogicException(e);
        }
    }
}

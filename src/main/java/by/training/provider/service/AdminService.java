package by.training.provider.service;

import by.training.provider.dao.impl.UserDao;
import by.training.provider.entity.User;
import by.training.provider.exception.BusinessLogicException;
import by.training.provider.exception.DaoException;

import java.util.List;

public class AdminService {

    public void addUser(User user) throws BusinessLogicException {
        if (!UserValidator.checkLogin(user.getLogin()) || UserValidator.checkPassword(user.getPassword())){
            throw new BusinessLogicException("The incorrect input data");
        }
        try {
            List<User> users = UserDao.getInstance().findUserByLogin(user.getLogin());

        }catch (DaoException e){

        }

    }

    public void addAdmin(User user) throws BusinessLogicException{
        if (!UserValidator.checkLogin(user.getLogin())|| UserValidator.checkPassword(user.getPassword())){
            throw new BusinessLogicException("The incorrect input data");
        }
        try {
            List<User> users = UserDao.getInstance().findUserByLogin(user.getLogin());
            if ()
        }catch (DaoException e){

        }

    }
}

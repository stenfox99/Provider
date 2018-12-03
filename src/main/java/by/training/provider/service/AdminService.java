package by.training.provider.service;

import by.training.provider.dao.impl.UserDao;
import by.training.provider.dao.impl.UserDataDao;
import by.training.provider.entity.User;
import by.training.provider.entity.UserData;
import by.training.provider.exception.BusinessLogicException;
import by.training.provider.exception.DaoException;

import java.util.List;

public class AdminService {

    public void addUser(User user) throws BusinessLogicException {              //TODO USER ID
        if (!UserValidator.checkLogin(user.getLogin()) || !UserValidator.checkPassword(user.getPassword())){
            throw new BusinessLogicException("The incorrect input data");
        }
        try {
            List<User> users = UserDao.getInstance().findUserByLogin(user.getLogin());
            if (users.isEmpty()){
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
        if (!UserValidator.checkLogin(user.getLogin())|| UserValidator.checkPassword(user.getPassword())){
            throw new BusinessLogicException("The incorrect input data");
        }
        try {
            List<User> users = UserDao.getInstance().findUserByLogin(user.getLogin());
            if (users.isEmpty()){
                UserDao.getInstance().add(user);
            }else{
                throw new BusinessLogicException("This login already exists");
            }
        }catch (DaoException e){
            throw new BusinessLogicException(e);
        }

    }
}

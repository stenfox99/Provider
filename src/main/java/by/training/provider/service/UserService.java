package by.training.provider.service;

import by.training.provider.dao.impl.UserDao;
import by.training.provider.encrypt.Encrypt;
import by.training.provider.entity.User;
import by.training.provider.exception.BusinessLogicException;
import by.training.provider.exception.DaoException;

import java.util.List;
import java.util.Optional;

public class UserService {

    public Optional<User> findUser(String login, String password) throws BusinessLogicException {
        Optional<User> user = Optional.empty();
        try {
            String encryptPassword = Encrypt.encrypt(password);
            List<User> users = UserDao.getInstance().findUserByLoginAndPassword(login, encryptPassword);
            if (!users.isEmpty()) {
                user = Optional.of(users.get(0));
            }
        } catch (DaoException e) {
            throw new BusinessLogicException(e);
        }
        return user;
    }

    public void updateUser(int userId, String password) throws BusinessLogicException{
        try {
            String encryptPassword = Encrypt.encrypt(password);
            User user = new User(userId, encryptPassword);
            UserDao.getInstance().update(user);
        }catch (DaoException e){
            throw new BusinessLogicException(e);
        }

    }
}

package by.training.provider.service;

import by.training.provider.dao.impl.UserDaoImpl;
import by.training.provider.util.Encrypt;
import by.training.provider.entity.User;
import by.training.provider.exception.LogicException;
import by.training.provider.exception.DaoException;

import java.util.List;
import java.util.Optional;

public class UserService {

    public Optional<User> findUser(String login, String password) throws LogicException {
        Optional<User> user = Optional.empty();
        try {
            String encryptPassword = Encrypt.encrypt(password);
            List<User> users = UserDaoImpl.getInstance().findUserByLoginAndPassword(login, encryptPassword);
            if (!users.isEmpty()) {
                user = Optional.of(users.get(0));
            }
        } catch (DaoException e) {
            throw new LogicException(e);
        }
        return user;
    }

    public void updateUser(String login, String password) throws LogicException {
        try {
            String encryptPassword = Encrypt.encrypt(password);
            User user = new User(login, encryptPassword);
            UserDaoImpl.getInstance().update(user);
        }catch (DaoException e){
            throw new LogicException(e);
        }

    }
}

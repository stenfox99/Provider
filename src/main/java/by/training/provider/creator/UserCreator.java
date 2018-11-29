package by.training.provider.creator;

import by.training.provider.encrypt.Encrypt;
import by.training.provider.entity.User;
import by.training.provider.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserCreator {
    public static List<User> createUsers(ResultSet resultUsers) throws DaoException {
        List<User> users = new ArrayList<>();
        try {
            while (resultUsers.next()) {
                int id = resultUsers.getInt(1);
                String login = resultUsers.getString(2);
                String password = Encrypt.decrypt(resultUsers.getString(3));
                int userTypeId = resultUsers.getInt(4);
                int userDataId = resultUsers.getInt(5);
                User user = new User(id, login, password, userTypeId, userDataId);
                users.add(user);
            }
        }catch (SQLException e){
            throw new DaoException("Can't interpret result set to user", e);
        }
        return users;
    }
}

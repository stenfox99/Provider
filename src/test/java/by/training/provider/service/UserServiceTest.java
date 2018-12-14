package by.training.provider.service;

import by.training.provider.entity.User;
import by.training.provider.entity.UserType;
import by.training.provider.exception.LogicException;
import org.testng.annotations.Test;

public class UserServiceTest {

    @Test
    public void updateUserTest() throws LogicException {
        User user = new User(1, "user2", "user2", new UserType(1, "admin"));
        UserService service = new UserService();
        service.updateUserPassword(user.getLogin(), user.getPassword());
    }
}

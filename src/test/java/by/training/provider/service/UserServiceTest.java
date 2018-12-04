package by.training.provider.service;

import by.training.provider.entity.User;
import by.training.provider.entity.UserType;
import by.training.provider.exception.BusinessLogicException;
import org.testng.annotations.Test;

public class UserServiceTest {

    @Test
    public void updateUserTest() throws BusinessLogicException {
        User user = new User(1, "user2", "user2", new UserType(1, "admin"));
        UserService service = new UserService();
        service.updateUser(user.getLogin(), user.getPassword());
    }
}

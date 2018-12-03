package by.training.provider.service;

import by.training.provider.entity.User;
import by.training.provider.entity.UserType;
import by.training.provider.exception.BusinessLogicException;
import org.testng.annotations.Test;

public class UserServiceTest {

    @Test
    public void updateUserTest() throws BusinessLogicException {
        User user = new User(7, "user1", "updateTest1", new UserType(2, "user"));
        UserService service = new UserService();
        service.updateUser(user.getUserId(), user.getPassword());
    }
}

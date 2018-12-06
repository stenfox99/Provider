package by.training.provider.dao;

import by.training.provider.entity.User;
import by.training.provider.entity.UserType;
import by.training.provider.exception.BusinessLogicException;
import by.training.provider.service.UserService;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Optional;

public class UserDaoTest {

    @Test
    public void checkUser() {
        String login = "admin";
        String pas = "admin";
        UserService service = new UserService();
        Optional<User> userOptional = Optional.empty();
        try {
            userOptional = service.findUser(login, pas);
        } catch (BusinessLogicException e) {
            e.printStackTrace();
        }
        User expected = new User(5, "admin", null, new UserType(1, "admin"));
        Assert.assertEquals(userOptional.get(), expected);
    }
}

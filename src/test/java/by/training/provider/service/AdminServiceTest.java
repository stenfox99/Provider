package by.training.provider.service;

import by.training.provider.entity.User;
import by.training.provider.entity.UserType;
import by.training.provider.exception.BusinessLogicException;
import org.testng.annotations.Test;

public class AdminServiceTest {

    @Test(expectedExceptions = BusinessLogicException.class)
    public void addUser() throws BusinessLogicException {
        User user = new User( 0, "admin", "al89", new UserType(2));
        AdminService service = new AdminService();
        service.addUser(user);
    }
}

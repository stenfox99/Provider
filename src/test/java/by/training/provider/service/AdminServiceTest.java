package by.training.provider.service;

import by.training.provider.entity.Tariff;
import by.training.provider.entity.User;
import by.training.provider.entity.UserType;
import by.training.provider.exception.BusinessLogicException;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class AdminServiceTest {

    @Test
    public void addUser() throws BusinessLogicException {
        User user = new User( 0, "user3", "qwedsa", new UserType(2));
        AdminService service = new AdminService();
        service.addUser(user);
    }

    @Test
    public void addAdmin() throws BusinessLogicException {
        User user = new User( 0, "admin", "admin", new UserType(1));
        AdminService service = new AdminService();
        service.addAdmin(user);
    }

    @Test
    public void addTariff() throws BusinessLogicException{
        Tariff tariff = new Tariff("tariff1", BigDecimal.valueOf(30), "description");
        AdminService service = new AdminService();
        service.addTariff(tariff);
    }
}

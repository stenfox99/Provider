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
        User user = new User(0, "user1", "user11", new UserType(2));
        AdminService service = new AdminService();
        service.addUser(user);
    }

    @Test
    public void addAdmin() throws BusinessLogicException {
        User user = new User(0, "admin", "mainAdmin", new UserType(1));
        AdminService service = new AdminService();
        service.addAdmin(user);
    }

    @Test
    public void addTariff() throws BusinessLogicException {
        Tariff tariff = new Tariff("tariff15", BigDecimal.valueOf(30), 10000, "The best tariff in the world");
        AdminService service = new AdminService();
        service.addTariff(tariff);
    }

    @Test
    public void removeTariff() throws BusinessLogicException {
        AdminService service = new AdminService();
        service.removeTariff("tariff1");
    }

    @Test
    public void updateTariff() throws BusinessLogicException {
        Tariff tariff = new Tariff(2, "tariff2", BigDecimal.valueOf(10), 30000,"the best tariff");
        AdminService service = new AdminService();
        service.updateTariff(tariff);
    }
}

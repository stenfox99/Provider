package by.training.provider.service;

import by.training.provider.entity.Tariff;
import by.training.provider.entity.User;
import by.training.provider.entity.UserType;
import by.training.provider.exception.LogicException;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class AdminServiceTest {

    @Test
    public void addUser() throws LogicException {
        User user = new User(1, "user3", "user33", new UserType(2));
        AdminService service = new AdminService();
        service.addUser(user);
    }

    @Test
    public void addAdmin() throws LogicException {
        User user = new User(2, "admin", "mainAdmin", new UserType(1));
        AdminService service = new AdminService();
        service.addAdmin(user);
    }

    @Test
    public void addTariff() throws LogicException {
        Tariff tariff = new Tariff("tariff15", BigDecimal.valueOf(30), 10000, "The best tariff in the world");
        AdminService service = new AdminService();
        service.addTariff(tariff);
    }

    @Test
    public void removeTariff() throws LogicException {
        AdminService service = new AdminService();
        service.removeTariff("tariff1");
    }
}

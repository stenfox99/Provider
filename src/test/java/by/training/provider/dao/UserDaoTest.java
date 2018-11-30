package by.training.provider.dao;

import by.training.provider.entity.User;
import org.testng.annotations.Test;

public class UserDaoTest {

    @Test
    public void addUser(){
        User user = new User(0, "login", "password", 2,1);

    }
}

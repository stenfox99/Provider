package by.training.provider.dao;

import by.training.provider.dao.impl.UserTypeDaoImpl;
import by.training.provider.entity.UserType;
import by.training.provider.exception.DaoException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class UserTypeDaoTest {

    @Test
    public void findAllTest() throws DaoException {
        List<UserType> userTypes = UserTypeDaoImpl.getInstance().findAll();
        int actual = userTypes.size();
        int expected = 2;
        Assert.assertEquals(expected, actual);
    }
}

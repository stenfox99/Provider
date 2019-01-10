package by.training.provider.util;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserValidatorTest {

    @Test(dataProvider = "names")
    public void initialValidateTest(String login, boolean expected){
        boolean actual = UserValidator.validLogin(login);
        Assert.assertEquals(expected, actual);
    }

    @DataProvider(name = "names")
    private Object[][] names(){
        return new Object[][]{
                {"StenFox", true},
                {"4", false},
                {"Alexandr", true},
                {"", false},
                {"<script>", false},
                {"Yan321", true},
                {"name w", false}
        };
    }

    @Test(dataProvider = "passwords")
    public void validPasswordTest(String password, boolean expected){
        boolean actual = UserValidator.validPassword(password);
        Assert.assertEquals(expected, actual);
    }

    @DataProvider(name = "passwords")
    private Object[][] passwords(){
        return new Object[][]{
                {"q3256179asd", true},
                {"4", false},
                {"123456789", true},
                {"", false},
                {"<script>", false},
                {"pazxcas", true},
                {"pas wqe", false}
        };
    }
}

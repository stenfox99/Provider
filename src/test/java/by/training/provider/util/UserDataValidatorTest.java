package by.training.provider.util;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserDataValidatorTest {

    @Test(dataProvider = "initials")
    public void initialValidateTest(String initial, boolean expected){
        boolean actual = UserDataValidator.validInitial(initial);
        Assert.assertEquals(expected, actual);
    }

    @DataProvider(name = "initials")
    private Object[][] initials(){
        return new Object[][]{
                {"Alexandr", true},
                {"4123", false},
                {"Polovinkin", true},
                {"", true},
                {"<script>", false},
                {"Yan", true},
                {"name w", false}
        };
    }

    @Test(dataProvider = "emails")
    public void emailValidateTest(String email, boolean expected){
        boolean actual = UserDataValidator.validEmail(email);
        Assert.assertEquals(expected, actual);
    }

    @DataProvider(name = "emails")
    private Object[][] emails(){
        return new Object[][]{
                {"polovinkin@gmail.com", true},
                {"4123", false},
                {"sad@mail.ru", true},
                {"", true},
                {"<script>", false},
                {"dsaewqe123@gmail.com", true},
                {"uhqiwe@gmail.ru", false}
        };
    }

    @Test(dataProvider = "phones")
    public void phoneValidateTest(String phone, boolean expected){
        boolean actual = UserDataValidator.validPhone(phone);
        Assert.assertEquals(expected, actual);
    }

    @DataProvider(name = "phones")
    private Object[][] phones(){
        return new Object[][]{
                {"+375296700901", true},
                {"4123", false},
                {"+354891246721", true},
                {"", true},
                {"<script>", false},
                {"+", false}
        };
    }
}

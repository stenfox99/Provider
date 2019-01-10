package by.training.provider.util;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class EncryptTest {

    @Test(dataProvider = "passwords")
    public void encryptTest(String password, String expected){
        String actual = Encrypt.encrypt(password);
        Assert.assertEquals(expected, actual);
    }

    @DataProvider(name = "passwords")
    private Object[][] passwords(){
        return new Object[][]{
                {"q23241asdF", "cTIzMjQxYXNkRg=="},
                {"1230851", "MTIzMDg1MQ=="},
                {"vjcxnjdf", "dmpjeG5qZGY="},
        };
    }
}

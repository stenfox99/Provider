package by.training.provider.util;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class TariffValidatorTest {

    @Test(dataProvider = "tariffNames")
    public void validTariffNameTest(String name, boolean expected){
        boolean actual = TariffValidator.validTariffName(name);
        Assert.assertEquals(expected, actual);
    }

    @DataProvider(name = "tariffNames")
    private Object[][] tariffNames(){
        return new Object[][]{
                {"bright", true},
                {"00", false},
                {"bright2", true},
                {"4123422", true},
                {"<script>", false},
                {"Hot tariff", true},
                {"tariff 3 !", false}
        };
    }

    @Test(dataProvider = "prices")
    public void validPriceTest(BigDecimal price, boolean expected){
        boolean actual = TariffValidator.validPrice(price);
        Assert.assertEquals(expected, actual);
    }

    @DataProvider(name = "prices")
    private Object[][] prices(){
        return new Object[][]{
                {new BigDecimal(30), true},
                {new BigDecimal(-20), false},
                {new BigDecimal(15), true},
                {new BigDecimal(512321312.4), false},
                {new BigDecimal(32.124124512312), false},
                {new BigDecimal(1.1), false}
        };
    }

    @Test(dataProvider = "descriptions")
    public void validDescriptionTest(String description, boolean expected){
        boolean actual = TariffValidator.validDescription(description);
        Assert.assertEquals(expected, actual);
    }

    @DataProvider(name = "descriptions")
    private Object[][] descriptions(){
        return new Object[][]{
                {"The tariff for people who don's like to spent a lot of time for the computer", true},
                {"<script>alert('hello');</script>", false},
                {"421312", true},
                {"The tariff for gamers", true},
                {"", false},
                {"The best tariff", true},
                {"<>", false}
        };
    }

    @Test(dataProvider = "traffics")
    public void validDiscountValueTest(int traffic, boolean expected){
        boolean actual = TariffValidator.validTraffic(traffic);
        Assert.assertEquals(expected, actual);
    }

    @DataProvider(name = "traffics")
    private Object[][] traffics(){
        return new Object[][]{
                {255123, true},
                {1202312312, false},
                {10423, true},
                {2314, true},
                {-20, false},
                {9932, true},
                {-3213213, false}
        };
    }
}

package by.training.provider.util;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DiscountValidatorTest {

    @Test(dataProvider = "discountNames")
    public void validDiscountNameTest(String name, boolean expected){
        boolean actual = DiscountValidator.validDiscountName(name);
        Assert.assertEquals(expected, actual);
    }

    @DataProvider(name = "discountNames")
    private Object[][] discountNames(){
        return new Object[][]{
                {"week discount", true},
                {"00", false},
                {"bright2", true},
                {"4123422", true},
                {"<script>", false},
                {"Hot discount", true},
                {"tariff 3 !", false}
        };
    }

    @Test(dataProvider = "discountValues")
    public void validDiscountValueTest(int discount, boolean expected){
        boolean actual = DiscountValidator.validDiscountValue(discount);
        Assert.assertEquals(expected, actual);
    }

    @DataProvider(name = "discountValues")
    private Object[][] discountValues(){
        return new Object[][]{
                {25, true},
                {120, false},
                {10, true},
                {23, true},
                {-20, false},
                {99, true},
                {-4, false}
        };
    }

    @Test(dataProvider = "descriptions")
    public void validDescriptionTest(String description, boolean expected){
        boolean actual = DiscountValidator.validDescription(description);
        Assert.assertEquals(expected, actual);
    }

    @DataProvider(name = "descriptions")
    private Object[][] descriptions(){
        return new Object[][]{
                {"Only for this week!", true},
                {"<script>alert('hello');</script>", false},
                {"421312", true},
                {"You will never see this opportunity", true},
                {"", false},
                {"It's discount", true},
                {"<>", false}
        };
    }
}

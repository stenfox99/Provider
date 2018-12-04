package by.training.provider.service;

import java.math.BigDecimal;

public class TariffValidator {          //TODO ONE VALIDATOR OR FOR EVERY ENTITY
    private static final String TARIFF_NAME_VALIDATOR = "[\\w\\d]{6,20}";
    private static final String TARIFF_PRICE_VALIDATOR = "[\\d]{1,6}";
    private static final String TARIFF_DESCRIPTION_VALIDATOR = "[\\w\\d]{1,45}";

    static boolean validTariff(String tariffName){
        return tariffName.matches(TARIFF_NAME_VALIDATOR);
    }

    static boolean validPrice(BigDecimal price){
        return price.toString().matches(TARIFF_PRICE_VALIDATOR);
    }

    static boolean validDescription(String description){
        return description.matches(TARIFF_DESCRIPTION_VALIDATOR);
    }
}

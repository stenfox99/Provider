package by.training.provider.util;

import java.math.BigDecimal;

public class TariffValidator {
    private static final String TARIFF_NAME_VALIDATOR = "[\\w\\d\\sа-яА-Я]{3,20}";
    private static final String TARIFF_PRICE_VALIDATOR = "\\d{1,6}\\.\\d{2}|\\d{1,6}\\.\\d";
    private static final String TARIFF_TRAFFIC_VALIDATOR = "[\\d]{1,9}";
    private static final String TARIFF_DESCRIPTION_VALIDATOR = "[\\w\\d\\s\\nа-яА-Я',.!?]{1,255}";
    private static final String CSS_ATTACK_VALIDATOR = "</?script>";

    public static boolean validTariffName(String tariffName){
        return tariffName.matches(TARIFF_NAME_VALIDATOR) && !tariffName.matches(CSS_ATTACK_VALIDATOR);
    }

    public static boolean validPrice(BigDecimal price){
        return price.toString().matches(TARIFF_PRICE_VALIDATOR);
    }

    public static boolean validDescription(String description){
        return description.matches(TARIFF_DESCRIPTION_VALIDATOR) && !description.matches(CSS_ATTACK_VALIDATOR);
    }

    public static boolean validTraffic(int traffic){
        return String.valueOf(traffic).matches(TARIFF_TRAFFIC_VALIDATOR);
    }
}

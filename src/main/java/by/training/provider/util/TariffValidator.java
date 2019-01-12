package by.training.provider.util;

import java.math.BigDecimal;

/**
 * The Class TariffValidator.
 */
public class TariffValidator {
    
    /** The Constant TARIFF_NAME_VALIDATOR. */
    private static final String TARIFF_NAME_VALIDATOR = "[\\w\\d\\sа-яА-Я]{3,20}";
    
    /** The Constant TARIFF_PRICE_VALIDATOR. */
    private static final String TARIFF_PRICE_VALIDATOR = "\\d{1,6}\\.\\d{2}|\\d{1,6}.\\d";
    
    /** The Constant TARIFF_TRAFFIC_VALIDATOR. */
    private static final String TARIFF_TRAFFIC_VALIDATOR = "[\\d]{1,9}";
    
    /** The Constant TARIFF_DESCRIPTION_VALIDATOR. */
    private static final String TARIFF_DESCRIPTION_VALIDATOR = "[\\w\\d\\s\\nа-яА-Я',.!?]{1,255}";
    
    /** The Constant XSS_ATTACK_VALIDATOR. */
    private static final String XSS_ATTACK_VALIDATOR = "</?script>";

    /**
     * Instantiates a new tariff validator.
     */
    private TariffValidator(){}

    /**
     * Valid tariff name.
     *
     * @param tariffName the tariff name
     * @return true, if successful
     */
    public static boolean validTariffName(String tariffName){
        return tariffName.matches(TARIFF_NAME_VALIDATOR) && !tariffName.matches(XSS_ATTACK_VALIDATOR);
    }

    /**
     * Valid price.
     *
     * @param price the price
     * @return true, if successful
     */
    public static boolean validPrice(BigDecimal price){
        return price.toString().matches(TARIFF_PRICE_VALIDATOR);
    }

    /**
     * Valid description.
     *
     * @param description the description
     * @return true, if successful
     */
    public static boolean validDescription(String description){
        return description.matches(TARIFF_DESCRIPTION_VALIDATOR) && !description.matches(XSS_ATTACK_VALIDATOR);
    }

    /**
     * Valid traffic.
     *
     * @param traffic the traffic
     * @return true, if successful
     */
    public static boolean validTraffic(int traffic){
        return String.valueOf(traffic).matches(TARIFF_TRAFFIC_VALIDATOR);
    }
}

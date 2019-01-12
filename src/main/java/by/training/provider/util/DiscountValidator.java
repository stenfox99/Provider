package by.training.provider.util;

/**
 * The Class DiscountValidator.
 */
public class DiscountValidator {
    
    /** The Constant DISCOUNT_NAME_VALIDATOR. */
    private static final String DISCOUNT_NAME_VALIDATOR = "[\\w\\d\\s]{6,20}";
    
    /** The Constant DISCOUNT_DISCOUNT_VALIDATOR. */
    private static final String DISCOUNT_DISCOUNT_VALIDATOR = "[\\d]{1,4}";
    
    /** The Constant DISCOUNT_DESCRIPTION_VALIDATOR. */
    private static final String DISCOUNT_DESCRIPTION_VALIDATOR = "[\\w\\d\\s\\n',.!?]{1,255}";
    
    /** The Constant XSS_ATTACK_VALIDATOR. */
    private static final String XSS_ATTACK_VALIDATOR = "</?script>";

    /**
     * Instantiates a new discount validator.
     */
    private DiscountValidator(){}

    /**
     * Valid discount name.
     *
     * @param discountName the discount name
     * @return true, if successful
     */
    public static boolean validDiscountName(String discountName) {
        return discountName.matches(DISCOUNT_NAME_VALIDATOR) && !discountName.matches(XSS_ATTACK_VALIDATOR);
    }

    /**
     * Valid discount value.
     *
     * @param discount the discount
     * @return true, if successful
     */
    public static boolean validDiscountValue(int discount) {
        return String.valueOf(discount).matches(DISCOUNT_DISCOUNT_VALIDATOR) && discount >= 0 && discount <= 100;
    }

    /**
     * Valid description.
     *
     * @param description the description
     * @return true, if successful
     */
    public static boolean validDescription(String description) {
        return description.matches(DISCOUNT_DESCRIPTION_VALIDATOR) && !description.matches(XSS_ATTACK_VALIDATOR);
    }
}

package by.training.provider.util;

public class DiscountValidator {
    private static final String DISCOUNT_NAME_VALIDATOR = "[\\w\\d\\s]{6,20}";
    private static final String DISCOUNT_DISCOUNT_VALIDATOR = "[\\d]{1,4}";
    private static final String DISCOUNT_DESCRIPTION_VALIDATOR = "[\\w\\d\\s\\n',.!?]{1,255}";
    private static final String XSS_ATTACK_VALIDATOR = "</?script>";

    public static boolean validDiscountName(String discountName) {
        return discountName.matches(DISCOUNT_NAME_VALIDATOR) && !discountName.matches(XSS_ATTACK_VALIDATOR);
    }

    public static boolean validDiscountValue(int discount) {
        return String.valueOf(discount).matches(DISCOUNT_DISCOUNT_VALIDATOR) && discount >= 0 && discount <= 100;
    }

    public static boolean validDescription(String description) {
        return description.matches(DISCOUNT_DESCRIPTION_VALIDATOR) && !description.matches(XSS_ATTACK_VALIDATOR);
    }
}

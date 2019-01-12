package by.training.provider.util;

/**
 * The Class UserDataValidator.
 */
public class UserDataValidator {
    
    /** The Constant INITIALS_VALIDATOR. */
    private static final String INITIALS_VALIDATOR = "[a-zA-Z]{2,12}";
    
    /** The Constant EMAIL_VALIDATOR. */
    private static final String EMAIL_VALIDATOR = "[\\w\\d]+@mail\\.ru|[\\w\\d]+@gmail\\.com";
    
    /** The Constant PHONE_VALIDATOR. */
    private static final String PHONE_VALIDATOR = "\\+\\d{12}";
    
    /** The Constant XSS_ATTACK_VALIDATOR. */
    private static final String XSS_ATTACK_VALIDATOR = "</?script>";

    /**
     * Instantiates a new user data validator.
     */
    private UserDataValidator(){}

    /**
     * Valid initial.
     *
     * @param initial the initial
     * @return true, if successful
     */
    public static boolean validInitial(String initial) {
        if (initial.isEmpty()) {
            return true;
        }
        return initial.matches(INITIALS_VALIDATOR) && !initial.matches(XSS_ATTACK_VALIDATOR);
    }

    /**
     * Valid email.
     *
     * @param email the email
     * @return true, if successful
     */
    public static boolean validEmail(String email) {
        if (email.isEmpty()) {
            return true;
        }
        return email.matches(EMAIL_VALIDATOR) && !email.matches(XSS_ATTACK_VALIDATOR);
    }

    /**
     * Valid phone.
     *
     * @param phone the phone
     * @return true, if successful
     */
    public static boolean validPhone(String phone) {
        if (phone.isEmpty()) {
            return true;
        }
        return phone.matches(PHONE_VALIDATOR);
    }
}

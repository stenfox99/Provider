package by.training.provider.util;

/**
 * The Class UserValidator.
 */
public class UserValidator {
    
    /** The Constant LOGIN_VALIDATOR. */
    private static final String LOGIN_VALIDATOR = "[\\w\\d]{4,16}";
    
    /** The Constant PASSWORD_VALIDATOR. */
    private static final String PASSWORD_VALIDATOR = "[\\w\\d]{6,20}";
    
    /** The Constant XSS_ATTACK_VALIDATOR. */
    private static final String XSS_ATTACK_VALIDATOR = "</?script>";

    /**
     * Instantiates a new user validator.
     */
    private UserValidator(){}

    /**
     * Valid login.
     *
     * @param login the login
     * @return true, if successful
     */
    public static boolean validLogin(String login) {
        return login.matches(LOGIN_VALIDATOR) && !login.matches(XSS_ATTACK_VALIDATOR);
    }

    /**
     * Valid password.
     *
     * @param password the password
     * @return true, if successful
     */
    public static boolean validPassword(String password){
        return password.matches(PASSWORD_VALIDATOR);
    }

    /**
     * Verify password.
     *
     * @param password the password
     * @param password2 the password 2
     * @return true, if successful
     */
    public static boolean verifyPassword(String password, String password2){
        return password.equals(password2);
    }
}

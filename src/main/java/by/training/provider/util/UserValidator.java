package by.training.provider.util;

public class UserValidator {
    private static final String LOGIN_VALIDATOR = "[\\w\\d]{4,16}";
    private static final String PASSWORD_VALIDATOR = "[\\w\\d]{6,20}";
    private static final String XSS_ATTACK_VALIDATOR = "</?script>";

    private UserValidator(){}

    public static boolean validLogin(String login) {
        return login.matches(LOGIN_VALIDATOR) && !login.matches(XSS_ATTACK_VALIDATOR);
    }

    public static boolean validPassword(String password){
        return password.matches(PASSWORD_VALIDATOR);
    }

    public static boolean verifyPassword(String password, String password2){
        return password.equals(password2);
    }
}

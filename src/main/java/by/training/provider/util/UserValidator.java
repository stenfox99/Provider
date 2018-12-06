package by.training.provider.util;

public class UserValidator {
    private static final String LOGIN_VALIDATOR = "[\\w\\d]{4,16}";      //TODO CSS ATTACK
    private static final String PASSWORD_VALIDATOR = "[\\w\\d]{6,20}";

    public static boolean validLogin(String login) {
        return login.matches(LOGIN_VALIDATOR);
    }

    public static boolean validPassword(String password){
        return password.matches(PASSWORD_VALIDATOR);
    }
}

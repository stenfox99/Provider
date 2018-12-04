package by.training.provider.service;

class UserValidator {
    private static final String LOGIN_VALIDATOR = "[\\w\\d]{4,16}";
    private static final String PASSWORD_VALIDATOR = "[\\w\\d]{6,20}";

    static boolean validLogin(String login) {
        return login.matches(LOGIN_VALIDATOR);
    }

    static boolean validPassword(String password){
        return password.matches(PASSWORD_VALIDATOR);
    }
}

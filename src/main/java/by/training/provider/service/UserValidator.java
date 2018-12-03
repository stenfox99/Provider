package by.training.provider.service;

class UserValidator {
    private static final String LOGIN_VALIDATOR = "\\b{4,16}";
    private static final String PASSWORD_VALIDATOR = "\\b{4,16}";

    static boolean checkLogin(String login) {
        return login.matches(LOGIN_VALIDATOR);
    }

    static boolean checkPassword(String password){
        return password.matches(PASSWORD_VALIDATOR);
    }
}

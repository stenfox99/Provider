package by.training.provider.util;

public class UserDataValidator {
    private static final String INITIALS_VALIDATOR = "[a-zA-Z]{2,12}";
    private static final String EMAIL_VALIDATOR = "[\\w\\d]+@mail\\.ru|[\\w\\d]+@gmail\\.com";
    private static final String PHONE_VALIDATOR = "\\+\\d{12}";
    private static final String XSS_ATTACK_VALIDATOR = "</?script>";

    public static boolean validInitial(String initial) {
        if (initial.isEmpty()) {
            return true;
        }
        return initial.matches(INITIALS_VALIDATOR) && !initial.matches(XSS_ATTACK_VALIDATOR);
    }

    public static boolean validEmail(String email) {
        if (email.isEmpty()) {
            return true;
        }
        return email.matches(EMAIL_VALIDATOR) && !email.matches(XSS_ATTACK_VALIDATOR);
    }

    public static boolean validPhone(String phone) {
        if (phone.isEmpty()) {
            return true;
        }
        return phone.matches(PHONE_VALIDATOR);
    }
}

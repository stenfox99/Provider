package by.training.provider.util;

public class UserDataValidator {
    private static final String INITIALS_VALIDATOR = "[a-zA-Z]{2,12}";
    private static final String EMAIL_VALIDATOR = "[\\w\\d]+@mail\\.ru|[\\w\\d]+@gmail\\.com";
    private static final String PHONE_VALIDATOR = "\\+\\d{12}";
    private static final String CSS_ATTACK_VALIDATOR = "</?script>";

    public static boolean initialValidate(String initial) {         //TODO VALID EMPTY
        if (initial.isEmpty()) return true;
        return initial.matches(INITIALS_VALIDATOR) && !initial.matches(CSS_ATTACK_VALIDATOR);
    }

    public static boolean emailValidate(String email) {
        if (email.isEmpty()) return true;
        return email.isEmpty() || email.matches(EMAIL_VALIDATOR) && !email.matches(CSS_ATTACK_VALIDATOR);
    }

    public static boolean phoneValidate(String phone) {
        if (phone.isEmpty()) return true;
        return phone.isEmpty() || phone.matches(PHONE_VALIDATOR);
    }
}

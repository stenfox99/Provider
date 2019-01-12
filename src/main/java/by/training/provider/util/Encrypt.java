package by.training.provider.util;

import java.util.Base64;

/**
 * The Class Encrypt.
 */
public class Encrypt {

    /**
     * Instantiates a new encrypt.
     */
    private Encrypt() {
    }

    /**
     * Encrypt.
     *
     * @param value the value
     * @return the string
     */
    public static String encrypt(String value) {
        return new String(Base64.getEncoder().encode(value.getBytes()));
    }
}

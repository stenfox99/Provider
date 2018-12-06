package by.training.provider.util;


import java.util.Base64;

public class Encrypt {

    private Encrypt() {
    }

    public static String encrypt(String value) {
        return new String(Base64.getEncoder().encode(value.getBytes()));
    }

    public static String decrypt(String value) {
        return new String(Base64.getDecoder().decode(value.getBytes()));
    }
}

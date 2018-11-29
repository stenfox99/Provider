package by.training.provider.encrypt;

import org.apache.commons.codec.binary.Base64;

public class Encrypt {

    private Encrypt() {
    }

    public static String encrypt(String value) {
        return new String(Base64.encodeBase64(value.getBytes()));
    }

    public static String decrypt(String value) {
        return new String(Base64.decodeBase64(value.getBytes()));
    }
}

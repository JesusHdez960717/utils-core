package com.jhw.utils.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.jhw.utils.others.Misc;

/**
 *
 * @author Jesús Hernández Barrios (jhernandezb96@gmail.com)
 */
public class SHA {

    public static byte[] hash256(byte[] text) {
        try {
            MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
            return mDigest.digest(text);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static byte[] hash512(byte[] text) {
        try {
            MessageDigest mDigest = MessageDigest.getInstance("SHA-512");
            return mDigest.digest(text);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String hash256(String text) {
        try {
            MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
            byte[] arr = mDigest.digest(text.getBytes());
            return Misc.toString64(arr);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String hash512(String text) {
        try {
            MessageDigest mDigest = MessageDigest.getInstance("SHA-512");
            byte[] arr = mDigest.digest(text.getBytes());
            return Misc.toString64(arr);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String hash(String text, String algo) {
        try {
            MessageDigest mDigest = MessageDigest.getInstance(algo);
            byte[] result = mDigest.digest(text.getBytes());
            return String.format("%1$064x", new java.math.BigInteger(1, result));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}

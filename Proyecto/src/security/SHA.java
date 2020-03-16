/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Yo
 */
public class SHA {

    public static String hash256(String text) {
        try {
            MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
            byte[] result = mDigest.digest(text.getBytes());
            return String.format("%1$064x", new java.math.BigInteger(1, result));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String hash512(String text) {
        try {
            MessageDigest mDigest = MessageDigest.getInstance("SHA-512");
            byte[] result = mDigest.digest(text.getBytes());
            return String.format("%1$064x", new java.math.BigInteger(1, result));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}

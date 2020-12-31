package com.root101.utils.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class AES {

    private static SecretKey secret;
    private static final String ALGO = "AES/CBC/PKCS5Padding";
    private static Cipher cipher;
    private static byte[] iv = new byte[16];

    public static byte[] cipher(byte[] pass, byte[] text) throws Exception {
        cipher = Cipher.getInstance(ALGO);

        byte[] passPadded = SHA.hash256(pass);
        
        byte sec[] = new byte[16];
        System.arraycopy(passPadded, 0, sec, 0, sec.length);//primeros 16  bytes del sha-256 para la clave secreta
        secret = new SecretKeySpec(sec, "AES");

        System.arraycopy(passPadded, 16, iv, 0, iv.length);//ultimos 16  bytes del sha-256 para iv

        cipher.init(Cipher.ENCRYPT_MODE, secret, new IvParameterSpec(iv));
        return cipher.doFinal(text);
    }

    public static byte[] decipher(byte[] pass, byte[] text) throws Exception {
        cipher = Cipher.getInstance(ALGO);

        byte[] passPadded = SHA.hash256(pass);
        
        byte sec[] = new byte[16];
        System.arraycopy(passPadded, 0, sec, 0, sec.length);//primeros 16  bytes del sha-256 para la clave secreta
        secret = new SecretKeySpec(sec, "AES");

        System.arraycopy(passPadded, 16, iv, 0, iv.length);//ultimos 16  bytes del sha-256 para iv

        cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv));
        return cipher.doFinal(text);
    }
}

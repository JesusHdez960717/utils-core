/*
 * Copyright 2021 Root101 (jhernandezb96@gmail.com, +53-5-426-8660).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Or read it directly from LICENCE.txt file at the root of this project.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.root101.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
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

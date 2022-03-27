/*
 * Copyright 2022 Root101 (jhernandezb96@gmail.com, +53-5-426-8660).
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
package dev.root101.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import dev.root101.utils.others.Misc;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
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

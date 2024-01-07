package com.danielrodrigues.app.utils;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class BCryptUtil {

    public static SecretKeySpec generateKey(String value) throws Exception {
        byte[] keyRaw = value.getBytes("UTF-8");
        return new SecretKeySpec(keyRaw, "AES");
    }

    public static String encrypt(SecretKeySpec key, String value) throws Exception{
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedValue = cipher.doFinal(value.getBytes());
        return Base64.getEncoder().encodeToString(encryptedValue);
    }
    
    public static String decrypt(SecretKeySpec key, String value) throws Exception{
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] bytesValue = Base64.getDecoder().decode(value);
        byte[] descryptedValue = cipher.doFinal(bytesValue);
        return new String(descryptedValue);
    }

}

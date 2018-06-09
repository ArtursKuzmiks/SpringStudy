package com.example.Tests.AESutil;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


/**
 * @author Artur Kuzmik on 18.5.6
 */

@Service
@Qualifier("AesUtil")
public class AesUtilImpl implements AesUtil{

    private static String key = "0000000000000090";

    private byte[] hexToByteArray(String hex) {
        if (hex == null || hex.length() == 0) {
            return null;
        }

        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }

        return bytes;
    }

    private String byteArrayToHex(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        String hexNum;
        for (byte aByte : bytes) {
            hexNum = "0" + Integer.toHexString(0xff & aByte);
            sb.append(hexNum.substring(hexNum.length() - 2));
        }

        return sb.toString();
    }

    @Override
    public String encrypt(String origString) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key.getBytes(),"AES");

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        cipher.init(Cipher.ENCRYPT_MODE,secretKey);

        byte [] encrypted = cipher.doFinal(origString.getBytes());
        return byteArrayToHex(encrypted);
    }

    @Override
    public String decrypt(String encrypted) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key.getBytes(),"AES");

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE,secretKey);

        byte [] original = cipher.doFinal(hexToByteArray(encrypted));

        return new String(original);
    }

}

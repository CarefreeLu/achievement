package com.nuonuo.trade.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 加密解密工具类
 */
public class CryptoUtils
{

    private static final String CHARSET = "UTF-8";

    /**
     * 加密算法
     */
    private static final String ALGORITHM = "DES";

    /**
     * 密钥参数名
     */
    private static final String APPSECRET_PARAM = "appsecret=";

    /**
     * 对用DES加密过的数据进行加密.
     *
     * @param data 待加密字符串
     * @param key  密钥
     * @return 加密后的字符串
     * @throws Exception Exception
     */
    public static String encrypt(String data, String key) throws Exception {
        return byte2hex(encrypt(data.getBytes(CHARSET), key.getBytes(CHARSET)));
    }

    /**
     * 对数据进行DES解密.
     *
     * @param data 待解密字符串
     * @param key  密钥
     * @return 解密后的字符串
     * @throws Exception Exception
     */
    public static String decrypt(String data, String key) throws Exception {
        return new String(decrypt(hex2byte(data.getBytes(CHARSET)), key.getBytes(CHARSET)),CHARSET);
    }

    /**
     * 签名
     *
     * @param paramValues 签名需要的参数
     * @param appsecret   密钥
     * @return 签名后的字符串
     */
    public static String sign(Map<String, String> paramValues, String appsecret) {
        if (paramValues == null || paramValues.isEmpty()) {
            throw new RuntimeException("请检查签名参数");
        }
        if (appsecret == null || "".equals(appsecret.trim())
                || 32 < appsecret.length() || 8 > appsecret.length()) {
            throw new RuntimeException("请检查appsecret");
        }

        StringBuilder sb = new StringBuilder();
        try {
            List<String> paramNames = new ArrayList<>(paramValues.size());
            paramNames.addAll(paramValues.keySet());
            Collections.sort(paramNames);
            for (String paramName : paramNames) {
                sb.append(paramName).append("=").
                        append(paramValues.get(paramName)).append("&");
            }
            sb.append(APPSECRET_PARAM).append(appsecret);
            byte[] sha1Digest = getSHA1Digest(sb.toString());
            return CryptoUtils.byte2hex(sha1Digest);
        } catch (Exception e) {
            throw new RuntimeException("加密签名计算错误", e);
        }
    }

    /**
     * 用指定的key对数据进行DES解密.
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey secretKey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, sr);
        return cipher.doFinal(data);
    }

    /**
     * 用指定的key对数据进行DES加密.
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey secretKey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, sr);
        return cipher.doFinal(data);
    }

    /**
     * byte数组转换成16进制串
     */
    private static String byte2hex(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            String s = Integer.toHexString(b & 0XFF);
            if (s.length() == 1) {
                stringBuilder.append("0").append(s);
            } else {
                stringBuilder.append(s);
            }
        }
        return stringBuilder.toString().toLowerCase();
    }

    /**
     * 16进制转换成byte数组
     */
    private static byte[] hex2byte(byte[] hex) {
        if ((hex.length % 2) != 0) {
            throw new IllegalArgumentException("长度不是偶数");
        }

        byte[] result = new byte[hex.length / 2];
        for (int i = 0; i < hex.length; i += 2) {
            String s = new String(hex, i, 2);
            result[i / 2] = (byte) Integer.parseInt(s, 16);
        }
        return result;
    }

    /**
     * SHA1签名算法
     */
    private static byte[] getSHA1Digest(String data) throws IOException {
        byte[] bytes;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            bytes = md.digest(data.getBytes("UTF-8"));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse);
        }
        return bytes;
    }

    public static void main(String[] args) throws Exception {
        String encrypt = encrypt("AH95F820190701121121", "12345678abcdefgh");
        System.out.println(encrypt);
    }
}
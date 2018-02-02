package com.example.damai.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by guodazhao on 2018/2/1 0001.
 */

public class MD5Utils {
    public static String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            byte[] bytes = mDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                String hex = Integer.toHexString(0xFF & bytes[i]);
                if (hex.length()==1) {
                    sb.append('0');
                }
                sb.append(hex);
            }
            cacheKey = sb.toString();
        } catch (NoSuchAlgorithmException e) {//没有这个算法异常
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }
}

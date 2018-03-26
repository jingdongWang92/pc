package com.jcble.jcparking.common.utils;

import java.security.MessageDigest;

/**
 * MD5工具类
 * 
 * @author
 * 
 */
public class MD5 {
    private static final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
            'f'};

    /** 字符集 */
    private static final String Charset = "UTF-8";

    /**
     * 加密算法
     * 
     * @param src
     *            待加密内容
     * @return
     * @throws Exception
     */
    public static String encrypt(byte[] src) throws Exception {
        MessageDigest mdTemp = MessageDigest.getInstance("MD5");
        mdTemp.update(src);
        byte[] md = mdTemp.digest();
        int j = md.length;
        char str[] = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(str);

    }

    /**
     * 加密算法
     * 
     * @param src
     *            待加密内容
     * @return
     * @throws Exception
     */
    public static String encrypt(String src) throws Exception {
        byte[] srcByte = src.getBytes(Charset);
        return encrypt(srcByte);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(encrypt("admin"));
    }

}

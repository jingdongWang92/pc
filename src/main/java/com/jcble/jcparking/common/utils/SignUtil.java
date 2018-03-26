package com.jcble.jcparking.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 签名工具类
 * 
 * @authorJingdong Wang
 * @Date 2016-02-14
 * 
 */
public abstract class SignUtil {
    // 密钥是16位长度的byte[]进行Base64转换后得到的字符串
    public static String key = "LmMGStGtOpF4xNyvYt54EQ==";
    
    private static final Logger logger = LoggerFactory.getLogger(SignUtil.class);

    /**
     * <li>
     * 方法名称:byte2hex</li> <li>
     * 功能描述:
     * 
     * <pre>
     * 字节数组转换为二行制表示
     * </pre>
     * 
     * </li>
     * 
     * @param inStr
     *            需要转换字节数组。
     * @return 字节数组的二进制表示。
     */
    public static String byte2hex(byte[] inStr) {
        String stmp;
        StringBuffer out = new StringBuffer(inStr.length * 2);

        for (int n = 0; n < inStr.length; n++) {
            // 字节做"与"运算，去除高位置字节 11111111
            stmp = Integer.toHexString(inStr[n] & 0xFF);
            if (stmp.length() == 1) {
                // 如果是0至F的单位字符串，则添加0
                out.append("0" + stmp);
            } else {
                out.append(stmp);
            }
        }
        return out.toString();
    }

    /**
     * <li>
     * 方法名称:addMD5</li> <li>
     * 功能描述:
     * 
     * <pre>
     * MD校验码 组合方法，前16位放MD5Hash码。 把MD5验证码byte[]，加密内容byte[]组合的方法。
     * </pre>
     * 
     * </li>
     * 
     * @param md5Byte
     *            加密内容的MD5Hash字节数组。
     * @param bodyByte
     *            加密内容字节数组
     * @return 组合后的字节数组，比加密内容长16个字节。
     */
    public static byte[] addMD5(byte[] md5Byte, byte[] bodyByte) {
        int length = bodyByte.length + md5Byte.length;
        byte[] resutlByte = new byte[length];

        // 前16位放MD5Hash码
        for (int i = 0; i < length; i++) {
            if (i < md5Byte.length) {
                resutlByte[i] = md5Byte[i];
            } else {
                resutlByte[i] = bodyByte[i - md5Byte.length];
            }
        }

        return resutlByte;
    }


    /**
     * 生成有效签名
     * 
     * @param params
     * @param secret
     * @return
     * @throws Exception
     */
    public static String signature(Map<String, String> params, String secret, boolean appendSecret, boolean isHMac,
            String signName) throws Exception {
        params.remove(signName);
        String[] names = params.keySet().toArray(ArrayUtils.EMPTY_STRING_ARRAY);
        Arrays.sort(names);
        StringBuilder sb = new StringBuilder();
        // append if not hmac
        if (!isHMac) {
            sb.append(secret);
        }
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            sb.append(name);
            sb.append(params.get(name));
        }
        if (appendSecret && !isHMac) {
            sb.append(secret);
        }
        String sign = null;
        logger.info("sign source str:"+sb.toString());
        try {
            if (isHMac) {
                // hmac
                sign = byte2hex(encryptHMAC(sb.toString().getBytes("UTF-8"), secret.getBytes("UTF-8")));
            } else {
                // md5
                sign = MD5.encrypt(sb.toString()).toUpperCase();
            }
        } catch (UnsupportedEncodingException e) {
            throw new java.lang.RuntimeException(e);
        }
        return sign;
    }


    /**
     * HMAC加密
     * 
     * @param data
     * @param key
     * @return
     * @throws Exception
     * @throws Exception
     */
    public static byte[] encryptHMAC(byte[] data, byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, "HmacMD5");
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        return mac.doFinal(data);
    }

}
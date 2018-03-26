package com.jcble.jcparking.common.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 发送短信给手机
 * 
 * @author Jingdong Wang
 * 
 */
public class SMSUtil {
    private static final Logger logger = LoggerFactory.getLogger(ClientSmsSender.class);

    private static final String BATCH_URL = "http://120.26.69.248/msg/HttpBatchSendSM";
    
    /**
     * 发送短息验证码
     * 
     * @param mobile
     */
    public static String sendVcodeSMSToMobile(String mobile) {
        String vcode = RandomStringUtils.randomNumeric(4);
        sendSMS(mobile, vcode);
        return vcode;
    }

    /**
     * 执行发送短信
     * 
     * @param mobile 手机号码
     * @param vcode 验证码
     */
    public static String sendSMS(String mobile,String vcode) {
    	String account = GetSystemConfig.getValueByKey("ronglian.account");
    	String password = GetSystemConfig.getValueByKey("ronglian.password");
    	String result = "";
        String msg = "欢迎使用朕要停车，您本次的验证码为"+vcode+",该验证码在2分钟内有效。";
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("account", account);
    	params.put("pswd", password);
    	params.put("mobile", mobile);
    	params.put("msg", msg);
    	params.put("needstatus", "true");
        try {
            result = HttpUtil.sendPostRequest(BATCH_URL, params);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return result;
    }
}

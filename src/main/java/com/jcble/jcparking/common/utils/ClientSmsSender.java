package com.jcble.jcparking.common.utils;

import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cloopen.rest.sdk.CCPRestSDK;

/**
 * 短信下发客服端
 * 
 * @author Jingdong Wang
 * 
 */
public class ClientSmsSender {
    private static final Logger logger = LoggerFactory.getLogger(ClientSmsSender.class);

    public static final String VCODE_TEMPLATEID = "82451";

    private static CCPRestSDK restAPI;


    /**
     * 发送短息验证码
     * 
     * @param mobile
     */
    public static String sendVcodeSMSToMobile(String mobile) {
        String vcode = RandomStringUtils.randomNumeric(4);
        String[] params = {vcode};
        sendSMS(mobile, VCODE_TEMPLATEID, params);
        return vcode;
    }

    /**
     * 执行发送短信
     * 
     * @param mobile
     * @param templateId
     * @param datas
     */
    public static Map<String, Object> sendSMS(String mobile, String templateId, String[] datas) {
        Map<String, Object> result = null;
        try {
            result = restAPI.sendTemplateSMS(mobile, templateId, datas);
            logger.info("Response result=" + result);
            if ("000000".equals(result.get("statusCode"))) {
                // 正常返回输出data包体信息（map）
                logger.info("手机号：" + mobile + ",下发短信成功");
            } else {
                // 异常返回输出错误码和错误信息
                logger.error("phone=" + mobile + "错误码=" + result.get("statusCode") + " 错误信息= "
                        + result.get("statusMsg"));
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return result;
    }
}

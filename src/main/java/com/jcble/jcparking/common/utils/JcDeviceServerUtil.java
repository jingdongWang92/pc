package com.jcble.jcparking.common.utils;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 甲虫设备控制Util
 * 
 * @author Jingdong Wang
 * @date 2017年3月21日 下午5:17:50
 *
 */
public class JcDeviceServerUtil {
	protected static final Logger log = LoggerFactory.getLogger(JcDeviceServerUtil.class);

	private static String TOKEN = null;
	private static final String DEVELOPER_JCBEL_SERVER_HOST = GetSystemConfig.getValueByKey("developer.jcbel.server.host");
	private static final String GRANT_TYPE = "password";
	private static final String ACCESS_KEY = GetSystemConfig.getValueByKey("rabbitmq.username");
	private static final String ACCESS_SECRET = GetSystemConfig.getValueByKey("rabbitmq.password");
	private static final String USERNAME = "jcble@jcble.com";
	private static final String PASSWORD = "12345678";

	public static String getToken() {
		if (null == TOKEN) {
			synchronized (JcDeviceServerUtil.class) {
				if (null == TOKEN) {
					grantTypePassword();
				}
			}
		}
		return TOKEN;
	}

	/**
	 * 获取token grant_type:password
	 */
	public static void grantTypePassword() {
		String url = DEVELOPER_JCBEL_SERVER_HOST + "/auth/token";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("grant_type", GRANT_TYPE);
		paramMap.put("username", USERNAME);
		paramMap.put("password", PASSWORD);
		String result = HttpUtil.sendPostSSLJsonRequest(url, paramMap,null);
		String token = null;
		JSONObject jsonObject = JSONObject.fromObject(result);
		if (!jsonObject.getBoolean("error")) {
			JSONObject data = jsonObject.getJSONObject("payload");
			token = data.getString("access_token");
			TOKEN = token;
		}
	}
	
	/**
	 * 获取token grant_type:password
	 */
	public static void grantTypeClientCredentials() {
		String url = DEVELOPER_JCBEL_SERVER_HOST + "/auth/token";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("grant_type", GRANT_TYPE);
		paramMap.put("access_key", ACCESS_KEY);
		paramMap.put("access_secret", ACCESS_SECRET);
		String result = HttpUtil.sendPostSSLJsonRequest(url, paramMap,null);
		String token = null;
		JSONObject jsonObject = JSONObject.fromObject(result);
		if (!jsonObject.getBoolean("error")) {
			JSONObject data = jsonObject.getJSONObject("payload");
			token = data.getString("access_token");
			TOKEN = token;
		}
	}

	/**
	 * 车位锁控制
	 * @param serialNumber 设备串号
	 * @param command 控制命令
	 * @return
	 * @throws Exception
	 */
	public static String lockControl(String serialNumber,String command) throws Exception {
		String url = DEVELOPER_JCBEL_SERVER_HOST + "/devices/" + serialNumber + "/command";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("command", command);
		String result = HttpUtil.sendPostSSLJsonRequest(url, paramMap,getToken());
		return result;
	}

	/**
	 * 获取车位锁的状态
	 * 
	 * @param imei
	 * @return
	 * @throws Exception
	 */
	public static String get(String imei) throws Exception {
		String url = DEVELOPER_JCBEL_SERVER_HOST + "/devices/" + imei;
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Authorization", "Bearer " + getToken());
		headerMap.put("Content-Type", "application/json");
		Map<String, String> paramMap = new HashMap<String, String>();
		String result = HttpUtil.sendGetRequest(url, headerMap, paramMap, null, null);
		return result;
	}

	public static int getStatus(String imei) throws Exception {
		String result = get(imei);
		JSONObject json = JSONObject.fromObject(result);
		JSONArray data = json.getJSONArray("data");
		int status = data.getJSONObject(0).getInt("status");
		return status;
	}
}

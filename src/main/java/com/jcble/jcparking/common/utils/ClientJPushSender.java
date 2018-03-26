package com.jcble.jcparking.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcble.jcparking.common.CommonEnum;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * 极光推送到app客服端
 * 
 * @author Jingdong Wang
 * @date 2017年3月1日 上午11:44:40
 *
 */
public class ClientJPushSender {
	private static final Logger logger = LoggerFactory.getLogger(ClientJPushSender.class);

	private static JPushClient jpushClient;
	
	private static final String ADMIN_CLIENT_JPUSH_APPKEY = GetSystemConfig.getValueByKey("admin.client.jpush.app.key");
	private static final String ADMIN_CLIENT_JPUSH_MASTER_SECRET = GetSystemConfig.getValueByKey("admin.client.jpush.master.secret");
	private static final String USER_CLIENT_JPUSH_APPKEY = GetSystemConfig.getValueByKey("user.client.jpush.app.key");
	private static final String USER_CLIENT_JPUSH_MASTER_SECRET = GetSystemConfig.getValueByKey("user.client.jpush.master.secret");
	private static final boolean JPUSH_APNS_PRODUCTION = "true".equals(GetSystemConfig.getValueByKey("jpush.apns.production")) ? true : false;

	/**
	 * 向指定regid推送消息通知
	 * 
	 * @param regId
	 * @param content
	 */
	public static void sendByRegId(String regId, String content,String clientType) {
		try {
			if(CommonEnum.ClientType.Admin.code.equals(clientType)) {
				sendByRegId(regId, content, ADMIN_CLIENT_JPUSH_APPKEY, ADMIN_CLIENT_JPUSH_MASTER_SECRET, true);
			} else {
				sendByRegId(regId, content, USER_CLIENT_JPUSH_APPKEY, USER_CLIENT_JPUSH_MASTER_SECRET, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 向指定regid推送消息通知
	 * 
	 * @param regId
	 * @param content
	 */
	public static void sendByRegId(String regId, String content, boolean apnsProduction) {
		sendByRegId(regId, content, ADMIN_CLIENT_JPUSH_APPKEY, ADMIN_CLIENT_JPUSH_MASTER_SECRET, apnsProduction);
	}

	/**
	 * 向指定regid推送消息通知
	 * 
	 * @param regId
	 * @param content
	 */
	public static void sendByRegId(String regId, String content, String appkey, String secret, boolean apnsProduction) {
		jpushClient = new JPushClient(secret, appkey);
		try {
			PushPayload payload = buildPushObject_all_regId_alert(regId, content, apnsProduction);
			PushResult result = jpushClient.sendPush(payload);
			logger.info("Got result - " + result);
		} catch (APIConnectionException e) {
			logger.error("Connection error, should retry later", e);
		} catch (APIRequestException e) {
			logger.error("Should review the error, and fix the request", e);
			logger.info("HTTP Status: " + e.getStatus());
			logger.info("Error Code: " + e.getErrorCode());
			logger.info("Error Message: " + e.getErrorMessage());
		} finally {
			logger.info("Send regId==>" + regId + ",content==>" + content);
		}
	}

	/**
	 * 向指定regid推送消息通知
	 * 
	 * @param regId
	 * @param content
	 */
	public static void sendExtrasByRegId(String regId, String content,String key,
			String value, boolean apnsProduction) {
		String jpushAppKey = GetSystemConfig.getValueByKey("admin.client.jpush.app.key");
		String jpushMasterSecret = GetSystemConfig.getValueByKey("admin.client.jpush.master.secret");
		jpushClient = new JPushClient(jpushMasterSecret, jpushAppKey);
		try {
			PushPayload payload = buildPushObject_ios_audienceMore_messageWithExtras(regId, content, key, value,
					apnsProduction);
			PushResult result = jpushClient.sendPush(payload);
			logger.info("Got result - " + result);
		} catch (APIConnectionException e) {
			logger.error("Connection error, should retry later", e);
		} catch (APIRequestException e) {
			logger.error("Should review the error, and fix the request", e);
			logger.info("HTTP Status: " + e.getStatus());
			logger.info("Error Code: " + e.getErrorCode());
			logger.info("Error Message: " + e.getErrorMessage());
		} finally {
			logger.info("Send regId==>" + regId + ",content==>" + content);
		}
	}

	/**
	 * 向别名为指定的所有设备推送消息通知
	 * 
	 * @param regId
	 * @param content
	 */
	public static void sendAllByAlias(String alias, String content, boolean apnsProduction,String clientType) {
		if(CommonEnum.ClientType.Admin.code.equals(clientType)) {
			jpushClient = new JPushClient(ADMIN_CLIENT_JPUSH_MASTER_SECRET, ADMIN_CLIENT_JPUSH_APPKEY);
		} else {
			jpushClient = new JPushClient(USER_CLIENT_JPUSH_MASTER_SECRET, USER_CLIENT_JPUSH_APPKEY);
		}
		try {
			PushPayload payload = buildPushObject_all_alias_alert(alias, content, apnsProduction);
			PushResult result = jpushClient.sendPush(payload);
			logger.info("Got result - " + result);
		} catch (APIConnectionException e) {
			logger.error("Connection error, should retry later", e);
		} catch (APIRequestException e) {
			logger.error("Should review the error, and fix the request", e);
			logger.info("HTTP Status: " + e.getStatus());
			logger.info("Error Code: " + e.getErrorCode());
			logger.info("Error Message: " + e.getErrorMessage());
		}
	}
	
	/**
	 * 向别名为指定的所有设备推送消息通知,带附加字段
	 * 
	 * @param regId
	 * @param content
	 */
	public static void sendAllByAliasWithExtras(String tag, String content,String clientType,
			String extras, String value) {
		if (CommonEnum.ClientType.Admin.code.equals(clientType)) {
			jpushClient = new JPushClient(ADMIN_CLIENT_JPUSH_MASTER_SECRET, ADMIN_CLIENT_JPUSH_APPKEY);
		} else {
			jpushClient = new JPushClient(USER_CLIENT_JPUSH_MASTER_SECRET, USER_CLIENT_JPUSH_APPKEY);
		}
		try {
			PushPayload payload = buildPushObject_all_tag_alertWithExtras(tag, content, JPUSH_APNS_PRODUCTION, extras,value);
			PushResult result = jpushClient.sendPush(payload);
			logger.info("Got result - " + result);
		} catch (APIConnectionException e) {
			logger.error("Connection error, should retry later", e);
		} catch (APIRequestException e) {
			logger.error("Should review the error, and fix the request", e);
			logger.info("HTTP Status: " + e.getStatus());
			logger.info("Error Code: " + e.getErrorCode());
			logger.info("Error Message: " + e.getErrorMessage());
		}
	}
	/**
	 * 向tag为指定的所有设备推送消息通知,带附加字段
	 * 
	 * @param regId
	 * @param content
	 */
	public static void sendAllByTagWithExtras(String alias, String content,String clientType,
			String extras, String value) {
		if (CommonEnum.ClientType.Admin.code.equals(clientType)) {
			jpushClient = new JPushClient(ADMIN_CLIENT_JPUSH_MASTER_SECRET, ADMIN_CLIENT_JPUSH_APPKEY);
		} else {
			jpushClient = new JPushClient(USER_CLIENT_JPUSH_MASTER_SECRET, USER_CLIENT_JPUSH_APPKEY);
		}
		try {
			PushPayload payload = buildPushObject_all_tag_alertWithExtras(alias, content, JPUSH_APNS_PRODUCTION, extras,value);
			PushResult result = jpushClient.sendPush(payload);
			logger.info("Got result - " + result);
		} catch (APIConnectionException e) {
			logger.error("Connection error, should retry later", e);
		} catch (APIRequestException e) {
			logger.error("Should review the error, and fix the request", e);
			logger.info("HTTP Status: " + e.getStatus());
			logger.info("Error Code: " + e.getErrorCode());
			logger.info("Error Message: " + e.getErrorMessage());
		}
	}

	/**
	 * 向别名为指定的所有设备推送消息通知
	 * 
	 * @param content
	 */
	public static void sendAll(String content) {
		jpushClient = new JPushClient(ADMIN_CLIENT_JPUSH_MASTER_SECRET, ADMIN_CLIENT_JPUSH_APPKEY);
		try {
			PushPayload payload = buildPushObject_all_all_alert(content);
			PushResult result = jpushClient.sendPush(payload);
			logger.info("Got result - " + result);
		} catch (APIConnectionException e) {
			logger.error("Connection error, should retry later", e);
		} catch (APIRequestException e) {
			logger.error("Should review the error, and fix the request", e);
			logger.info("HTTP Status: " + e.getStatus());
			logger.info("Error Code: " + e.getErrorCode());
			logger.info("Error Message: " + e.getErrorMessage());
		}
	}

	/**
	 * 向所有平台，推送注册指定注册id时，推送消息通知
	 * 
	 * @param regId
	 * @param content
	 * @return
	 */
	public static PushPayload buildPushObject_all_regId_alert(String regId, String content) {
		return buildPushObject_all_regId_alert(regId, content, true);
	}

	/**
	 * 向所有平台，推送注册指定注册id时，推送消息通知
	 * 
	 * @param regId
	 * @param content
	 * @return
	 */
	public static PushPayload buildPushObject_all_regId_alert(String regId, String content, boolean apnsProduction) {
		return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.registrationId(regId))
				.setNotification(Notification.alert(content))
				.setOptions(Options.newBuilder().setApnsProduction(apnsProduction).build()).build();
	}

	/**
	 * 向所有平台，推送注册指定注册id时，推送额外参数消息通知
	 * 
	 * @param regId
	 * @param content
	 * @return
	 */
	public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras(String regId, String content,
			String key, String value, boolean apnsProduction) {
		return buildPushObject_ios_audienceMore_messageWithExtras(regId, content, key, value, "order.aiff",
				apnsProduction);
	}

	/**
	 * 向所有平台，推送注册指定注册id时，推送额外参数消息通知
	 * 
	 * @param regId
	 * @param content
	 * @return
	 */
	public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras(String regId, String content,
			String key, String value, String sound, boolean apnsProduction) {
		return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.registrationId(regId))
				.setNotification(
						Notification.newBuilder()
								.addPlatformNotification(IosNotification.newBuilder().setAlert(content).setBadge(1)
										.setSound(sound).build())
								.build())
				.setMessage(Message.newBuilder().setMsgContent(content).addExtra(key, value).build())
				.setOptions(Options.newBuilder().setApnsProduction(apnsProduction).build()).build();
	}

	/**
	 * 向 所有平台，所有设备，推送通知
	 * 
	 * @param content
	 *            通知消息
	 * @return
	 */
	public static PushPayload buildPushObject_all_all_alert(String content) {
		return PushPayload.alertAll(content);
	}

	/**
	 * 向 所有平台，推送目标别名指定名称时，推送通知内容
	 * 
	 * @param targetAliasName
	 * @param content
	 * @return
	 */
	public static PushPayload buildPushObject_all_alias_alert(String targetAliasName, String content,
			boolean apnsProduction) {
		return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.alias(targetAliasName))
				.setNotification(Notification.alert(content))
				.setOptions(Options.newBuilder().setApnsProduction(apnsProduction).build()).build();
	}
	
	/**
	 * 向别名为指定的所有设备推送消息通知,带附加字段
	 * @param targetAliasName
	 * @param content
	 * @param apnsProduction
	 * @param extras
	 * @param value
	 * @return
	 */
	public static PushPayload buildPushObject_all_alias_alertWithExtras(String targetAliasName, String content,
			boolean apnsProduction,String extras,String value) {
		return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.alias(targetAliasName))
				.setNotification(Notification.alert(content))
				.setMessage(Message.newBuilder().setMsgContent(content).addExtra(extras, value).build())
				.setOptions(Options.newBuilder().setApnsProduction(apnsProduction).build()).build();
	}

	/**
	 * 向所有平台,目标是 tag的设备，推送消息通知
	 * @param tag
	 * @param content
	 * @param apnsProduction
	 * @param extras
	 * @param value
	 * @return
	 */
	public static PushPayload buildPushObject_all_tag_alertWithExtras(String tag,String content,
			boolean apnsProduction,String extras,String value) {
		return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.tag(tag))
				.setNotification(Notification.alert(content))
				.setMessage(Message.newBuilder().setMsgContent(content).addExtra(extras, value).build())
				.setOptions(Options.newBuilder().setApnsProduction(apnsProduction).build()).build();
	}

}

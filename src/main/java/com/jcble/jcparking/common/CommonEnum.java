package com.jcble.jcparking.common;

import java.util.HashMap;
import java.util.Map;

public class CommonEnum {

	/** 返回代码*/
	public enum Rtn {
		Success("0", "成功"), Failure("1", "失败");

		public String code;
		public String name;

		Rtn(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	/** 通用有无或存在与否 */
	public enum ExistFlg {
		No("0", "无"), Yes("1", "有");

		public String code;
		public String name;

		ExistFlg(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	/** 通用是或不是 */
	public enum YesNoFlg {
		No("0", "不是"), Yes("1", "是");

		public String code;
		public String name;

		YesNoFlg(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	/** 通用成功或者失败 */
	public enum OPResult {
		Success("0", "成功"), Failure("1", "失败");

		public String code;
		public String name;

		OPResult(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	/** 车位状态 */
	public enum ParkingStatus {
		Free("0", "空闲"), Use("1", "占用");

		public String code;
		public String name;

		ParkingStatus(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	/** 注册类型 */
	public enum RegisterType {
		App("0", "App"), Wechat("1", "微信");

		public String code;
		public String name;

		RegisterType(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	/** 车辆状态 */
	public enum CarStatus {
		Out("0", "未入场"), Reservation("1", "已预约"), In("2", "已入场"), NotLeave("3", "已缴费未出场");

		public String code;
		public String name;

		CarStatus(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	/** 管理员账号状态 */
	public enum AdminStatus {
		Unabled("0", "未启用"), Enabled("1", "启用");

		public String code;
		public String name;

		AdminStatus(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	/** 活动状态 */
	public enum ActivityStatus {
		UnStart("0", "未开始"), Ing("1", "进行中"), End("2", "已结束");

		public String code;
		public String name;

		ActivityStatus(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	/** 预约状态 */
	public enum RevatationStatus {
		Revatationed("0", "已预约"),Failure("1", "预约失败"),Success("2", "预约成功"), Cancel("3", "已取消"), Using("4", "使用中"), OverTime("5", "预约过期"),End("6", "结束");

		public String code;
		public String name;

		RevatationStatus(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	/** 支付方式 */
	public enum PayWay {
		Online("0", "网络支付"),//页面展示
		Cash("1", "现金支付"),Alipay("2", "支付宝支付"),WxPay("3", "微信支付"),Balance("4","余额");

		public String code;
		public String name;

		PayWay(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	/** 反馈类型 */
	public enum FeedbackType {
		SoftwareBug("0", "软件Bug"), Advice("1", "建议"), Other("2", "其他");

		public String code;
		public String name;

		FeedbackType(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	/** 警告处理状态 */
	public enum AlarmHandleStatus {
		UnProcessed("0", "未处理"),Processed("1", "已处理");

		public String code;
		public String name;

		AlarmHandleStatus(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	/** 订单来源 */
	public enum OrderSource {
		Barrier("0", "道闸"), PDA("1", "PDA");

		public String code;
		public String name;

		OrderSource(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	/** 订单状态 */
	public enum OrderStatus {
		Ing("0", "进行中"), OwnFee("1", "欠费"), End("2", "已结束");

		public String code;
		public String name;

		OrderStatus(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	/** 是否为免费订单 */
	public enum IsFreeOrder {
		No("0", "非免费订单"), Yes("1", "免费订单");

		public String code;
		public String name;

		IsFreeOrder(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	/** 车辆进出方向 */
	public enum PassDirec {
		In("0", "进"), Out("1", "出");

		public String code;
		public String name;

		PassDirec(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	/** 车辆类型 */
	public enum carType {
		Large("0", "大型车"), Mid("1", "中型车"), Small("2", "小型车");

		public String code;
		public String name;

		carType(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	/** 停车类型 */
	public enum parkingType {
		Temp("0", "临时车"), MonthRent("1", "月租车");

		public String code;
		public String name;

		parkingType(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	/** 网关状态 */
	public enum GatewayStatus {
		inline("0", "在线"), offline("1", "离线");

		public String code;
		public String name;

		GatewayStatus(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	/** 车位锁状态 */
	public enum ParkingLockStatus {
		NoConnection("0", "未连接"), Lock("1", "升起"), UnLock("2", "降下"), Impact("3", "外力碰撞"), Block("4",
				"升起受阻"), Fault("5", "硬件故障");

		public String code;
		public String name;

		ParkingLockStatus(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	/** 地磁状态 */
	public enum GeoStatus {
		Initial("0", "初始化"), Free("1", "空闲"), Use("2", "占用"), Error("3", "错误"), Unknow("4", "未知"), OffLine("5", "离线");

		public String code;
		public String name;

		GeoStatus(String code, String name) {
			this.code = code;
			this.name = name;
		}

		public static Map<String, String> codeMap = new HashMap<String, String>();
		static {
			for (int i = 0; i < values().length; i++) {
				codeMap.put(values()[i].code, values()[i].name);
			}
		}

		public String getCode() {
			return code;
		}

		public String getName() {
			return name;
		}
	}

	/** 设备类型 */
	public enum DeviceType {
		Lock("01", "车位锁"), GateWay("02", "网关"),Geomagnetism("06", "地磁");

		public String code;
		public String name;

		DeviceType(String code, String name) {
			this.code = code;
			this.name = name;
		}

		public static Map<String, String> codeMap = new HashMap<String, String>();
		static {
			for (int i = 0; i < values().length; i++) {
				codeMap.put(values()[i].code, values()[i].name);
			}
		}

		public String getCode() {
			return code;
		}

		public String getName() {
			return name;
		}
	}

	/** 车位锁操作类型 */
	public enum LockOpType {
		Up("0", "升起"), Down("1", "降下");

		public String code;
		public String name;

		LockOpType(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	/** 地磁强磁干扰 */
	public enum SMIStatus {
		Formal("0", "正常"), SMI("1", "强磁干扰");

		public String code;
		public String name;

		SMIStatus(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	/** 地磁是否空闲 */
	public enum GeoIsFree {
		Yes("0", "空闲"), No("1", "占用");

		public String code;
		public String name;

		GeoIsFree(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	/** 车位创建方式 */
	public enum ParkingCreateWay {
		Single("0", "单个添加"), excelBatch("1", "Excel批量导入"), PaasBatch("2", "Paas批量导入");

		public String code;
		public String name;

		ParkingCreateWay(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	/** 车位锁告警类型 */
	public enum LockAlarmType {
		LowPower("0", "低电量"), Impact("1", "外力碰撞"), Block("2", "升起受阻"), Fault("3", "硬件故障");

		public String code;
		public String name;

		LockAlarmType(String code, String name) {
			this.code = code;
			this.name = name;
		}

		public static Map<String, String> codeMap = new HashMap<String, String>();
		static {
			for (int i = 0; i < values().length; i++) {
				codeMap.put(values()[i].code, values()[i].name);
			}
		}

		public String getCode() {
			return code;
		}

		public String getName() {
			return name;
		}
	}

	/** 地磁告警类型 */
	public enum GeoAlarmType {
		LowPower("0", "低电量"), SMI("1", "强磁干扰"), OffLine("2", "离线");

		public String code;
		public String name;

		GeoAlarmType(String code, String name) {
			this.code = code;
			this.name = name;
		}

		public static Map<String, String> codeMap = new HashMap<String, String>();
		static {
			for (int i = 0; i < values().length; i++) {
				codeMap.put(values()[i].code, values()[i].name);
			}
		}

		public String getCode() {
			return code;
		}

		public String getName() {
			return name;
		}
	}

	/** 支付状态 */
	public enum PayStatus {
		UnPay("0", "未支付"), Payed("1", "已支付"),UnRefund("2", "未退款"),Refund("3", "已退款");

		public String code;
		public String name;

		PayStatus(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	/** 设备绑定情况 */
	public enum DevBindStatus {
		UnBind("1", "未绑定车位锁"), BindEd("2", "未绑定地磁");
		public String code;
		public String name;

		DevBindStatus(String code, String name) {
			this.code = code;
			this.name = name;
		}

		/** The code map. */
		public static Map<String, String> codeMap = new HashMap<String, String>();

		static {
			for (int i = 0; i < values().length; i++) {
				codeMap.put(values()[i].code, values()[i].name);
			}
		}

		public String getCode() {
			return code;
		}

		public String getName() {
			return name;
		}
	}

	/** 车位锁升起降下状态 */
	public enum LockStatus {
		Lock("1", "锁升起车位"), UnLock("2", "锁降下车位");
		public String code;
		public String name;

		LockStatus(String code, String name) {
			this.code = code;
			this.name = name;
		}

		/** The code map. */
		public static Map<String, String> codeMap = new HashMap<String, String>();

		static {
			for (int i = 0; i < values().length; i++) {
				codeMap.put(values()[i].code, values()[i].name);
			}
		}

		public String getCode() {
			return code;
		}

		public String getName() {
			return name;
		}
	}

	/** 管理员类型 */
	public enum AdminType {
		Device("0", "设备管理员"), Charge("1", "收费管理员"), Both("2", "设备管理员、收费管理员");

		public String code;
		public String name;

		AdminType(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}
	
	/** 订单发票状态 */
	public enum OrderInvoiceStatus {
		NotQualified("0", "不具备"), UnApply("1", "未申请"), Applyed("2", "已申请"),Finish("3", "已完成");

		public String code;
		public String name;

		OrderInvoiceStatus(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}
	/** 发票状态 */
	public enum InvoiceStatus {
		Review("0", "审核中"), Finish("1", "已完成");
		
		public String code;
		public String name;
		
		InvoiceStatus(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}
	/** 固定车状态 */
	public enum WhitelistStatus {
		UnExpired("0", "未过期"), Expired("1", "已过期");
		
		public String code;
		public String name;
		
		WhitelistStatus(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}
	
	/** 费用类型 */
	public enum FeeType {
		AppointParkingFee("0", "预约停车费用"), ParkingFee("1", "停车费用"),BalanceRecharge("2", "余额充值");

		public String code;
		public String name;

		FeeType(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}
	/** 车位锁控制方式  */
	public enum LockOpWay {
		WEB("0","后台控制"),JURA("1", "JURA控制");

		public String code;
		public String name;

		LockOpWay(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}
	
	/** 车位锁控制方式  */
	public enum CarInOrOut {
		In("0","进场"),Out("1", "出场");

		public String code;
		public String name;

		CarInOrOut(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}
	
	/** 客户端类型  */
	public enum ClientType {
		User("0","用户端"),Admin("1", "管理员端");

		public String code;
		public String name;

		ClientType(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}
}

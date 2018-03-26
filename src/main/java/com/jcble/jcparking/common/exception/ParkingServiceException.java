package com.jcble.jcparking.common.exception;

import baseproj.common.exception.BaseServiceException;

/**
 * 自定义异常类
 * 
 * @author Jingdong Wang
 * 
 */
public class ParkingServiceException extends BaseServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int ERROR_00001 = 00001; //
    public static final int ERROR_10000 = 10000; // 签名错误
    public static final int ERROR_10001 = 10001; // 请求参数错误
    public static final int ERROR_10002 = 10002; // 没有权限
    public static final int ERROR_10003 = 10003; // 账号信息不存在
    public static final int ERROR_10004 = 10004; // 密码错误
    public static final int ERROR_10005 = 10005; // 车位不存在
    public static final int ERROR_10006 = 10006; // 验证码错误
    public static final int ERROR_10007 = 10007; // 设备已经被绑定
    public static final int ERROR_10008 = 10008; // 手机号码格式有误
    public static final int ERROR_10009 = 10009; // 订单不存在
    public static final int ERROR_10010 = 10010; // 停车场不存在
    public static final int ERROR_10011 = 10011; // 车辆信息不存在
    public static final int ERROR_10012 = 10012; // 车辆已经存在,请勿重复添加
    public static final int ERROR_10013 = 10013; // 车辆已预约车位,暂时不能删除
    public static final int ERROR_10014 = 10014; // 车辆已进场,暂时不能删除
    public static final int ERROR_10015 = 10015; // 手机号码被占用
    public static final int ERROR_10016 = 10016; // 原密码错误
    public static final int ERROR_10017 = 10017; // 该微信账号已被绑定
    public static final int ERROR_10018 = 10018; // 该账号已绑定微信
    public static final int ERROR_10019 = 10019; // 发票金额不能为零
    public static final int ERROR_10020 = 10020; // 该车辆存在未结束的订单,暂时不可以预约
    public static final int ERROR_10021 = 10021; // 暂时没有可以预约的车位,请稍后再试
    public static final int ERROR_10022 = 10022; // 当前状态不可取消预约
    public static final int ERROR_10023 = 10023; // 抱歉，只有支付10分钟以内才可取消订单
    public static final int ERROR_10024 = 10024; // 支付失败
    public static final int ERROR_10025 = 10025; // 账户余额不足 
    public static final int ERROR_10026 = 10026; // 该手机号与当前绑定的手机号相同
    public static final int ERROR_10027 = 10027; // 暂时没有临停的车位
    public static final int ERROR_10028 = 10028; // 录入订单失败,该车辆存在未结束的订单
    public static final int ERROR_10029 = 10029; // 当前需缴纳的金额为0

	public ParkingServiceException(int erroCode) {
		super(erroCode);
		this.setExceptionDesc(erroCode);
	}

	public ParkingServiceException(int erroCode, Object... params) {
		super(erroCode);
		if (params == null) {
			throw new IllegalArgumentException("params can't be null");
		}
		this.setExceptionDesc(erroCode, params);
	}
	
	/**
     * 设置业务异常描述
     * 
     * @param erroCode
     */
    protected void setExceptionDesc(int erroCode, Object... params) {
        switch (erroCode) {
        case ERROR_00001:
            this.setErroDesc("", params);
            break;
        case ERROR_10000:
            this.setErroDesc("签名错误", params);
            break;
        case ERROR_10001:
            this.setErroDesc("请求参数错误", params);
            break;
        case ERROR_10002:
            this.setErroDesc("没有权限", params);
            break;
        case ERROR_10003:
            this.setErroDesc("账号信息不存在", params);
            break;
        case ERROR_10004:
            this.setErroDesc("用户名或密码错误", params);
            break;
        case ERROR_10005:
            this.setErroDesc("车位不存在", params);
            break;
        case ERROR_10006:
        	this.setErroDesc("验证码错误", params);
        	break;
        case ERROR_10007:
        	this.setErroDesc("设备已经被绑定", params);
        	break;
        case ERROR_10008:
        	this.setErroDesc("手机号码格式有误", params);
        	break;
        case ERROR_10009:
        	this.setErroDesc("订单不存在", params);
        	break;
        case ERROR_10010:
        	this.setErroDesc("停车场不存在", params);
        	break;
        case ERROR_10011:
        	this.setErroDesc("车辆信息不存在", params);
        	break;
        case ERROR_10012:
        	this.setErroDesc("车辆已经存在,请勿重复添加", params);
        	break;
        case ERROR_10013:
        	this.setErroDesc("车辆已预约车位,暂时不能删除", params);
        	break;
        case ERROR_10014:
        	this.setErroDesc("车辆已进场,暂时不能删除", params);
        	break;
        case ERROR_10015:
        	this.setErroDesc("手机号码被占用", params);
        	break;
        case ERROR_10016:
        	this.setErroDesc("原密码错误", params);
        	break;
        case ERROR_10017:
        	this.setErroDesc("该微信账号已被绑定", params);
        	break;
        case ERROR_10018:
        	this.setErroDesc("该账号已绑定微信", params);
        	break;
        case ERROR_10019:
        	this.setErroDesc("发票金额不能为零", params);
        	break;
        case ERROR_10020:
        	this.setErroDesc("该车辆存在未结束的订单,暂时不可以预约", params);
        	break;
        case ERROR_10021:
        	this.setErroDesc("暂时没有可以预约的车位,请稍后再试", params);
        	break;
        case ERROR_10022:
        	this.setErroDesc("当前状态不可取消预约", params);
        	break;
        case ERROR_10023:
        	this.setErroDesc("抱歉,只有支付10分钟以内才可取消订单", params);
        	break;
        case ERROR_10024:
        	this.setErroDesc("支付失败", params);
        	break;
        case ERROR_10025:
        	this.setErroDesc("账户余额不足", params);
        	break;
        case ERROR_10026:
        	this.setErroDesc("该手机号与当前绑定的手机号相同", params);
        	break;
        case ERROR_10027:
        	this.setErroDesc("暂时没有临停的车位", params);
        	break;
        case ERROR_10028:
        	this.setErroDesc("录入订单失败,该车辆存在未结束的订单", params);
        	break;
        case ERROR_10029:
        	this.setErroDesc("当前需缴纳的金额为0", params);
        	break;
        }
    }

    public void setErroDesc(String erroDesc, Object... params) {
        super.setErroDesc(String.format(erroDesc, params));
    }
}

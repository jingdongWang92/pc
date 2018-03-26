package com.jcble.jcparking.common.model.pay;

import org.apache.ibatis.type.JdbcType;

import com.jcble.jcparking.common.model.BaseDto;

import baseproj.common.mybatis.mapper.annotation.FieldMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.TableMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "t_wx_pay_callback_log", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class WxPayCallbackLog extends BaseDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3132015565325525257L;
	
	@FieldMapperAnnotation(dbFieldName = "id", jdbcType = JdbcType.INTEGER)
	private Integer id; // 主键ID
	@FieldMapperAnnotation(dbFieldName = "createtime", jdbcType = JdbcType.VARCHAR)
	private String createtime;
	@FieldMapperAnnotation(dbFieldName = "return_code", jdbcType = JdbcType.VARCHAR)
	private String return_code; // 返回状态码
	@FieldMapperAnnotation(dbFieldName = "return_msg", jdbcType = JdbcType.VARCHAR)
	private String return_msg; // 返回信息
	@FieldMapperAnnotation(dbFieldName = "appid", jdbcType = JdbcType.VARCHAR)
	private String appid;
	@FieldMapperAnnotation(dbFieldName = "mch_id", jdbcType = JdbcType.VARCHAR)
	private String mch_id;
	@FieldMapperAnnotation(dbFieldName = "device_info", jdbcType = JdbcType.VARCHAR)
	private String device_info;
	@FieldMapperAnnotation(dbFieldName = "nonce_str", jdbcType = JdbcType.VARCHAR)
	private String nonce_str;
	@FieldMapperAnnotation(dbFieldName = "sign", jdbcType = JdbcType.VARCHAR)
	private String sign;
	@FieldMapperAnnotation(dbFieldName = "result_code", jdbcType = JdbcType.VARCHAR)
	private String result_code;
	@FieldMapperAnnotation(dbFieldName = "err_code", jdbcType = JdbcType.VARCHAR)
	private String err_code;
	@FieldMapperAnnotation(dbFieldName = "err_code_des", jdbcType = JdbcType.VARCHAR)
	private String err_code_des;
	@FieldMapperAnnotation(dbFieldName = "openid", jdbcType = JdbcType.VARCHAR)
	private String openid;
	@FieldMapperAnnotation(dbFieldName = "is_subscribe", jdbcType = JdbcType.CHAR)
	private String is_subscribe;
	@FieldMapperAnnotation(dbFieldName = "trade_type", jdbcType = JdbcType.VARCHAR)
	private String trade_type;
	@FieldMapperAnnotation(dbFieldName = "bank_type", jdbcType = JdbcType.VARCHAR)
	private String bank_type;
	@FieldMapperAnnotation(dbFieldName = "total_fee", jdbcType = JdbcType.INTEGER)
	private Integer total_fee;
	@FieldMapperAnnotation(dbFieldName = "cash_fee", jdbcType = JdbcType.INTEGER)
	private Integer cash_fee;
	@FieldMapperAnnotation(dbFieldName = "coupon_fee", jdbcType = JdbcType.INTEGER)
	private Integer coupon_fee;
	@FieldMapperAnnotation(dbFieldName = "coupon_count", jdbcType = JdbcType.INTEGER)
	private Integer coupon_count;
	@FieldMapperAnnotation(dbFieldName = "fee_type", jdbcType = JdbcType.VARCHAR)
	private String fee_type;
	@FieldMapperAnnotation(dbFieldName = "cash_fee_type", jdbcType = JdbcType.VARCHAR)
	private String cash_fee_type;
	@FieldMapperAnnotation(dbFieldName = "coupon_id_$n", jdbcType = JdbcType.VARCHAR)
	private String coupon_id_$n;
	@FieldMapperAnnotation(dbFieldName = "transaction_id", jdbcType = JdbcType.VARCHAR)
	private String transaction_id;
	@FieldMapperAnnotation(dbFieldName = "coupon_fee_$n", jdbcType = JdbcType.INTEGER)
	private Integer coupon_fee_$n;
	@FieldMapperAnnotation(dbFieldName = "out_trade_no", jdbcType = JdbcType.VARCHAR)
	private String out_trade_no;
	@FieldMapperAnnotation(dbFieldName = "attach", jdbcType = JdbcType.VARCHAR)
	private String attach;
	@FieldMapperAnnotation(dbFieldName = "time_end", jdbcType = JdbcType.VARCHAR)
	private String time_end;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getErr_code() {
		return err_code;
	}

	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}

	public String getErr_code_des() {
		return err_code_des;
	}

	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getIs_subscribe() {
		return is_subscribe;
	}

	public void setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getBank_type() {
		return bank_type;
	}

	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}

	public Integer getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(Integer total_fee) {
		this.total_fee = total_fee;
	}

	public Integer getCash_fee() {
		return cash_fee;
	}

	public void setCash_fee(Integer cash_fee) {
		this.cash_fee = cash_fee;
	}

	public Integer getCoupon_fee() {
		return coupon_fee;
	}

	public void setCoupon_fee(Integer coupon_fee) {
		this.coupon_fee = coupon_fee;
	}

	public Integer getCoupon_count() {
		return coupon_count;
	}

	public void setCoupon_count(Integer coupon_count) {
		this.coupon_count = coupon_count;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public String getCash_fee_type() {
		return cash_fee_type;
	}

	public void setCash_fee_type(String cash_fee_type) {
		this.cash_fee_type = cash_fee_type;
	}

	public String getCoupon_id_$n() {
		return coupon_id_$n;
	}

	public void setCoupon_id_$n(String coupon_id_$n) {
		this.coupon_id_$n = coupon_id_$n;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public Integer getCoupon_fee_$n() {
		return coupon_fee_$n;
	}

	public void setCoupon_fee_$n(Integer coupon_fee_$n) {
		this.coupon_fee_$n = coupon_fee_$n;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getTime_end() {
		return time_end;
	}

	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}

}

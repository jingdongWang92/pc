package com.jcble.jcparking.common;

public class CommonConstants {

	/** 默认分页起始页 */
	public static final int PAGE_INDEX = 1;

	/** 默认分页记录条数 */
	public static final int PAGE_SIZE = 10;

	/** 摘要加密MD5种子 */
	public static final String SECRET_CODE = "jcip";
	
	/** 摘要的参数名 */
	public static final String PARAMTER_SIGN = "sign";

	/** Http Get 请求 */
	public static final String HTTPMETHOD_GET = "GET";
	
	/** Http Delete 请求 */
	public static final String HTTPMETHOD_DELETE = "DELETE";

	public static final String DEFAULT_VERSION = "1";
	
	public static final String ALIPAY_TRADE_SUCCESS = "TRADE_SUCCESS";
	public static final String ALIPAY_TRADE_CLOSE = "TRADE_CLOSED";
	public static final String ALIPAY_TRADE_FAIL = "TRADE_FAIL";
	
	public static final String WXPAY_SUCCESS_RESULT_CODE = "SUCCESS";
	public static final String WXPAY_FAIL_RESULT_CODE = "FAIL";

}

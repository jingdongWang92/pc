package com.jcble.jcparking.common.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;

/**
 * <pre>
 * <b>高进度计算辅助.</b>
 * <b>Description:</b> 提供高精度数据计算的封装, 默认保留2位小数精度.
 * 
 * <b>Author:</b> yangjiechao@yocheche.com
 * <b>Date:</b> 2014-1-1 上午10:00:01
 * <b>Copyright:</b> Copyright &copy;2006-2015 yangjiechao@yocheche.com., Ltd. All rights reserved.
 * <b>Changelog:</b> 
 *   ----------------------------------------------------------------------
 *   1.0   2014-01-01 10:00:01    yangjiechao@yocheche.com
 *         new file.
 * </pre>
 */
public abstract class MathUtil {

    /**
     * 默认除法运算精度长度,即保留小数点多少位.
     */
    public static final int SCALE_LENGTH = 2;

    /**
     * 数值默认表示格式，2位小数 (#0.00).
     */
    public static final String FORMAT = "#0.00";
    
    /**地球平均半径*/  
    private static final double EARTH_RADIUS = 6378137; 

    // 默认构造方法.
    protected MathUtil() {
        super();
    }

    /**
     * 精确加法运算, 默认精度：2位小数.
     * 
     * @param v1 被加数
     * @param v2 加数
     * @return double 两个参数的和
     */
    public static BigDecimal add(BigDecimal v1, BigDecimal v2) {
        return add(v1, v2, SCALE_LENGTH);
    }

    /**
     * 精确加法运算.
     * 
     * @param v1 被加数
     * @param v2 加数
     * @param scale 精度
     * @return double 两个参数的和
     */
    public static BigDecimal add(BigDecimal v1, BigDecimal v2, int scale) {
        return (v1.add(v2)).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 精确减法运, 默认精度：2位小数.
     * 
     * @param v1 被减数
     * @param v2 减数
     * @return double 两个参数的差
     */
    public static BigDecimal sub(BigDecimal v1, BigDecimal v2) {
        return sub(v1, v2, SCALE_LENGTH);
    }

    /**
     * 精确减法运.
     * 
     * @param v1 被减数
     * @param v2 减数
     * @param scale 精度
     * @return double 两个参数的差
     */
    public static BigDecimal sub(BigDecimal v1, BigDecimal v2, int scale) {
        return (v1.subtract(v2)).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 精确乘法运算, 默认精度：2位小数.
     * 
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static BigDecimal mul(BigDecimal v1, BigDecimal v2) {
        return mul(v1, v2, SCALE_LENGTH);
    }

    /**
     * 精确乘法运算
     * 
     * @param v1 被乘数
     * @param v2 乘数
     * @param scale 精度
     * @return 两个参数的积
     */
    public static BigDecimal mul(BigDecimal v1, BigDecimal v2, int scale) {
        return (v1.multiply(v2)).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * <pre>
     * （相对）精确除法运算，当发生除不尽的情况时，精确到 小数点以后多少位，以后的数字四舍五入
     * 默认精度：2位小数
     * </pre>
     * 
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static BigDecimal div(BigDecimal v1, BigDecimal v2) {
        return div(v1, v2, SCALE_LENGTH);
    }

    /**
     * （相对）精确的除法运算，当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入.
     * 
     * @param v1 被除数
     * @param v2 除数
     * @param scale 精度
     * @return double 两个参数的商
     */
    public static BigDecimal div(BigDecimal v1, BigDecimal v2, int scale) {
        if (scale < 0) {
            return div(v1, v2, SCALE_LENGTH);
        }
        return (v1.divide(v2, scale, BigDecimal.ROUND_HALF_UP));
    }

    /**
     * 计算Factorial阶乘.
     * 
     * @param n 任意大于等于0的int
     * @return n BigInteger !的值
     */
    public static BigInteger factorial(int n) {
        if (n < 0) {
            return new BigInteger("-1");
        } else if (n == 0) {
            return new BigInteger("0");
        }

        // 将数组换成字符串后构造BigInteger
        BigInteger result = new BigInteger("1");
        for (; n > 0; n--) {
            // 将数字n转换成字符串后，再构造一个BigInteger对象，与现有结果做乘法
            result = result.multiply(new BigInteger(new Integer(n).toString()));
        }
        return result;
    }

    /**
     * 降低小数的精度, 默认精度：2位小数.
     * 
     * @param v1 目标小数.
     * @return double
     */
    public static double scale(double v1) {
        return scale(v1, 2);
    }

    /**
     * 降低小数的精度.
     * 
     * @param v1 目标小数.
     * @param scale 小数精度.
     * @return double
     */
    public static double scale(double v1, int scale) {
        return new BigDecimal(Double.toString(v1)).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 数字渲染格式化 默认格式：#0.00.
     * 
     * @param value
     * @return String
     */
    public static String format(double value) {
        return format(value, FORMAT);
    }

    /**
     * 数字渲染格式化.
     * 
     * @param value
     * @param ft 例如：#0.00
     * @return String
     */
    public static String format(double value, String ft) {
        return new DecimalFormat(ft).format(value);
    }

    /**  
     * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米  
     * @param lng1  
     * @param lat1  
     * @param lng2  
     * @param lat2  
     * @return  
     */  
    public static int getDistance(double lng1, double lat1, double lng2, double lat2){  
       double radLat1 = rad(lat1);  
       double radLat2 = rad(lat2);  
       double a = radLat1 - radLat2;  
       double b = rad(lng1) - rad(lng2);  
       double s = 2 * Math.asin(  
            Math.sqrt(  
                Math.pow(Math.sin(a/2),2)   
                + Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)  
            )  
        );  
       s = s * EARTH_RADIUS;  
       s = Math.round(s * 10000) / 10000;  
       return (int)s;  
    }  
     
    //把经纬度转为度（°）  
    private static double rad(double d){  
       return d * Math.PI / 180.0;  
    }
}
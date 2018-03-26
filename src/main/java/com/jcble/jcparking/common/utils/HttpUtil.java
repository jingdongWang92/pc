package com.jcble.jcparking.common.utils;

import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;


public class HttpUtil {

    protected static final Logger log = LoggerFactory.getLogger(HttpUtil.class);

    private static final boolean showlog = true;

    public static boolean showConsole = false;

    public static String sendGetRequest(String reqURL, Map<String, String> params) throws Exception {
        return sendGetRequest(reqURL, null, params, null, null);
    }

    public static String sendGetRequest(String reqURL, Map<String, String> headers, Map<String, String> params)
            throws Exception {
        return sendGetRequest(reqURL, headers, params, null, null);
    }

    public static String sendPostRequest(String reqURL, Map<String, String> params) throws Exception {
        return sendPostRequest(reqURL, null, params, null, null);
    }

    public static String sendPostRequest(String reqURL, Map<String, String> headers, Map<String, String> params)
            throws Exception {
        return sendPostRequest(reqURL, headers, params, null, null);
    }

    public static String sendPutRequest(String reqURL, Map<String, String> params) throws Exception {
        return sendPutRequest(reqURL, null, params, null, null);
    }

    public static String sendPutRequest(String reqURL, Map<String, String> headers, Map<String, String> params)
            throws Exception {
        return sendPutRequest(reqURL, headers, params, null, null);
    }

    public static String sendPostRequest(String reqURL, Map<String, String> headers, Map<String, String> params,
            String encodeCharset, String decodeCharset) throws Exception {
        String sessionId = logStart("post", reqURL);
        logCenter("post", sessionId, headers, params, encodeCharset, decodeCharset);
        String responseContent = null;
        HttpResponse response = null;
        HttpClient httpClient = new DefaultHttpClient();

        HttpPost httpPost = new HttpPost(reqURL);

        if (null != headers && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }

        List<NameValuePair> formParams = new ArrayList<NameValuePair>(); // 创建参数队列
        if (null != params && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(formParams, encodeCharset == null ? "UTF-8" : encodeCharset));
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                responseContent = EntityUtils.toString(entity, decodeCharset == null ? "UTF-8" : decodeCharset);
                EntityUtils.consume(entity);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        logEnd("post", sessionId, response, responseContent);
        return responseContent;
    }

    public static String sendGetRequest(String reqURL, Map<String, String> headers, Map<String, String> params,
            String encodeCharset, String decodeCharset) throws Exception {
        String sessionId = logStart("get", reqURL);
        logCenter("get", sessionId, headers, params, encodeCharset, decodeCharset);
        String responseContent = null;
        HttpResponse response = null;
        HttpClient httpClient = new DefaultHttpClient();

        StringBuffer sb = new StringBuffer();
        sb.append(reqURL);
        if (null != params && !params.isEmpty()) {
            sb.append("?");
            boolean flag = false;
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (flag) {
                    sb.append("&");
                } else {
                    flag = true;
                }
                sb.append(entry.getKey()).append("=");
                if (null != entry.getValue()) {
                    sb.append(URLEncoder.encode(entry.getValue()));
                }
            }
        }
        HttpGet httpGet = new HttpGet(sb.toString());

        if (null != headers && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpGet.setHeader(entry.getKey(), entry.getValue());
            }
        }
        try {

            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                responseContent = EntityUtils.toString(entity, decodeCharset == null ? "UTF-8" : decodeCharset);
                EntityUtils.consume(entity);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        logEnd("get", sessionId, response, responseContent);
        return responseContent;
    }

    public static String sendPutRequest(String reqURL, Map<String, String> headers, Map<String, String> params,
            String encodeCharset, String decodeCharset) throws Exception {
        String sessionId = logStart("post", reqURL);
        logCenter("post", sessionId, headers, params, encodeCharset, decodeCharset);
        String responseContent = null;
        HttpResponse response = null;
        HttpClient httpClient = new DefaultHttpClient();

        HttpPut httpPut = new HttpPut(reqURL);

        if (null != headers && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPut.setHeader(entry.getKey(), entry.getValue());
            }
        }

        try {
            httpPut.setEntity(new StringEntity(JsonUtil.map2json(params), "UTF-8"));

            response = httpClient.execute(httpPut);
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                responseContent = EntityUtils.toString(entity, decodeCharset == null ? "UTF-8" : decodeCharset);
                EntityUtils.consume(entity);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        logEnd("post", sessionId, response, responseContent);
        return responseContent;
    }

    /**
     * 发送HTTPS_POST请求
     * 
     * @param reqURL
     *            请求地址
     * @param params
     *            请求参数
     * @return 远程主机响应正文
     */
    public static String sendPostSSLJsonRequest(String reqURL, Map<String, String> params,String token) {
        String sessionId = logStart("post", reqURL);
        logCenter("post", sessionId, null, params, null, null);
        String responseContent = "";
        HttpClient httpClient = getSSLClient();
        HttpResponse response = null;

        try {
            HttpPost httpPost = new HttpPost(reqURL);
            List<NameValuePair> headers = new ArrayList<NameValuePair>();
            headers.add(new BasicNameValuePair("Content-Type", "application/json"));
            if(StringUtils.isNoneBlank(token)) {
            	headers.add(new BasicNameValuePair("authorization", "Bearer "+token));
            }
            for (NameValuePair nameValuePair : headers) {
                httpPost.addHeader(nameValuePair.getName(), nameValuePair.getValue());
            }
            httpPost.setEntity(new StringEntity(JsonUtil.map2json(params), "UTF-8"));

            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                responseContent = EntityUtils.toString(entity, "UTF-8");
                EntityUtils.consume(entity);
            }
        } catch (Exception e) {
            log.error("与[" + reqURL + "]通信过程中发生异常,堆栈信息为", e);
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        logEnd(" post ssl", sessionId, response, responseContent);
        return responseContent;
    }

    public static String sendPostJsonRequest(String reqURL, Map<String, String> headers, Map<String, String> params,
            String encodeCharset, String decodeCharset) throws Exception {
        String sessionId = logStart("post", reqURL);
        logCenter("post", sessionId, null, params, null, null);
        String responseContent = "";
        HttpClient httpClient = HttpUtil.getDefaultClient();
        HttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(reqURL);
            if (null != headers && !headers.isEmpty()) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpPost.addHeader(entry.getKey(), entry.getValue());
                }
            }
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.setEntity(new StringEntity(JsonUtil.map2json(params), "UTF-8"));

            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                responseContent = EntityUtils.toString(entity, "UTF-8");
                EntityUtils.consume(entity);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        logEnd(" post json", sessionId, response, responseContent);
        return responseContent;
    }

    public static CloseableHttpClient getSSLClient() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                // 信任所有
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return HttpClients.createDefault();
    }

    public static CloseableHttpClient getDefaultClient() {
        return HttpClients.createDefault();
    }

    private static String logStart(String type, String reqURL) {
        if (!showlog) {
            return null;
        }
        String sessionId = String.valueOf(System.currentTimeMillis());
        StringBuffer sb = new StringBuffer();
        sb.append("sessionId=").append(sessionId);
        sb.append(" request type:" + type + " url:").append(reqURL);
        info(sb.toString());
        return sessionId;
    }

    private static void logCenter(String type, String sessionId, Map<String, String> headers,
            Map<String, String> params, String encodeCharset, String decodeCharset) {
        if (!showlog) {
            return;
        }
        StringBuffer sb = new StringBuffer();
        sb.append("sessionId=").append(sessionId);
        JSONObject object = new JSONObject();
        object.put("headers", headers);
        object.put("params", params);
        object.put("encodeCharset", encodeCharset);
        object.put("decodeCharset", decodeCharset);
        String objectJsonStr = object.toString();
        sb.append(" request type:" + type + " param:").append(objectJsonStr);
        info(sb.toString());
    }

    private static void logEnd(String type, String sessionId, HttpResponse response, String responseContent) {
        if (!showlog) {
            return;
        }
        StringBuffer sb = new StringBuffer();
        sb.append("sessionId=").append(sessionId);
        JSONObject object = new JSONObject();
        if (null != response) {
            object.put("statusCode", response.getStatusLine().getStatusCode());
            object.put("responseBody", responseContent);
        }
        String objectJsonStr = object.toString();
        sb.append(" response type:" + type + " result:").append(objectJsonStr);
        info(sb.toString());
    }

    private static void info(String msg) {
        log.info(msg);
        if (showConsole) {
            System.out.println(msg);
        }
    }
}

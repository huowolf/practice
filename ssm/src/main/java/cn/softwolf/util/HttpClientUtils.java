package cn.softwolf.util;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClients;

public class HttpClientUtils {
    /**
     * 初始化HttpClient
     */
    private static HttpClient httpClient = null;
    /**
     * 生产HttpClient实例
     * 公开，静态的工厂方法，需要使用时才去创建该单体
     *
     * @return
     */
    public static HttpClient getHttpClient() {
        if (httpClient == null) {
            httpClient = HttpClients.createDefault();
        }
        return httpClient;
    }
    /**
     * POST方式调用
     *
     * @param url
     * @param params 参数为NameValuePair键值对对象
     * @return 响应字符串
     * @throws java.io.UnsupportedEncodingException
     */
    public static String executeByPOST(String url, List<NameValuePair> params) {
        HttpClient httpclient = getHttpClient();
        HttpPost post = new HttpPost(url);

        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        
        String responseJson = null;
        try {
            if (params != null) {
                post.setEntity(new UrlEncodedFormEntity(params));
            }
            responseJson = httpclient.execute(post, responseHandler);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpclient.getConnectionManager().closeExpiredConnections();
        }
        return responseJson;
    }
    /**
     * Get方式请求
     *
     * @param url    带参数占位符的URL，例：
     * @param params 参数值数组，需要与url中占位符顺序对应
     * @return 响应字符串
     * @throws java.io.UnsupportedEncodingException
     */
    public static String executeByGET(String url, Object[] params) {
        HttpClient httpclient = getHttpClient();
        String messages = MessageFormat.format(url, params);
        HttpGet get = new HttpGet(messages);
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String responseJson = null;
        try {
            responseJson = httpclient.execute(get, responseHandler);
            
            responseJson = new String(responseJson.getBytes("GBK"),"UTF-8");
            
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            httpclient.getConnectionManager().closeExpiredConnections();
        }
        return responseJson;
    }
    /**
     * @param url
     * @return
     */
    public static String executeByGET(String url) {
        HttpClient httpclient = getHttpClient();
        HttpGet get = new HttpGet(url);
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String responseJson = null;
        try {
            responseJson = httpclient.execute(get, responseHandler);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpclient.getConnectionManager().closeExpiredConnections();
        }
        return responseJson;
    }
}


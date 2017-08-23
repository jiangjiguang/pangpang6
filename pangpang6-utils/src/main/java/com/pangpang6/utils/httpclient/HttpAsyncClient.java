package com.pangpang6.utils.httpclient;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * Created by jiangjg on 2017/7/17.
 */
public class HttpAsyncClient {
    private static Logger logger = LoggerFactory.getLogger(HttpAsyncClient.class);
    private final static boolean KEEP_ALIVE=false;

    private int connectionTimeout = 1000;  //单位ms 从服务器读取数据的timeout
    private int socketTimeout = 30000;    //单位ms 和服务器建立连接的timeout
    private int connectionRequestTimeout = 3000;  //单位ms 从连接池获取连接的timeout

    private CloseableHttpAsyncClient httpclient;
    private CloseableHttpAsyncClient httpsclient;

    private RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(socketTimeout)
            .setConnectTimeout(connectionTimeout)
            .setConnectionRequestTimeout(connectionRequestTimeout)
            .build();

    private static HttpAsyncClient instance = new HttpAsyncClient();

    private HttpAsyncClient(){
        httpStart();
        httpsStart();
    }



    private void httpStart(){
        if(httpclient == null){
            httpclient = HttpAsyncClients.createDefault();
        }
        if(httpclient.isRunning()){
            return;
        }
        httpclient.start();
    }

    private void httpsStart(){
        if(httpsclient == null) {
            try {
                SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                    @Override
                    public boolean isTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {
                        return true;
                    }
                }).build();
                httpsclient = HttpAsyncClients.custom().setSSLContext(sslContext).build();
            } catch (Exception e) {
                logger.error(ExceptionUtils.getStackTrace(e));
            }
        }
        if(httpsclient.isRunning()){
            return;
        }
        httpsclient.start();
    }

    public void close(){
        try {
            if(httpclient!=null&&httpclient.isRunning()){
                httpclient.close();
            }
        }catch (Exception e){
            logger.error("close httpclient异常: " + ExceptionUtils.getStackTrace(e));
        }
        try {
            if(httpsclient!=null&&httpsclient.isRunning()){
                httpsclient.close();
            }
        }catch (Exception e){
            logger.error("close httpsclient异常: " + ExceptionUtils.getStackTrace(e));
        }
    }


    public static HttpAsyncClient getInstance(){
        return instance;
    }


    /**
     * 发送 post请求
     * @param httpUrl 地址
     */
    public void sendHttpPost(String httpUrl, FutureCallback<HttpResponse> callback) throws IOException {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        sendHttpPost(httpPost, callback);
    }

    /**
     * 发送 post请求
     * @param httpUrl 地址
     * @param params  请求参数
     * @param charset 字符编码
     * @param contentType  contentType跟请求参数要对应如：params是json根式，contentType为application/json
     */
    public void sendHttpPost(String httpUrl, String params, String charset, String contentType, FutureCallback<HttpResponse> callback) throws IOException {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        //设置参数
        StringEntity stringEntity = new StringEntity(params, charset);
        stringEntity.setContentType(contentType);
        httpPost.setEntity(stringEntity);
        sendHttpPost(httpPost,callback);
    }

    /**
     * 发送 post请求
     * @param httpUrl 地址
     * @param maps    参数
     */
    public void sendHttpPost(String httpUrl, Map<String, String> maps, FutureCallback<HttpResponse> callback) throws IOException {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        // 创建参数队列
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        for (String key : maps.keySet()) {
            nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
        sendHttpPost(httpPost, callback);
    }
    /**
     * 发送 get请求
     * @param httpUrl
     */
    public void sendHttpGet(String httpUrl,FutureCallback<HttpResponse> callback) throws IOException{
        HttpGet httpGet = new HttpGet(httpUrl);// 创建get请求
        sendHttpGet(httpGet, callback);
    }
    public void sendHttpsGet(String url, FutureCallback<HttpResponse> callback){
        HttpGet httpGet =new HttpGet(url);
        sendHttpsGet(httpGet, callback);
    }

    /**
     * 发送 post请求
     * @param httpUrl 地址
     */
    public void sendHttpsPost(String httpUrl, FutureCallback<HttpResponse> callback) throws IOException {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        sendHttpsPost(httpPost, callback);
    }

    /**
     * 发送 post请求
     * @param httpUrl 地址
     * @param params  请求参数
     * @param charset 字符编码
     * @param contentType  contentType跟请求参数要对应如：params是json根式，contentType为application/json
     */
    public void sendHttpsPost(String httpUrl, String params, String charset, String contentType, FutureCallback<HttpResponse> callback) throws IOException{
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        //设置参数
        StringEntity stringEntity = new StringEntity(params, charset);
        stringEntity.setContentType(contentType);
        httpPost.setEntity(stringEntity);
        sendHttpsPost(httpPost,callback);
    }

    /**
     * 发送 post请求
     * @param httpUrl 地址
     * @param maps    参数
     */
    public void sendHttpsPost(String httpUrl, Map<String, String> maps, FutureCallback<HttpResponse> callback) throws  IOException{
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        // 创建参数队列
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        for (String key : maps.keySet()) {
            nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
        sendHttpsPost(httpPost, callback);
    }

    public void sendHttpPost(HttpPost httpPost, FutureCallback<HttpResponse> callback) {
        httpPost.setConfig(requestConfig);
        httpStart();
        httpclient.execute(httpPost, callback);
    }
    public void sendHttpGet(HttpGet httpGet, FutureCallback<HttpResponse> callback){
        httpGet.setConfig(requestConfig);
        httpStart();
        httpclient.execute(httpGet, callback);
    }

    public void sendHttpsGet(HttpGet httpGet, FutureCallback<HttpResponse> callback){
        httpGet.setConfig(requestConfig);
        httpsStart();
        httpsclient.execute(httpGet, callback);
    }
    public void sendHttpsPost(HttpPost httpPost, FutureCallback<HttpResponse> callback){
        httpPost.setConfig(requestConfig);
        httpsStart();
        httpsclient.execute(httpPost, callback);
    }

    public Future<HttpResponse> sendHttpGet(HttpGet httpGet){
        return httpclient.execute(httpGet, null);
    }
}

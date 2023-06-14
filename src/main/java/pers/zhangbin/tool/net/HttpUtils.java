package pers.zhangbin.tool.net;

import pers.zhangbin.tool.common.util.StringUtils;
import pers.zhangbin.tool.net.util.SSLSocketClient;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * ClassName: HttpUtils <br>
 * Description: <p> 执行Http与Https相关的方法 </p>  <br>
 *
 * @author zhangbin
 * @create 2022/2/11 16:19
 * @since JDK1.8
 */
public class HttpUtils {

    private static final Integer CONNECTION_TIMEOUT = 15000;
    private static final Integer READ_TIMEOUT = 60000;
    /**
     * HTTP状态码
     */
    private static final Integer HTTP_CODE_SUCCESS = 200;
    private static final Integer HTTP_CODE_BAD_REQUEST = 400;
    private static final Integer HTTP_CODE_UNAUTHORIZED = 401;
    private static final Integer HTTP_CODE_FORBIDDEN = 403;
    private static final Integer HTTP_CODE_NOT_FOUNT = 404;
    private static final Integer HTTP_CODE_METHOD_NOT_ALLOWED = 405;
    private static final Integer HTTP_CODE_REQUEST_TIMEOUT = 408;
    private static final Integer HTTP_CODE_INTERNAL_SERVER_ERROR = 500;
    private static final Integer HTTP_CODE_BAD_GATEWAY = 502;
    private static final Integer HTTP_CODE_GATEWAY_TIMEOUT = 504;

    /**
     * <p> 执行Http请求 </p>
     *
     * @param request 请求对象
     * @return 请求结果
     */
    public static HttpResponse executeHttp(HttpRequest request) throws IOException {
        HttpURLConnection conn = getHttpConnection(request);
        conn.connect();
        HttpResponse response = null;
        if (conn.getResponseCode() == HTTP_CODE_SUCCESS) {
            response = HttpResponse.success();
            response.setData(getResponseData(conn));
        } else {
            response = HttpResponse.error(StringUtils.getStringByInStream(conn.getErrorStream(), StandardCharsets.UTF_8));
        }
        return response;
    }

    /**
     * <p> 使用SSL证书访问HTTPS网址 </p>
     *
     * @param request 请求对象
     * @param stream  证书流
     * @return 请求结果对象
     */
    public static HttpResponse executeHttps(HttpRequest request, InputStream stream) throws IOException {
        HttpURLConnection conn = getConnection(request.getUrl(), stream, request.getMethod().getName());
        conn.connect();
        HttpResponse response = null;
        if (conn.getResponseCode() == HTTP_CODE_SUCCESS) {
            response = HttpResponse.success();
            response.setData(getResponseData(conn));
        } else {
            response = HttpResponse.error(StringUtils.getStringByInStream(conn.getErrorStream(), StandardCharsets.UTF_8));
        }
        return response;
    }

    /**
     * <p> 访问HTTPS网址，绕过SSL检测 </p>
     *
     * @param request 访问请求对象
     * @return 访问返回结果数据对象
     */
    public static HttpResponse executeHttps(HttpRequest request) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        URL url = new URL(request.getUrl());
        trustAllHttpsCertificates();
        HttpsURLConnection.setDefaultHostnameVerifier(SSLSocketClient.getHostnameVerifier());
//        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        HttpsURLConnection conn = getHttpsConnection(request);

        conn.setRequestMethod(request.getMethod().getName());
        conn.setConnectTimeout(CONNECTION_TIMEOUT);
        conn.setReadTimeout(READ_TIMEOUT);
        HttpResponse response = new HttpResponse();
        if (conn.getResponseCode() == HTTP_CODE_SUCCESS) {
            response = HttpResponse.success();
            response.setData(getResponseData(conn));
        } else {
            response = HttpResponse.error(StringUtils.getStringByInStream(conn.getErrorStream(), StandardCharsets.UTF_8));
        }
        return response;
    }

    /**
     * 信任所有证书
     */
    private static void trustAllHttpsCertificates() throws NoSuchAlgorithmException, KeyManagementException {
        TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
        TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }

    private static class miTM implements javax.net.ssl.TrustManager, javax.net.ssl.X509TrustManager {
        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        @Override
        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
        }

        @Override
        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
        }
    }

    /**
     * <p> 连接网址后好，从连接中获取的输入流转换为字符串 </p>
     *
     * @param conn 连接对象
     * @return 访问结果
     */
    private static String getResponseData(URLConnection conn) throws IOException {
        InputStream is = conn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        String tmp;
        StringBuilder builder = new StringBuilder();
        while ((tmp = reader.readLine()) != null) {
            builder.append(tmp).append("\r\n");
        }
        return builder.toString();
    }

    /**
     * <p> 获取HTTPS方式的连接对象 </p>
     *
     * @param url    访问地址
     * @param stream SSL证书流
     * @return HTTPS连接对象
     */
    private static HttpsURLConnection getConnection(String url, InputStream stream, String method) throws IOException {
        URL u = new URL(url);
        HttpsURLConnection connection = (HttpsURLConnection) u.openConnection();
        connection.setSSLSocketFactory(SSLSocketClient.getSSlSocketFactory(stream));
        connection.setRequestMethod(method);
        connection.setConnectTimeout(CONNECTION_TIMEOUT);
        connection.setReadTimeout(READ_TIMEOUT);
        return connection;
    }

    /**
     * <p> 获取HTTP方式的连接对象 </p>
     *
     * @param request 访问地址信息、访问的请求参数、访问方式等封装而成的对象
     * @return HTTP连接对象
     */
    private static HttpURLConnection getHttpConnection(HttpRequest request) throws IOException {
        URL u = new URL(request.getUrl());
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();
        setConnection(conn, request);
        return conn;
    }

    /**
     * <p> 获取HTTPS方式的连接对象 </p>
     * @param request 请求信息
     * @return HTTPS连接对象
     */
    private static HttpsURLConnection getHttpsConnection(HttpRequest request) throws IOException {
        URL u = new URL(request.getUrl());
        HttpsURLConnection conn = (HttpsURLConnection) u.openConnection();
        setConnection(conn, request);
        return conn;
    }

    /**
     * <p> 设置Connection的信息 </p>
     * @param conn 连接对象
     * @param request 访问信息
     */
    private static void setConnection(HttpURLConnection conn, HttpRequest request) throws IOException {
        conn.setRequestMethod(request.getMethod().getName());
        conn.setConnectTimeout(15000);
        conn.setReadTimeout(60000);
        switch (request.getMethod()) {
            case GET:
                break;
            case POST:
            case PUT:
            case DELETE:
                // 设置头部信息
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Accept", "application/json");
                conn.setRequestProperty("Charset", "UTF-8");
                // 设置访问数据
                conn.setDoInput(true);
                conn.setDoOutput(true);
                OutputStream os = conn.getOutputStream();
                os.write(request.getBody().getBytes(StandardCharsets.UTF_8));
                os.flush();
                os.close();
                break;
            default:
                throw new RuntimeException("method is not allowed");
        }
    }
}

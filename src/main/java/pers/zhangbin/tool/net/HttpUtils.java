package pers.zhangbin.tool.net;


import pers.zhangbin.tool.common.util.StringUtils;
import pers.zhangbin.tool.net.util.SSLSocketClient;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

/**
 * ClassName: HttpClient <br>
 * Description: <p>  </p>  <br>
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
     * <p> 通过Get方式访问https链接获取数据 </p>
     *
     * @param url    访问地址
     * @param stream SSL证书流
     * @return 响应体数据
     */
    public static HttpResponse executeGetHttps(String url, InputStream stream) throws IOException {
        HttpResponse response = new HttpResponse();
        HttpsURLConnection connection = getConnection(url, stream, "GET");
        connection.connect();
        if (connection.getResponseCode() == HTTP_CODE_SUCCESS) {
            response.setData(getResponseData(connection));
        } else {
            response.setError(StringUtils.getStringByInStream(connection.getErrorStream(), StandardCharsets.UTF_8));
        }
        return response;
    }

    /**
     * <p> 通过Get方式访问http链接获取数据 </p>
     *
     * @param url 访问地址
     * @return 响应体数据
     */
    public static HttpResponse executeGetHttp(String url) throws IOException {
        HttpResponse response = new HttpResponse();
        HttpURLConnection connection = getConnection(url, "GET");
        connection.connect();
        if (connection.getResponseCode() == HTTP_CODE_SUCCESS) {
            response.setData(getResponseData(connection));
        } else {
            response.setError(StringUtils.getStringByInStream(connection.getErrorStream(), StandardCharsets.UTF_8));
        }
        return response;
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
     * @param url 访问地址
     * @return HTTP连接对象
     */
    private static HttpURLConnection getConnection(String url, String method) throws IOException {
        URL u = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) u.openConnection();
        connection.setRequestMethod(method);
        connection.setConnectTimeout(15000);
        connection.setReadTimeout(60000);
        return connection;
    }
}

package pers.zhangbin.tool.net;

import com.alibaba.fastjson.JSON;
import pers.zhangbin.tool.common.util.StringUtils;
import pers.zhangbin.tool.net.enumeration.HttpMethod;
import pers.zhangbin.tool.net.function.RequestInvoke;
import pers.zhangbin.tool.web.Result;

import java.io.IOException;
import java.io.InputStream;

/**
 * ClassName: HttpClient <br>
 * Description: <p>  </p>  <br>
 *
 * @author zhangbin
 * @create 2022/2/11 16:19
 * @since JDK1.8
 */
public class HttpClient {
    /**
     * 网络请求
     *
     * @param url   请求地址
     * @param body  请求参数
     * @param <REQ> 请求参数类型泛型
     * @return 请求服务器返回的数据
     */
    public static <REQ> HttpResponse execute(String url, REQ body, RequestInvoke invoke, HttpMethod method, boolean isHttps, InputStream certificate) {
        // 构建请求参数
        HttpRequest req = getRequest(url, body, method, isHttps, certificate);
        try {
            // 发起请求
            return invoke.execute(req);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 网络请求
     *
     * @param url   请求地址
     * @param body  请求参数
     * @param <REQ> 请求参数类型泛型
     * @return 请求服务器返回的数据
     */
    public static <REQ> Result<?> executeResult(String url, REQ body, RequestInvoke invoke, HttpMethod method, boolean isHttps, InputStream certificate) {
        HttpResponse response = execute(url, body, invoke, method, isHttps, certificate);
        if (response != null) {
            return JSON.parseObject((String) response.getData(), Result.class);
        } else {
            return null;
        }
    }

    /**
     * 网络请求POST的同步实现
     * {@link HttpClient#executeResult(String, Object, RequestInvoke, HttpMethod, boolean, InputStream)}
     */
    public static <REQ> Result<?> executePostSync(String url, REQ body, boolean isHttps, InputStream certificate) {
        return executeResult(url, body, HttpUtil::sendSync, HttpMethod.POST, isHttps, certificate);
    }

    /**
     * 网络请求POST的异步实现
     * {@link HttpClient#executeResult(String, Object, RequestInvoke, HttpMethod, boolean, InputStream)}
     */
    public static <REQ> Result<?> executePostAsync(String url, REQ body, boolean isHttps, InputStream certificate) {
        return executeResult(url, body, HttpUtil::sendAsync, HttpMethod.POST, isHttps, certificate);
    }

    /**
     * 网络请求GET的同步实现
     * {@link HttpClient#executeResult(String, Object, RequestInvoke, HttpMethod, boolean, InputStream)}
     */
    public static Result<?> executeGetSync(String url, boolean isHttps, InputStream certificate) {
        return executeResult(url, "", HttpUtil::sendSync, HttpMethod.GET, isHttps, certificate);
    }

    /**
     * 网络请求GET的异步实现
     * {@link HttpClient#executeResult(String, Object, RequestInvoke, HttpMethod, boolean, InputStream)}
     */
    public static Result<?> executeGetAsync(String url, boolean isHttps, InputStream certificate) {
        return executeResult(url, "", HttpUtil::sendAsync, HttpMethod.GET, isHttps, certificate);
    }

    /**
     * 网络请求PUT的同步实现
     * {@link HttpClient#executeResult(String, Object, RequestInvoke, HttpMethod, boolean, InputStream)}
     */
    public static <REQ> Result<?> executePutSync(String url, REQ body, boolean isHttps, InputStream certificate) {
        return executeResult(url, body, HttpUtil::sendSync, HttpMethod.PUT, isHttps, certificate);
    }

    /**
     * 网络请求PUT的异步实现
     * {@link HttpClient#executeResult(String, Object, RequestInvoke, HttpMethod, boolean, InputStream)}
     */
    public static <REQ> Result<?> executePutAsync(String url, REQ body, boolean isHttps, InputStream certificate) {
        return executeResult(url, body, HttpUtil::sendAsync, HttpMethod.PUT, isHttps, certificate);
    }

    /**
     * 网络请求DELETE的同步实现
     * {@link HttpClient#executeResult(String, Object, RequestInvoke, HttpMethod, boolean, InputStream)}
     */
    public static <REQ> Result<?> executeDeleteSync(String url, REQ body, boolean isHttps, InputStream certificate) {
        return executeResult(url, body, HttpUtil::sendSync, HttpMethod.DELETE, isHttps, certificate);
    }

    /**
     * 网络请求DELETE的异步实现
     * {@link HttpClient#executeResult(String, Object, RequestInvoke, HttpMethod, boolean, InputStream)}
     */
    public static <REQ> Result<?> executeDeleteAsync(String url, REQ body, boolean isHttps, InputStream certificate) {
        return executeResult(url, body, HttpUtil::sendAsync, HttpMethod.DELETE, isHttps, certificate);
    }

    /**
     * 同步下载
     *
     * @param url 下载地址
     * @return 下载数据
     */
    public static Result<?> downloadSync(String url, boolean isHttps, InputStream certificate) throws IOException {
        HttpRequest request = getRequest(url, "", HttpMethod.GET, isHttps, certificate);
        HttpResponse response = HttpUtil.downloadSync(request);
        return Result.ok(response.getData());
    }

    /**
     * 异步下载
     *
     * @param url 下载地址
     * @return 下载数据
     */
    public static Result<?> downloadAsync(String url, boolean isHttps, InputStream certificate) throws IOException {
        HttpRequest request = getRequest(url, "", HttpMethod.GET, isHttps, certificate);
        HttpResponse response = HttpUtil.downloadAsync(request);
        return Result.ok(response.getData());
    }


    /**
     * 获取请求参数
     *
     * @param url    请求地址
     * @param body   请求体
     * @param method 请求方式
     * @param <REQ>  请求体类型泛型
     * @return 请求参数
     */
    private static <REQ> HttpRequest getRequest(String url, REQ body, HttpMethod method, boolean isHttps, InputStream certificate) {
        HttpRequest.Builder builder = new HttpRequest.Builder();
        builder = builder.isHttps(isHttps).certificate(certificate);
        switch (method) {
            case GET:
                return builder
                        .get()
                        .url(url)
                        .build();
            case POST:
                return builder
                        .url(url)
                        .post(JSON.toJSONString(body))
                        .build();
            case PUT:
                return builder
                        .url(url)
                        .put(JSON.toJSONString(body))
                        .build();
            case DELETE:
                String requestBody = JSON.toJSONString(body);
                requestBody = StringUtils.isEmpty(requestBody) ? "" : requestBody;
                return builder
                        .url(url)
                        .delete(requestBody)
                        .build();
            default:
                return null;
        }
    }


}

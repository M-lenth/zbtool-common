package com.zhangbin.tool.net;

import com.alibaba.fastjson.JSON;
import com.zhangbin.tool.common.util.StringUtils;
import com.zhangbin.tool.net.enumeration.HttpMethod;
import com.zhangbin.tool.net.function.RequestInvoke;
import com.zhangbin.tool.web.Result;

import java.io.IOException;

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
    public static <REQ> Result<?> execute(String url, REQ body, RequestInvoke invoke, HttpMethod method) {
        // 构建请求参数
        HttpRequest req = getRequest(url, body, method);
        try {
            // 发起请求
            HttpResponse response = invoke.execute(req);
            return JSON.parseObject((String) response.getData(), Result.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 网络请求POST的同步实现
     * {@link HttpClient#execute(String, Object, RequestInvoke, HttpMethod)}
     */
    public static <REQ> Result<?> executePostSync(String url, REQ body) {
        return execute(url, body, HttpUtil::sendSync, HttpMethod.POST);
    }

    /**
     * 网络请求POST的异步实现
     * {@link HttpClient#execute(String, Object, RequestInvoke, HttpMethod)}
     */
    public static <REQ> Result<?> executePostAsync(String url, REQ body) {
        return execute(url, body, HttpUtil::sendAsync, HttpMethod.POST);
    }

    /**
     * 网络请求GET的同步实现
     * {@link HttpClient#execute(String, Object, RequestInvoke, HttpMethod)}
     */
    public static Result<?> executeGetSync(String url) {
        return execute(url, "", HttpUtil::sendSync, HttpMethod.GET);
    }

    /**
     * 网络请求GET的异步实现
     * {@link HttpClient#execute(String, Object, RequestInvoke, HttpMethod)}
     */
    public static Result<?> executeGetAsync(String url) {
        return execute(url, "", HttpUtil::sendAsync, HttpMethod.GET);
    }

    /**
     * 网络请求PUT的同步实现
     * {@link HttpClient#execute(String, Object, RequestInvoke, HttpMethod)}
     */
    public static <REQ> Result<?> executePutSync(String url, REQ body) {
        return execute(url, body, HttpUtil::sendSync, HttpMethod.PUT);
    }

    /**
     * 网络请求PUT的异步实现
     * {@link HttpClient#execute(String, Object, RequestInvoke, HttpMethod)}
     */
    public static <REQ> Result<?> executePutAsync(String url, REQ body) {
        return execute(url, body, HttpUtil::sendAsync, HttpMethod.PUT);
    }

    /**
     * 网络请求DELETE的同步实现
     * {@link HttpClient#execute(String, Object, RequestInvoke, HttpMethod)}
     */
    public static <REQ> Result<?> executeDeleteSync(String url, REQ body) {
        return execute(url, body, HttpUtil::sendSync, HttpMethod.DELETE);
    }

    /**
     * 网络请求DELETE的异步实现
     * {@link HttpClient#execute(String, Object, RequestInvoke, HttpMethod)}
     */
    public static <REQ> Result<?> executeDeleteAsync(String url, REQ body) {
        return execute(url, body, HttpUtil::sendAsync, HttpMethod.DELETE);
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
    private static <REQ> HttpRequest getRequest(String url, REQ body, HttpMethod method) {
        switch (method) {
            case GET:
                return new HttpRequest.Builder()
                    .get()
                    .url(url)
                    .build();
            case POST:
                return new HttpRequest.Builder()
                    .url(url)
                    .post(JSON.toJSONString(body))
                    .build();
            case PUT:
                return new HttpRequest.Builder()
                    .url(url)
                    .put(JSON.toJSONString(body))
                    .build();
            case DELETE:
                String requestBody = JSON.toJSONString(body);
                requestBody = StringUtils.isEmpty(requestBody) ? "" : requestBody;
                return new HttpRequest.Builder()
                    .url(url)
                    .delete(requestBody)
                    .build();
            default:
                return null;
        }
    }


}

package com.zhangbin.tool.net;

import com.zhangbin.tool.common.util.StringUtils;
import com.zhangbin.tool.net.enumeration.HttpMethod;
import okhttp3.*;

import java.io.IOException;

/**
 * Classname: HttpUtil <br>
 * Description: <p> 使用okhttp框架，调用方法后需要更新主线程 </p>  <br>
 *
 * @author zhangbin
 * @create 2022/1/19 13:46
 * @since JDK1.8
 */
public class HttpUtil {
    /**
     * 发送请求
     *
     * @param request 请求信息
     * @return 响应信息
     */
    public static HttpResponse send(HttpRequest request) {
        checkRequest(request);
        // 响应信息
        final HttpResponse resp = new HttpResponse();
        // 构建OKhttp对象
        OkHttpClient client = new OkHttpClient();
        // 获取访问请求对象
        Request req = getRequest(request);
        // 访问网络
        client.newCall(req).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                resp.setError(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 返回JSON数据
                resp.setSuccess(response.body().string(), "返回成功");
            }
        });
        return resp;
    }

    /**
     * 发送请求
     *
     * @param request 请求信息
     * @return 响应信息
     */
    public static HttpResponse sendSync(HttpRequest request) throws IOException {
        checkRequest(request);
        // 响应信息
        final HttpResponse resp = new HttpResponse();
        // 构建OKhttp对象
        OkHttpClient client = new OkHttpClient();
        // 获取访问请求对象
        Request req = getRequest(request);
        // 访问网络
        Response response = client.newCall(req).execute();
        // 返回JSON数据
        resp.setSuccess(response.body().string(), "返回成功");
        return resp;
    }

    /**
     * 下载内容，一般使用get方式
     *
     * @param request 请求信息
     * @return 响应信息 data为byte[]
     */
    public static HttpResponse download(HttpRequest request) {
        checkRequest(request);
        final HttpResponse resp = new HttpResponse();
        OkHttpClient client = new OkHttpClient();
        Request req = new Request.Builder()
            .url(request.getUrl())
            .get()
            .build();
        client.newCall(req).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                resp.setError(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 返回字节数组数据
                resp.setSuccess(response.body().bytes(), "返回成功");
            }
        });
        return resp;
    }

    /**
     * 文件上传
     *
     * @param request 上传信息
     * @return 响应信息
     */
    public static HttpResponse upload(HttpRequest request) {
        checkRequest(request);
        // 访问文件类型
        MediaType mediaTypeJson = MediaType.parse("application/json; charset=utf-8");
        // 访问请求信息
        RequestBody body = RequestBody.create(mediaTypeJson, request.getBody());
        // 响应信息
        final HttpResponse resp = new HttpResponse();
        OkHttpClient client = new OkHttpClient();
        Request req = new Request.Builder()
            .url(request.getUrl())
            .post(body)
            .build();
        client.newCall(req).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                resp.setError(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 返回字节数组数据
                resp.setSuccess(response.body().bytes(), "返回成功");
            }
        });
        return resp;
    }

    /**
     * 根据请求内容与请求方式获取请求对象
     *
     * @param request 请求内容
     * @return 请求对象
     */
    private static Request getRequest(HttpRequest request) {
        Request.Builder builder = new Request.Builder();
        MediaType mediaTypeJson = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(mediaTypeJson, request.getBody());
        switch (request.getMethod()) {
            case GET:
                return builder.get().url(request.getUrl()).build();
            case PUT:
                return builder.put(body).url(request.getUrl()).build();
            case POST:
                return builder.post(body).url(request.getUrl()).build();
            case DELETE:
                return builder.delete(body).url(request.getUrl()).build();
            default:
                return null;
        }
    }

    private static void checkRequest(HttpRequest request) {
        if (StringUtils.isEmpty(request.getUrl())) {
            throw new RuntimeException("The URL is empty!Add URL please!");
        }
        if (request.getMethod() != HttpMethod.GET && request.getMethod() != HttpMethod.DELETE) {
            if (StringUtils.isEmpty(request.getBody())) {
                throw new RuntimeException("The request body is empty. Please add the request body or modify the request method to 'GET' or 'DELETE'");
            }
        }
    }

}

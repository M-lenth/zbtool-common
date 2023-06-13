package pers.zhangbin.tool.net;

import pers.zhangbin.tool.common.util.StringUtils;
import pers.zhangbin.tool.io.Resource;
import pers.zhangbin.tool.net.enumeration.HttpMethod;
import okhttp3.*;
import pers.zhangbin.tool.net.util.SSLSocketClient;

import java.io.IOException;
import java.util.Objects;

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
    protected static HttpResponse sendAsync(HttpRequest request) throws IOException {
        checkRequest(request);
        // 响应信息
        final HttpResponse resp = new HttpResponse();
        // 构建OKhttp对象
        OkHttpClient client = getClient(request);
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
    protected static HttpResponse sendSync(HttpRequest request) throws IOException {
        checkRequest(request);
        // 响应信息
        final HttpResponse resp = new HttpResponse();
        // 构建OKhttp对象
        OkHttpClient client = getClient(request);
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
    protected static HttpResponse downloadAsync(HttpRequest request) throws IOException {
        checkRequest(request);
        final HttpResponse resp = new HttpResponse();
        OkHttpClient client = getClient(request);
        Request req = new Request.Builder().url(request.getUrl()).get().build();
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
     * 下载内容，一般使用get方式
     *
     * @param request 请求信息
     * @return 响应信息 data为byte[]
     */
    protected static HttpResponse downloadSync(HttpRequest request) throws IOException {
        checkRequest(request);
        final HttpResponse resp = new HttpResponse();
        OkHttpClient client = getClient(request);
        Request req = new Request.Builder().url(request.getUrl()).get().build();
        Response response = client.newCall(req).execute();
        resp.setData(response.body().bytes());
        return resp;
    }

    /**
     * 文件上传
     *
     * @param request 上传信息
     * @return 响应信息
     */
    protected static HttpResponse upload(HttpRequest request) throws IOException {
        checkRequest(request);
        // 访问文件类型
        MediaType mediaTypeJson = MediaType.parse("application/json; charset=utf-8");
        // 访问请求信息
        RequestBody body = RequestBody.create(mediaTypeJson, request.getBody());
        // 响应信息
        final HttpResponse resp = new HttpResponse();
        OkHttpClient client = getClient(request);
        Request req = new Request.Builder().url(request.getUrl()).post(body).build();
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

    /**
     * 获取RestFul风格的地址
     *
     * @param ip   IP地址或者域名
     * @param port 端口号
     * @param url  访问路径 需要包含路径参数
     * @return 真实访问路径
     */
    public static String getRestFulUrl(String ip, int port, String url) {
        HttpUrl.Builder builder = new HttpUrl.Builder().scheme("http").host(ip).port(port);
        String[] params = getRestFulParam(url);
        for (String param : params) {
            builder.addPathSegment(param);
        }
        return builder.build().toString();
    }

    /**
     * 获取访问路径的数组
     *
     * @param url 系统访问路径
     * @return 访问路径数组
     */
    private static String[] getRestFulParam(String url) {
        boolean flag = url.charAt(0) == '/';
        if (flag) {
            url = url.substring(1);
        }
        return url.split("/");
    }

    public static OkHttpClient getClient(HttpRequest request) throws IOException {
        OkHttpClient client = new OkHttpClient();
        if (request.isHttps()) {
            client.newBuilder()
                    .sslSocketFactory(Objects.requireNonNull(SSLSocketClient.getSSlSocketFactory(request.getCertificate())))
                    .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                    .build();
        }
        return client;
    }

}

package com.zhangbin.net;

import com.zhangbin.net.enumeration.HttpMethod;

/**
 * Classname: HttpRequestEntity <br>
 * Description: <p> Http请求实体 </p>  <br>
 *
 * @author zhangbin
 * @create 2022/1/19 13:45
 * @since JDK1.8
 */
public class HttpRequest {

    /**
     * 请求地址
     */
    private String url;
    /**
     * 请求方式
     */
    private HttpMethod method;
    /**
     * 请求内容, JSON格式
     */
    private String body;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public static class Buider {

    }
}

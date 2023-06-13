package pers.zhangbin.tool.net;

import pers.zhangbin.tool.net.enumeration.HttpMethod;

import java.io.InputStream;

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
    private String body = "";

    private HttpRequest() {
    }

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

    public static class Builder {
        private final HttpRequest request = new HttpRequest();

        public Builder() {
            request.method = HttpMethod.GET;
        }

        public Builder url(String url) {
            this.request.url = url;
            return this;
        }

        public Builder get() {
            this.request.method = HttpMethod.GET;
            return this;
        }

        public Builder post(String body) {
            this.request.body = body;
            this.request.method = HttpMethod.POST;
            return this;
        }

        public Builder put(String body) {
            this.request.body = body;
            this.request.method = HttpMethod.PUT;
            return this;
        }

        public Builder delete(String body) {
            this.request.body = body;
            this.request.method = HttpMethod.DELETE;
            return this;
        }

        public Builder delete() {
            this.request.method = HttpMethod.DELETE;
            return this;
        }


        public HttpRequest build() {
            return this.request;
        }
    }
}

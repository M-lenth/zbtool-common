package pers.zhangbin.tool.net.enumeration;

/**
 * Classname: HttpMethodEnum <br>
 * Description: <p> Http请求的基本四种方式 </p>  <br>
 *
 * @author zhangbin
 * @create 2022/1/19 13:48
 * @since JDK1.8
 */
public enum HttpMethod {
    /**
     * Get
     */
    GET("GET"),
    /**
     * Post
     */
    POST("POST"),
    /**
     * Put
     */
    PUT("PUT"),
    /**
     * Delete
     */
    DELETE("DELETE");

    private final String name;

    HttpMethod(String value) {
        this.name = value;
    }

    public String getName() {
        return name;
    }

}

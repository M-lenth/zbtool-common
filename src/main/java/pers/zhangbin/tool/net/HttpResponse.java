package pers.zhangbin.tool.net;

/**
 * Classname: HttpResponse <br>
 * Description: <p>  </p>  <br>
 *
 * @author zhangbin
 * @create 2022/1/19 13:46
 * @since JDK1.8
 */
public class HttpResponse {
    /**
     * 响应码
     */
    private Integer code;
    /**
     * 响应数据
     */
    private Object data;
    /**
     * 成功信息
     */
    private String successMessage;
    /**
     * 失败信息
     */
    private String errorMessage;

    public HttpResponse() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * 设置失败后的返回信息
     *
     * @param errorMessage 失败原因
     */
    public void setError(String errorMessage) {
        this.errorMessage = errorMessage;
        this.code = ERROR;
    }

    /**
     * 设置成功的返回信息
     *
     * @param successMessage 成功返回信息
     */
    public void setSuccess(String successMessage) {
        this.successMessage = successMessage;
        this.code = SUCCESS;
    }

    /**
     * 设置成功的返回信息
     *
     * @param data           成功数据
     * @param successMessage 成功返回信息
     */
    protected void setSuccess(Object data, String successMessage) {
        this.data = data;
        this.successMessage = successMessage;
        this.code = SUCCESS;
    }

    public static final Integer SUCCESS = 101;
    public static final Integer ERROR = 201;

    /**
     * 成功方法
     *
     * @param data 成功需要返回的数据
     * @return 响应信息
     */
    public static HttpResponse success(Object data) {
        HttpResponse response = new HttpResponse();
        response.data = data;
        response.code = SUCCESS;
        response.successMessage = "处理成功";
        response.errorMessage = "";
        return response;
    }

    /**
     * 成功方法，不带返回数据
     *
     * @return 响应信息
     */
    public static HttpResponse success() {
        HttpResponse response = new HttpResponse();
        response.code = SUCCESS;
        response.successMessage = "处理成功";
        return response;
    }

    /**
     * 失败返回的信息
     *
     * @param errorMessage 失败原因
     * @return 响应信息
     */
    public static HttpResponse error(String errorMessage) {
        HttpResponse response = new HttpResponse();
        response.code = ERROR;
        response.errorMessage = errorMessage;
        return response;
    }

}

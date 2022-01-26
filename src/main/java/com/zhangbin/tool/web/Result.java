package com.zhangbin.tool.web;

import static com.zhangbin.tool.common.constant.ResultConstant.*;

/**
 * Classname: Result <br>
 * Description: <p> 控制器返回值 </p>  <br>
 *
 * @author zhangbin
 * @create 2021/12/17 11:20
 * @since JDK1.8
 */
public class Result<T> {

    private String message;
    private Integer code;
    private T data;

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 成功返回值
     *
     * @param data 返回数据
     * @param <T>  返回类型
     * @return {@link Result}
     */
    public static <T> Result<T> ok(T data) {
        return new Result<>(SUCCESS, SUCCESS_CN, data);
    }

    /**
     * 失败返回信息
     *
     * @param message 失败信息
     * @return 返回信息 {@link Result}
     */
    public static <T> Result<T> error(String message) {
        return new Result<>(ERROR, message, null);
    }


}

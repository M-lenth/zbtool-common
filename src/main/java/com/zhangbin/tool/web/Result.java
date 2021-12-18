package com.zhangbin.tool.web;

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

    public static <T> Result<T> ok() {
        return null;
    }


}

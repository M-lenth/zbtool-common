package com.zhangbin.tool.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

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

    /**
     * 返回的消息
     */
    private String message;
    /**
     * 返回码
     */
    private Integer code;
    /**
     * 返回的数据
     */
    private T data;
    /**
     * 返回状态是否成功
     */
    private boolean success;

    public Result(Integer code, String message, T data, boolean success) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.success = success;
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * 因为使用网络传输过程中会出现对象类型变化的情况，使用此方法转换为对应的类型
     *
     * @param clazz 转换的目标类型
     * @return 目标类型对象
     */
    public <R> R parse(Class<R> clazz) {
        if (this.data instanceof JSONObject) {
            return ((JSONObject) this.getData()).toJavaObject(clazz);
        }
        String name = this.getData().getClass().getName();
        throw new RuntimeException("数据类型为" + name + "，请使用parseList方法");
    }

    /**
     * 因为使用网络传输过程中会出现对象类型变化的情况，使用此方法转换为对应的类型
     *
     * @param clazz 转换的目标类型
     * @return 目标类型对象
     */
    public <R> List<R> parseList(Class<R> clazz) {
        if (this.data instanceof JSONArray) {
            return ((JSONArray) this.getData()).toJavaList(clazz);
        }
        String name = this.getData().getClass().getName();
        throw new RuntimeException("数据类型为" + name + "，请使用parse方法");
    }

    /**
     * 成功返回值
     *
     * @param data 返回数据
     * @param <T>  返回类型
     * @return {@link Result}
     */
    public static <T> Result<T> ok(T data) {
        return new Result<>(SUCCESS, SUCCESS_CN, data, true);
    }


    /**
     * 成功返回值
     *
     * @param code    返回代码
     * @param message 返回消息
     * @param <T>     返回类型
     * @return {@link Result}
     */
    public static <T> Result<T> ok(Integer code, String message) {
        return new Result<>(code, message, (T) null, true);
    }

    /**
     * 成功返回值
     *
     * @param code    返回代码
     * @param message 返回消息
     * @param data    保存的数据
     * @param <T>     返回类型
     * @return {@link Result}
     */
    public static <T> Result<T> ok(Integer code, String message, T data) {
        return new Result<>(code, message, data, true);
    }

    /**
     * 失败返回信息
     *
     * @param message 失败信息
     * @return 返回信息 {@link Result}
     */
    public static <T> Result<T> error(String message) {
        return new Result<>(ERROR, message, null, false);
    }


    /**
     * 失败返回信息
     *
     * @param code    失败错误码
     * @param message 失败信息
     * @return 返回信息 {@link Result}
     */
    public static <T> Result<T> error(Integer code, String message) {
        return new Result<>(code, message, null, false);
    }


}

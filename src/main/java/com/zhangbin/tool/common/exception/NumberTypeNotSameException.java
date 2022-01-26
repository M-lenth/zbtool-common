package com.zhangbin.tool.common.exception;

/**
 * Classname: NumberTypeNotSameException <br>
 * Description: <p>  </p>  <br>
 *
 * @author zhangbin
 * @create 2021/12/30 17:18
 * @since JDK1.8
 */
public class NumberTypeNotSameException extends RuntimeException {

    public NumberTypeNotSameException() {
        super("数字类型不一致");
    }
}

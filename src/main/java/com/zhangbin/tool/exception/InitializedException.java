package com.zhangbin.tool.exception;

/**
 * Classname: ConnectionExistException <br>
 * Description: <p>  </p>  <br>
 *
 * @author zhangbin
 * @create 2021/12/18 10:11
 * @since JDK1.8
 */
public class InitializedException extends RuntimeException {
    public InitializedException(String message) {
        super(message);
    }

    public InitializedException() {
        super("已初始化过");
    }

}

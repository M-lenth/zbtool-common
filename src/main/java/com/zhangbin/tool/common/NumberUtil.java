package com.zhangbin.tool.common;

import com.zhangbin.tool.exception.NumberTypeNotSameException;

/**
 * Classname: NumberUtil <br>
 * Description: <p>  </p>  <br>
 *
 * @author zhangbin
 * @create 2021/12/30 16:58
 * @since JDK1.8
 */
public class NumberUtil {

    /**
     * 比较两个数的大小
     *
     * @param num1 比较的数1
     * @param num2 比较的数2
     * @return 比较的结果
     * <p> 0 num1 == num2</p>
     * <p> 1 num1 >  num2</p>
     * <p>-1 num1 <  num2</p>
     */
    public static int compare(Number num1, Number num2) {
        if (!isSameType(num1, num2)) {
            throw new NumberTypeNotSameException();
        }

        return 0;
    }

    /**
     * 判断两个数字是否同一个类型
     *
     * @return <p>true 同类型 false 不同类型</p>
     */
    public static boolean isSameType(Number num1, Number num2) {
        return getType(num1) == getType(num2);
    }

    /**
     * 获取类对象
     *
     * @param num 数字
     * @return 数字的类型，如 Integer Long Short Byte Double Float AtomicInteger AtomicLong 等
     */
    public static Class<?> getType(Number num) {
        return num.getClass();
    }

}

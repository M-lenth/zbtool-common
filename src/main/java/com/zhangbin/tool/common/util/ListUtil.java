package com.zhangbin.tool.common.util;

import java.util.*;

/**
 * Classname: ListUtil <br>
 * Description: <p> 对List的一些常用操作 </p>  <br>
 *
 * @author zhangbin
 * @create 2021/12/30 16:34
 * @since JDK1.8
 */
public class ListUtil {

    /**
     * 将List转换为Set类型
     *
     * @param list 需要转换的列表
     * @return 转换后的Set集合
     */
    public static Set<?> toSet(List<?> list) {
        return new HashSet<>(list);
    }

    /**
     * 判断列表是否为空
     *
     * @param list 列表
     * @return <p>true 为空 false 不为空</p>
     */
    public static boolean isNull(List<?> list) {
        return null == list || list.size() == 0;
    }

    /**
     * 判断列表是否不为空
     *
     * @param list 列表
     * @return <p>true 不为空 false 为空</p>
     */
    public static boolean isNotNull(List<?> list) {
        return !isNull(list);
    }

    /**
     * 获取最大值 中值 最小值
     *
     * @param list 获取的值的列表  其中的值需要是Number的子类
     * @return CurveMap 存储三个键值对 包含：
     * <pre>maxValue - 最大值</pre>
     * <pre>minValue - 最小值</pre>
     * <pre>midValue - 中值</pre>
     */
    public static Map<String, ? extends Number> getCurveMap(List<? extends Number> list) {
        Map<String, ? extends Number> curveMap = new HashMap<>(3);

        return null;
    }

}

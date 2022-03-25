package com.zhangbin.tool.common.util;

import java.util.*;

/**
 * ClassName: ListUtil <br>
 * Description: <p>  </p>  <br>
 *
 * @author zhangbin
 * @create 2022/3/18 10:32
 * @since JDK1.8
 */
public class CollectUtils {
    /**
     * 可变参数创建ArrayList
     *
     * @param ts List的值
     * @return List
     */
    @SafeVarargs
    public static <T> List<T> newLists(T... ts) {
        List<T> list = new ArrayList<>(ts.length);
        list.addAll(Arrays.asList(ts));
        return list;
    }

    /**
     * 可变参数创建HashSet
     *
     * @param ts Set的值
     * @return Set
     */
    @SafeVarargs
    public static <T> Set<T> newSets(T... ts) {
        Set<T> set = new HashSet<>();
        Collections.addAll(set, ts);
        return set;
    }

}

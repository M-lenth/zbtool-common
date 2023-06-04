package com.zhangbin.tool.common.util;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * ClassName: Json2Bean <br>
 * Time 2023/6/3 22:13
 * Description: <p> 通过文件转化JSON为实体类</p>  <br>
 *
 * @author zhangbin
 * @since JDK1.8
 */
public class Json2Bean {

    /**
     * 读取文件数据转化JSON为实体类
     *
     * @param filepath 文件路径
     * @param t        转化JSON数据类型类对象
     * @param <T>      转化数据所对应的数据泛型
     * @return JSON转换为的对象
     */
    public static <T> T getBean(String filepath, Class<T> t) throws IOException {
        return JSON.parseObject(getJson(filepath), t);
    }

    /**
     * 读取文件将JSON转化为对象List
     *
     * @param filepath 文件路径
     * @param t        转化JSON数据类型类对象
     * @param <T>      转化数据所对应的数据泛型
     * @return JSON所转化的对象List
     */
    public static <T> List<T> getList(String filepath, Class<T> t) throws IOException {
        return JSON.parseArray(getJson(filepath), t);
    }

    /**
     * 读取文件中的JSON数据
     *
     * @param filepath 文件路径
     * @return JSON数据
     */
    private static String getJson(String filepath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String buf;
        StringBuilder result = new StringBuilder();
        do {
            buf = reader.readLine();
            if (buf != null) {
                result.append(buf);
            }
        } while (null != buf);
        return result.toString();
    }
}

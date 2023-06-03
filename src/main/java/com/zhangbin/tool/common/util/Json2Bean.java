package com.zhangbin.tool.common.util;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * ClassName: Json2Bean <br>
 * Time 2023/6/3 22:13
 * Description: <p>  </p>  <br>
 *
 * @author zhangbin
 * @since JDK1.8
 */
public class Json2Bean {

    public static <T> T getBean(String filepath, Class<T> t) throws IOException {
        return JSON.parseObject(getJson(filepath), t);
    }

    public static <T> List<T> getList(String filepath, Class<T> t) throws IOException {
        return JSON.parseArray(getJson(filepath), t);
    }

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

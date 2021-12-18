package com.zhangbin.tool.common;

import com.alibaba.fastjson.JSON;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Classname: JsonUtil <br>
 * Description: <p>  </p>  <br>
 *
 * @author zhangbin
 * @create 2021/12/18 10:56
 * @since JDK1.8
 */
public class JsonUtil {

    /**
     * 根据JSON键生成类内容字符串
     *
     * @param json      json字符串
     * @param className 类文件名
     * @return 类内容字符串
     */
    public static String generateObject(String json, String className) {
        HashMap map = JSON.parseObject(json, HashMap.class);
        Set<String> columns = map.keySet();
        StringBuilder builder = new StringBuilder();
        builder.append("public class ").append(className).append("{\n\n");
        for (String column : columns) {
            builder.append("\tprivate String ").append(column).append(";\n\n");
        }
        builder.append("}");
        return builder.toString();
    }

    /**
     * 根据地址与json文件生成Java类
     *
     * @param json      JSON数据
     * @param className Java类名
     * @param filepath  文件路径，到文件所在文件夹路径
     */
    public static void generateObject(String json, String className, String filepath) throws IOException {
        if (StringUtils.isEmpty(className)) {
            className = "Demo";
        }
        File file = new File(filepath + File.separator + className);
        boolean flag = (file.exists() && file.isDirectory()) || !file.exists();
        if (flag) {
            // 文件不存在
            file.createNewFile();
        }
        FileWriter writer = new FileWriter(file);
        writer.write(generateObject(json, className));
        writer.close();
        writer.flush();
    }

    public static void main(String[] args) {
        String json = "{\n" +
            "  \"name\": \"json在线编辑器\",\n" +
            "  \"v1.0\": \"2014-07-02 22:05 工具上线\",\n" +
            "  \"v2.0\": \"2016-11-16 14:13 增加php, go类生成\",\n" +
            "  \"v2.1\": \"2016-11-19 01:17 增加java类生成\",\n" +
            "  \"v2.2\": \"2021-03-07 10:21 增加转换yaml功能\"\n" +
            "}";
        System.out.println(generateObject(json, ""));
    }

}

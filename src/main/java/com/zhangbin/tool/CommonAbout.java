package com.zhangbin.tool;

import com.zhangbin.tool.io.Resource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * ClassName: CommonAbout <br>
 * Time 2023/6/4 22:19
 * Description: <p> 介绍Common的内容 </p>  <br>
 *
 * @author zhangbin
 * @since JDK1.8
 */
public class CommonAbout {

    public static String getCommonVersion() throws IOException {
        Properties properties = new Properties();
        properties.load(new Resource("project.properties").getResourceAsReader());
        return "zbtool-common-version: " + properties.getProperty("version");
    }
}

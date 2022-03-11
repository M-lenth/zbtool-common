package com.zhangbin.tool.mybatis.bean;

import com.zhangbin.tool.mybatis.mapper.TableMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * ClassName: DataBaseConfig <br>
 * Description: <p>  </p>  <br>
 *
 * @author zhangbin
 * @create 2022/3/11 10:31
 * @since JDK1.8
 */
public class DataBaseConfigBean {

    private String url;
    private String driver;
    private String username;
    private String password;
    private final List<Class<?>> mappers = new ArrayList<>();

    public DataBaseConfigBean() {
        mappers.add(TableMapper.class);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Class<?>> getMappers() {
        return mappers;
    }

    public void addMappers(Class<?> mapper) {
        this.mappers.add(mapper);
    }

    public static DataBaseConfigBean getBeanByProperties(Properties properties) {
        DataBaseConfigBean bean = new DataBaseConfigBean();
        bean.driver = properties.getProperty("driver");
        bean.url = properties.getProperty("url");
        bean.username = properties.getProperty("username");
        bean.password = properties.getProperty("password");
        return bean;
    }

}

# zbtool-common

#### 介绍

zbtool-common，用于Java开发使用的一个工具类内容。其中包含以下模块

1. common
2. net

#### 安装教程

下载项目

添加依赖

```xml
<dependency>
    <groupId>pers.zhangbin</groupId>
    <artifactId>zbtool-common</artifactId>
</dependency>
```

#### 使用说明

##### COMMON

整个工具的公共使用模块，大部分工具类放在里面

- constant

  本工具包使用的常量类型

- util

  常用工具类

  DateUtil，对日期操作的方法，如:

  > 根据日期字符串与格式获取Date对象
>
> 使用默认格式"yyyy-MM-dd HH:mm:ss"，根据日期字符串获得Date对象
>
> 使用默认格式格式化Date对象为字符串
>
> 使用指定日期格式与Date对象获取日期字符串
>
> ```java
  > public static void main(String[] args) throws ParseException {
  >         System.out.println(DateUtil.format(new Date()));
  >         System.out.println(DateUtil.format(new Date(),"yyyyMMdd"));
  >         System.out.println(DateUtil.getDate("2022-01-26 14:18:54"));
  >         System.out.println(DateUtil.getDate("20220126","yyyyMMdd"));
  > }
  > ```
>
> 如下输出:
>
> 2022-01-26 14:19:50
> 20220126
> Wed Jan 26 14:18:54 CST 2022
> Wed Jan 26 00:00:00 CST 2022

StringUtils，操作字符串的工具类



##### NET

入口类为HttpUtils

执行访问之前需准备一个HttpRequest类

```java
/**
 * Classname: HttpRequest <br>
 * Description: <p> Http请求实体 </p>  <br>
 */
public class HttpRequest {
    /**
     * 请求地址
     */
    private String url;
    /**
     * 请求方式
     */
    private HttpMethod method;
    /**
     * 请求内容, JSON格式
     */
    private String body = "";

    public static class Builder {
       // 构造器构造请求对象
    }
}
```

执行时使用HttpUtils类的静态方法:

###### 访问HTTP协议的链接

> ```java
> // 生成HttpRequest对象
> HttpRequest req = HttpRequest.newBuilder().build();
> // 访问链接获取数据
> HttpResponse resp = HttpUtils.executeHttp(req);
> ```

###### 访问HTTPS协议的链接

> 1. 带有SSL证书访问
>
> ```java
> // 生成HttpRequest对象
> HttpRequest req = HttpRequest.newBuilder().build();
> // 携带SSL证书访问
> HttpResponse resp = HttpUtils.executeHttps(req, new Resource(ssl_path).getResourceAsInputStream());
> ```
>
> 2. 绕过SSL证书访问
>
> ```java
> // 生成HttpRequest对象
> HttpRequest req = HttpRequest.newBuilder().build();
> // 绕过SSL检测访问
> HttpUtils.executeHttps(req);
> ```
>
> 















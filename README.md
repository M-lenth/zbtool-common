# zbtool-common

#### 介绍

zbtool-common，用于Java开发使用的一个工具类内容。其中包含以下几个模块

1. common
2. net
3. web

#### 软件架构

工具类

#### 安装教程

添加远程仓库地址

```
http://120.24.205.42:8081/nexus/content/groups/public/
```

添加依赖

```xml
<dependency>
    <groupId>com.zhangbin</groupId>
    <artifactId>zbtool-common</artifactId>
    <version>1.0</version>
</dependency>
```

#### 使用说明

##### common

整个工具的公共使用模块，大部分工具类放在里面

- constant

  本工具包使用的常量类型

- exception

  用到的自定义异常

- filestorage

  用于进行文件上传

  操作的入口位于类StorageManager，首先需要对FastDFS服务器进行初始化，加载配置文件StorageManager.init(String url);

  之后可使用StorageManager类对外开放的接口进行上传下载操作

  ```java
  StorageManager.init("/src/config/fastdfs.conf");
  StorageManager.download(String groupName, String remoteFilename)
  ```

- security

  字符串的加密解密，根据对应的类，使用MD5加密或者使用Base64进行编码解码

- util

  常用工具类

  ListUtil，针对于List类型的常用工具类

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
  >     System.out.println(DateUtil.format(new Date()));
  >     System.out.println(DateUtil.format(new Date(),"yyyyMMdd"));
  >     System.out.println(DateUtil.getDate("2022-01-26 14:18:54"));
  >     System.out.println(DateUtil.getDate("20220126","yyyyMMdd"));
  > }
  > ```
  >
  > 如下输出:
  >
  > 2022-01-26 14:19:50
  > 20220126
  > Wed Jan 26 14:18:54 CST 2022
  > Wed Jan 26 00:00:00 CST 2022

  NumberUtil，用于对数字类型的比较，可对继承自Number的类操作，暂未编写完成

  

##### net

基于OKHttp框架封装的网络访问，主要便于Android访问

封装了一个私有方法: 供其他方法调用

  ```java
  public static <REQ> Result<?> execute(String url, REQ body, RequestInvoke invoke, HttpMethod method) {
      
  }
  ```

POST调用同步请求，使用如下:

  ```
  execute(url, body, HttpUtil::sendSync, HttpMethod.POST);
  ```

POST调用异步请求，使用如下:

  ```java
  execute(url, body, HttpUtil::sendAsync, HttpMethod.POST);
  ```

还封装了方法:

针对GET的:

```
executeGetSync(String url);
executeGetAsync(String url);
```

PUT:

```
executePutSync(String url, REQ body);
executePutAsync(String url, REQ body)
```

DELETE:

```
executeDeleteSync(String url, REQ body);
executeDeleteAsync(String url, REQ body)
```

##### web

面向JavaWeb开发，封装Web开发常用的一些类

- Result类，规定统一返回数据类型，从系统返回的数据类型使用此类封装，并自定义返回代码以及返回信息

提供方法:

parse将data从JSONObject转换为对象

parseList将data从JSONArray转换为List列表
















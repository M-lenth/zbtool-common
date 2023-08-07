# zbtool-common

#### 介绍

zbtool-common，用于Java开发使用的一个工具类内容。其中包含以下模块

1. common
2. net

工具类使用文档：

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

##### 使用文档

zbtool-common-doc.zip

解压缩后即可查看文档

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









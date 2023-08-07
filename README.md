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

  - DateUtils：对日期操作的工具类

  > | 返回值                    | 方法名称和参数                                               | 说明                                                         |
  > | :------------------------ | :----------------------------------------------------------- | ------------------------------------------------------------ |
  > | `static java.lang.String` | `format(java.util.Date date)`                                | 获取日期格式化后的字符串 使用默认格式 yyyy-MM-dd HH:mm:ss    |
  > | `static java.lang.String` | `format(java.util.Date date, java.lang.String pattern)`      | 获取日期格式化后的字符串                                     |
  > | `static java.util.Date`   | `getDate(java.lang.String date)`                             | 根据格式化的日期字符串获取日期对象 使用格式 yyyy-MM-dd HH:mm:ss |
  > | `static java.util.Date`   | `getDate(java.lang.String date, java.lang.String pattern)`   | 根据格式化的日期字符串获取日期对象                           |
  > | `static java.lang.String` | `getLastDay(java.util.Date date)`                            | 获取指定日期的上一天 使用yyyy-MM-dd                          |
  > | `static java.lang.String` | `getLastDay(java.lang.String date)`                          | 获取指定日期的上一天 使用yyyy-MM-dd                          |
  > | `static java.lang.String` | `getLastDay(java.lang.String date, java.lang.String pattern)` | 获取指定日期的上一天 使用yyyy-MM-dd                          |
  > | `static java.lang.String` | `getLastMonth(java.util.Date date)`                          | 获取指定日期的上一个月字符串显示 格式为yyyy-MM               |
  > | `static java.lang.String` | `getLastMonth(java.lang.String date)`                        | 获取指定日期的上一个月字符串显示 格式为yyyy-MM               |
  > | `static java.lang.String` | `getLastMonth(java.lang.String date, java.lang.String pattern)` | 获取指定日期的上一个月字符串显示 格式为yyyy-MM               |
  > | `static java.lang.String` | `getLastYear(java.util.Date date)`                           | 上一年年份                                                   |
  > | `static java.lang.String` | `getLastYear(java.lang.String date)`                         | 上一年年份                                                   |
  > | `static java.lang.String` | `getLastYear(java.lang.String date, java.lang.String pattern)` | 上一年年份                                                   |
  > | `static java.lang.String` | `getNextDay(java.util.Date date)`                            | 获取指定日期下一天                                           |
  > | `static java.lang.String` | `getNextDay(java.lang.String date)`                          | 获取指定日期下一天                                           |
  > | `static java.lang.String` | `getNextDay(java.lang.String date, java.lang.String pattern)` | 获取指定日期下一天                                           |
  > | `static java.lang.String` | `getNextMonth(java.util.Date date)`                          | 获取下一个月的月份                                           |
  > | `static java.lang.String` | `getNextMonth(java.lang.String date)`                        | 获取下一个月的月份                                           |
  > | `static java.lang.String` | `getNextMonth(java.lang.String date, java.lang.String pattern)` | 获取下一个月的月份                                           |
  > | `static java.lang.String` | `getNextYear(java.util.Date date)`                           | 获取下一年年份                                               |
  > | `static java.lang.String` | `getNextYear(java.lang.String date)`                         | 获取下一年年份 输入区日期格式默认为yyyy-MM-dd                |
  > | `static java.lang.String` | `getNextYear(java.lang.String date, java.lang.String pattern)` | 获取下一年年份 输入区日期格式默认为yyyy-MM-dd                |
  > | `static boolean`          | `isLargeMonth(int month)`                                    | 是否大月                                                     |
  > | `static boolean`          | `prime(int n)`                                               | 是否闰年                                                     |

  - StringUtils：操作字符串的工具类

  > |返回值						  |方法名称与参数								|说明								|
  > | --------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
  > | boolean            | `checkPassword( String pwd, int kind)` | 校验密码使用的字符种类 数字 字母 非数字字母字符 |
  > | boolean            | `equals( String s1,  String s2)` | 判断两个字符串是否相等 |
  > | String[] | `getBracketContent(String str, pers.zhangbin.tool.common.constant.BracketType type)` | 获取括号内的内容 |
  > | ` String`   | `getClassNameByTbName( String tbName)` | 将表名转换为类名 |
  > | `  String`   | `getFilename( String filename)` | 获取文件去后缀的文件名 |
  > | `  String`   | `getStringByInStream(java.io.InputStream is, java.nio.charset.Charset charset)` | 根据输入流获取字符串 |
  > | `  String`   | `getStringExtend(String str)`        | 获取文件后缀名 |
  > | `  String`   | `humpToUnderLine(String str)`        | 小驼峰转下划线 |
  > | ` boolean`            | `isEmail( String email)`            | 是否符合邮箱规则    |
  > | ` boolean`            | `isEmpty(String str)`                | 字符串是否为空         |
  > | ` boolean`            | `isEmptyAnd( String... str)`      | 字符串是否都不为空 |
  > | ` boolean`            | `isEmptyOr( String... str)`         | 字符串是否都为空 |
  > | ` boolean`            | `isNotEmpty(String str)`                           | 字符串不为空                     |
  > | ` boolean`            | `isNum( String num)`            | 字符串是否由数字组成  |
  > | ` boolean`            | `isPhone( String phone)` | 判断字符串是否符合电话号码规则 |
  > | `  String`   | `replaceBracketBrace(String str,  Object... strings)` | 替换字符串中的花括号为目标字符串`strings` |
  > | `  String`   | `replaceBracketRound(String str,  Object... strings)` | 替换字符串中的小括号为目标字符串`strings` |
  > | `  String`   | `replaceBracketSquare(String str,  Object... strings)` | 替换字符串中的中括号括号为目标字符串`strings` |
  > | `  String`   | `toUpperFirstChar(String str)` | 获取第一个字符串将第一个转换为大写字符 |
  > | `  String`   | `toUTF8String(String str)` | 下载保存时中文文件名的字符编码转换方法 |
  > | `  String`   | `underLineToHump(String str)`    | 下划线转小驼峰命名 |
  > | `  String`   | `underLineToMaxHump(String str)`     | 下划线转大驼峰 |
  
  - BeanUtils：处理对象的属性值工具类
  
  > | 限定符和类型                                              | 方法名称与参数                                               | 说明                                                         |
  > | :-------------------------------------------------------- | :----------------------------------------------------------- | ------------------------------------------------------------ |
  > | `static <T> T`                                            | `copyProperties(java.lang.Object obj, java.lang.Class<T> tClass)` | 复制对象中的属性值 复制的源对象与目标类型中的属性需要一致，当类型不一致时不赋值 |
  > | `static java.lang.Object`                                 | `invoke(java.lang.Object obj, java.lang.String methodName)`  | 参数列表为空的情况                                           |
  > | `static java.lang.Object`                                 | `invoke(java.lang.Object obj, java.lang.String methodName, java.lang.Class<?>[] argsType, java.lang.Object[] args)` | 调用私有方法                                                 |
  > | `static java.util.Map<java.lang.String,java.lang.Object>` | `objectToMap(java.lang.Object obj)`                          | 将对象转换为Map                                              |
  > | `static java.util.Map<java.lang.String,java.lang.Object>` | `propToMap(java.util.Properties poperties)`                  | 把配置文件转换为Map对象                                      |
  
  - ValidateUtils
  
  
  
  
  
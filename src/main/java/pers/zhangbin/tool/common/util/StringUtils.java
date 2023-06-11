package pers.zhangbin.tool.common.util;

import pers.zhangbin.tool.common.constant.BracketType;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

import static pers.zhangbin.tool.common.util.CharUtils.*;

/**
 * Classname: StringUtils <br>
 * Description: <p>  </p>  <br>
 *
 * @author zhangbin
 * @create 2021/12/18 11:22
 * @since JDK1.8
 */
public class StringUtils {
    /**
     * 字符串是否为空
     *
     * @param str 判断的目标字符串
     * @return <p>true 空 false 不为空</p>
     */
    public static boolean isEmpty(String str) {
        return null == str || "".equals(str);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 字符串是否都为空
     *
     * @param str 判断的字符串序列
     * @return <p>true 只要有一个不为空，则返回true; false 全部不为空返回false</p>
     */
    public static boolean isEmptyOr(String... str) {
        for (String s : str) {
            if (!isEmpty(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 字符串是否都不为空
     *
     * @param str 判断的字符串序列
     * @return <p>false 只要有一个为空，则返回false; true 全部不为空返回true</p>
     */
    public static boolean isEmptyAnd(String... str) {
        for (String s : str) {
            if (isEmpty(s)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 下载保存时中文文件名的字符编码转换方法
     */
    public static String toUTF8String(String str) {
        StringBuilder sb = new StringBuilder();
        int len = str.length();
        for (int i = 0; i < len; i++) {
            // 取出字符中的每个字符
            char c = str.charAt(i);
            // Unicode码值为0~255时，不做处理
            if (c <= 255) {
                sb.append(c);
            } else { // 转换 UTF-8 编码
                byte[] b;
                b = Character.toString(c).getBytes(StandardCharsets.UTF_8);
                // 转换为%HH的字符串形式
                for (int value : b) {
                    int k = value;
                    if (k < 0) {
                        k &= 255;
                    }
                    sb.append("%").append(Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    /**
     * 下划线转小驼峰命名
     *
     * @param str 输入的原串
     * @return 转换后的字符串
     */
    public static String underLineToHump(String str) {
        StringBuilder builder = new StringBuilder();
        boolean flag = false;
        for (char c : str.toCharArray()) {
            if (c == '_') {
                flag = true;
                continue;
            }
            if (flag) {
                String upperCase = (c + "").toUpperCase();
                builder.append(upperCase);
                flag = false;
                continue;
            }
            builder.append(c);
        }
        return builder.toString();
    }

    /**
     * 下划线转大驼峰
     *
     * @param str 输入的原串
     * @return 转换后的字符串
     */
    public static String underLineToMaxHump(String str) {
        return toUpperFirstChar(underLineToHump(str));
    }

    /**
     * 小驼峰转下划线
     *
     * @param str 原串
     * @return 下划线表示的字符串
     */
    public static String humpToUnderLine(String str) {
        StringBuilder builder = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (isUpper(c)) {
                builder.append("_").append((char) (c - 'A' + 'a'));
                continue;
            }
            builder.append(c);
        }
        return builder.toString();
    }

    /**
     * 获取第一个字符串将第一个转换为大写字符
     */
    public static String toUpperFirstChar(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 获取文件后缀名
     *
     * @param str 文件名称
     * @return 后缀
     */
    public static String getStringExtend(String str) {
        int i = str.lastIndexOf(".");
        if (i == -1) {
            return str;
        }
        return str.substring(i + 1);
    }

    /**
     * 将表名转换为类名
     *
     * @param tbName 表名
     * @return 类名
     */
    public static String getClassNameByTbName(String tbName) {
        if (tbName.startsWith("tb_")) {
            tbName = tbName.substring(3);
        }
        if (tbName.startsWith("t_")) {
            tbName = tbName.substring(2);
        }
        return underLineToMaxHump(tbName);
    }

    /**
     * 获取文件去后缀的文件名
     *
     * @param filename 文件名
     * @return 文件名称去后缀的字符串: a.txt => a;  StringUtils.java => StringUtils
     */
    public static String getFilename(String filename) {
        int i = filename.lastIndexOf(".");
        if (i == -1) {
            return filename;
        }
        return filename.substring(0, i);
    }

    /**
     * 是否符合邮箱规则
     *
     * @param email 输入字符串
     * @return true-是邮箱 false-不是邮箱
     */
    public static boolean isEmail(String email) {
        String regex = "^(\\w+((-\\w+)|(.\\w+)))+\\w+((-\\w+)|(.\\w+))@[A-Za-z0-9]+((.|-)[A-Za-z0-9]+)*.[A-Za-z0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }

    /**
     * 判断字符串是否符合电话号码规则
     *
     * @param phone 电话字符串
     * @return true-符合手机号规则 false-不符合
     */
    public static boolean isPhone(String phone) {
        String regex = "^((13[0-9])|(14[0,1,4-9])|(15[0-3,5-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\d{8}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(phone).matches();
    }

    /**
     * 字符串是否由数字组成
     *
     * @param num 字符串
     * @return true-全是数字 false-有字符
     */
    public static boolean isNum(String num) {
        for (int i = 0; i < num.length(); i++) {
            if (!CharUtils.isNum(num.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 校验密码使用的字符种类 数字 字母 非数字字母字符
     *
     * @param pwd  密码字符串
     * @param kind 种类
     * @return true-符合该种类规则 false-不符合该规则
     */
    public static boolean checkPassword(String pwd, int kind) {
        // 大写字母
        boolean charFlag = false;
        // 小写字母
        boolean specialFlag = false;
        // 特殊字符
        boolean numFlag = false;
        int count = 0;
        for (int i = 0; i < pwd.length(); i++) {
            if (count >= 3) {
                return true;
            }
            if (!charFlag) {
                if (isChar(pwd.charAt(i))) {
                    // 大写字母
                    charFlag = true;
                    count++;
                }
            }
            if (!specialFlag) {
                if (isChar(pwd.charAt(i))) {
                    specialFlag = true;
                    count++;
                }
            }
            if (!numFlag) {
                if (!CharUtils.isNum(pwd.charAt(i))) {
                    numFlag = true;
                    count++;
                }
            }
        }
        return count >= kind;
    }

    /**
     * 判断两个字符串是否相等
     *
     * @param s1 字符串1
     * @param s2 字符串2
     * @return true-相同 false-不同
     */
    public static boolean equals(String s1, String s2) {
        if (null == s1 || null == s2) {
            return false;
        }
        return s1.equals(s2);
    }

    /**
     * <p> 获取括号内的内容 </p>
     *
     * @param str  原字符串
     * @param type 字符串类型
     * @return <p> 字符串中括号里面的内容 </p>
     */
    public static String[] getBracketContent(String str, BracketType type) {
        if (isEmpty(str)) {
            return null;
        }
        Character bracketLeft = type.getLeft(), bracketRight = type.getRight();
        // 匹配括号的栈
        Stack<Character> st = new Stack<>();
        // 判断是否为括号内的内容
        boolean isContent = false;
        // 构造括号内的字符串内容
        StringBuilder resultBuilder = new StringBuilder();
        // 结果列表
        List<String> result = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == bracketLeft) {
                if (!st.isEmpty() && isContent) {
                    // 添加字符串内容  左括号未清空，认为这一个左括号为字符串内容
                    resultBuilder.append(str.charAt(i));
                    continue;
                }
                // 左括号，进栈，跳出循环
                st.push(bracketLeft);
                isContent = true;
                continue;
            }
            if (str.charAt(i) == bracketRight) {
                if (st.isEmpty()) {
                    return null;
                }
                // 右括号，出栈，跳出循环
                isContent = false;
                // 添加进结果列表
                result.add(resultBuilder.toString());
                // 重置builder
                resultBuilder.setLength(0);
                st.pop();
            }
            if (isContent) {
                // 添加字符串内容
                resultBuilder.append(str.charAt(i));
            }
        }
        return result.toArray(new String[0]);
    }

    /**
     * <p> 替换字符串中的括号 </p>
     *
     * @param str     原字符串
     * @param type    括号类型
     * @param strings 目标数组
     * @return 替换后的字符串
     */
    private static String replaceBracket(String str, BracketType type, String[] strings) {
        for (String string : strings) {
            str = str.replaceFirst("\\{}", string);
        }
        return str;
    }

    /**
     * <p> 替换字符串中的花括号为目标字符串<code>strings</code> </p>
     *
     * @param str     原字符串
     * @param strings 替换的目标数组
     * @return 替换花括号后的字符串
     */
    public static String replaceBracketBrace(String str, Object... strings) {
        return replaceBracket(str, BracketType.BRACE, getStringArr(strings));
    }


    /**
     * <p> 替换字符串中的中括号括号为目标字符串<code>strings</code> </p>
     *
     * @param str     原字符串
     * @param strings 替换的目标数组
     * @return 替换中括号后的字符串
     */
    public static String replaceBracketSquare(String str, Object... strings) {
        return replaceBracket(str, BracketType.SQUARE_BRACKETS, getStringArr(strings));
    }

    /**
     * <p> 替换字符串中的小括号为目标字符串<code>strings</code> </p>
     *
     * @param str     原字符串
     * @param strings 替换的目标数组
     * @return 替换魈括号后的字符串
     */
    public static String replaceBracketRound(String str, Object... strings) {
        return replaceBracket(str, BracketType.ROUND_BRACKETS, getStringArr(strings));
    }

    /**
     * <p> 将Object数组转换为String数组 </p>
     *
     * @param objects Object数组
     * @return String数组
     */
    private static String[] getStringArr(Object[] objects) {
        List<String> list = new ArrayList<>();
        for (Object o : objects) {
            list.add(String.valueOf(o));
        }
        return list.toArray(new String[0]);
    }
}

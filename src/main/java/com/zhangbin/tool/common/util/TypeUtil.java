package com.zhangbin.tool.common.util;

/**
 * ClassName: TypeUtil <br>
 * Description: <p>  </p>  <br>
 *
 * @author zhangbin
 * @create 2022/3/11 11:26
 * @since JDK1.8
 */
public class TypeUtil {

    public static String getTypeString(Class<?> clazz) {
        if (null == clazz) {
            return null;
        }
        if (clazz == byte.class) {
            return "byte";
        }
        if (clazz == short.class) {
            return "short";
        }
        if (clazz == int.class) {
            return "int";
        }
        if (clazz == long.class) {
            return "long";
        }
        if (clazz == double.class) {
            return "double";
        }
        if (clazz == char.class) {
            return "char";
        }
        if (clazz == float.class) {
            return "float";
        }
        if (clazz == boolean.class) {
            return "boolean";
        }
        return clazz.getTypeName();
    }

    public static String getBoxTypeString(Class<?> clazz) {
        if (null == clazz) {
            return null;
        }
        if (clazz == int.class) {
            return "java.lang.Integer";
        }
        if (clazz == char.class) {
            return "java.lang.Character";
        }
        if (clazz == byte.class) {
            return "java.lang.Byte";
        }
        if (clazz == short.class) {
            return "java.lang.Short";
        }
        if (clazz == long.class) {
            return "java.lang.Long";
        }
        if (clazz == double.class) {
            return "java.lang.Double";
        }
        if (clazz == float.class) {
            return "java.lang.Float";
        }
        if (clazz == boolean.class) {
            return "java.lang.Boolean";
        }
        return clazz.getTypeName();
    }

}

package pers.zhangbin.tool.common.util;

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
            clazz = Integer.class;
        }
        if (clazz == char.class) {
            clazz = Character.class;
        }
        if (clazz == byte.class) {
            clazz = Byte.class;
        }
        if (clazz == short.class) {
            clazz = Short.class;
        }
        if (clazz == long.class) {
            clazz = Long.class;
        }
        if (clazz == double.class) {
            clazz = Double.class;
        }
        if (clazz == float.class) {
            clazz = Float.class;
        }
        if (clazz == boolean.class) {
            clazz = Boolean.class;
        }
        return clazz.getTypeName();
    }

}

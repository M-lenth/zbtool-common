package pers.zhangbin.tool.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * ClassName: BeanUtils <br>
 * Description: <p>  </p>  <br>
 *
 * @author zhangbin
 * @create 2022/3/14 9:49
 * @since JDK1.8
 */
public class BeanUtils {
    /**
     * 调用私有方法
     *
     * @param obj        调用的对象
     * @param methodName 目标方法名称
     * @param argsType   参数列表类型
     * @param args       参数列表
     * @return 方法返回值
     */
    public static Object invoke(Object obj, String methodName, Class<?>[] argsType, Object[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> aClass = obj.getClass();
        Method method = aClass.getDeclaredMethod(methodName, argsType);
        method.setAccessible(true);
        return method.invoke(obj, args);
    }

    /**
     * 参数列表为空的情况
     *
     * @param obj        调用的目标对象
     * @param methodName 目标方法
     * @return 调用的目标方法返回值
     */
    public static Object invoke(Object obj, String methodName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return invoke(obj, methodName, null, null);
    }

    /**
     * 复制对象中的属性值 复制的源对象与目标类型中的属性需要一致，当类型不一致时不赋值
     *
     * @param obj    源对象
     * @param tClass 目标对象的类型
     * @return 复制后目标对象的值
     */
    public static <T> T copyProperties(Object obj, Class<T> tClass) throws InstantiationException, IllegalAccessException {
        T t = tClass.newInstance();
        Class<?> tmpClass = obj.getClass();
        while (tmpClass != null) {
            for (Field f : tmpClass.getDeclaredFields()) {
                try {
                    Field field = tClass.getDeclaredField(f.getName());
                    field.setAccessible(true);
                    f.setAccessible(true);
                    field.set(t, f.get(obj));
                } catch (IllegalAccessException | NoSuchFieldException ignored) {

                }
            }
            tmpClass = tmpClass.getSuperclass();
        }
        return t;
    }

    /**
     * 将对象转换为Map
     *
     * @param obj 转换的对象
     * @return 转换后的Map
     */
    public static Map<String, Object> objectToMap(Object obj) {
        Map<String, Object> map = new LinkedHashMap<>();
        Class<?> tmpClass = obj.getClass();
        while (tmpClass != null) {
            for (Field field : tmpClass.getDeclaredFields()) {
                try {
                    map.put(field.getName(), field.get(obj));
                } catch (IllegalAccessException ignored) {
                }
            }
            tmpClass = tmpClass.getSuperclass();
        }
        return map;
    }

    /**
     * 把配置文件转换为Map对象
     *
     * @param properties 配置文件 {@link Properties}
     * @return 转换后的Map对象
     */
    public static Map<String, Object> propToMap(Properties properties) {
        Set<Object> objects = properties.keySet();
        Map<String, Object> map = new LinkedHashMap<>();
        for (Object o : objects) {
            Object o1 = properties.get(o);
            map.put((String) o, o1);
        }
        return map;
    }

}

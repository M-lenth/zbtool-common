package com.zhangbin.tool.mybatis;


import com.zhangbin.tool.common.DateUtil;
import com.zhangbin.tool.constant.DateConstant;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;

/**
 * Classname: SqlInterceptor <br>
 * Description: <p> SQL拦截并拼接 </p>  <br>
 *
 * @author zhangbin
 * @create 2021/12/30 17:42
 * @since JDK1.8
 */
@Intercepts
    ({
        // 拦截execute的Query与Update方法
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
    })
public class MybatisSqlPrinter implements Interceptor {

    private Logger logger = Logger.getLogger(MybatisSqlPrinter.class);


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameterObject = null;
        if (invocation.getArgs().length > 1) {
            parameterObject = invocation.getArgs()[1];
        }
        // 开始时间
        long start = System.currentTimeMillis();
        // 执行SQL语句
        Object result = invocation.proceed();
        BoundSql boundSql = mappedStatement.getBoundSql(parameterObject);
        Configuration configuration = mappedStatement.getConfiguration();
        // 获取SQL方法
        String sql = getSql(boundSql, parameterObject, configuration);
        long end = System.currentTimeMillis();
        long timing = end - start;
        if (logger.isInfoEnabled()) {
            logger.info("执行SQL成功, 执行时间：[ " + timing + " ms], sql: \n");
            logger.info(sql);
        }
        return result;
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            // 代理执行拦截器的操作
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {
    }

    /**
     * 拼接SQL方法
     * @param boundSql
     * @param parameterObject 参数
     * @param configuration
     * @return 拼接后的SQL
     */
    private String getSql(BoundSql boundSql, Object parameterObject, Configuration configuration) {
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        if (parameterMappings != null) {
            for (ParameterMapping parameterMapping : parameterMappings) {
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    Object value;
                    String propertyName = parameterMapping.getProperty();
                    if (boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (parameterObject == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                        value = parameterObject;
                    } else {
                        MetaObject metaObject = configuration.newMetaObject(parameterObject);
                        value = metaObject.getValue(propertyName);
                    }
                    sql = replacePlaceholder(sql, value);
                }
            }
        }
        return sql;
    }

    /**
     * 替换占位符
     *
     * @param sql           sql语句
     * @param propertyValue 参数对应的值
     * @return 拼接后的SQL
     */
    private String replacePlaceholder(String sql, Object propertyValue) {
        String result;
        if (propertyValue != null) {
            if (propertyValue instanceof String) {
                // 拼接字符串
                result = "'" + propertyValue + "'";
            } else if (propertyValue instanceof Date) {
                // 日期
                try {
                    result = "'" + DateUtil.getDate((String) propertyValue, DateConstant.YYYY_MM_DD_HH_MM_SS) + "'";
                } catch (ParseException e) {
                    logger.info("出现异常: ", e);
                    result = "";
                }
            } else {
                // 其余类型
                result = propertyValue.toString();
            }
        } else {
            result = "null";
        }
        return sql.replaceFirst("\\?", Matcher.quoteReplacement(result));
    }
}

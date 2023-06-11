package pers.zhangbin.tool.mybatis;

import pers.zhangbin.tool.io.Resource;
import pers.zhangbin.tool.mybatis.bean.DataBaseConfigBean;
import pers.zhangbin.tool.mybatis.mapper.BaseMapper;
import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

/**
 * ClassName: MybatisManager <br>
 * Description: <p>  </p>  <br>
 *
 * @author zhangbin
 * @create 2022/3/11 10:18
 * @since JDK1.8
 */
public class MybatisManager {

    private static final ThreadLocal<SqlSession> POOL = new ThreadLocal<>();

    public static <T extends BaseMapper> T getMapper(Class<T> clazz, DataBaseConfigBean bean) throws IOException {
        SqlSession session = POOL.get();
        if (session != null) {
            return session.getMapper(clazz);
        }

        SqlSessionFactory factory = buildSqlSessionFactory(new Resource("mybatis-config.xml").getResourceAsReader(), bean);
        session = factory.openSession();
        POOL.set(session);
        return session.getMapper(clazz);
    }

    private static SqlSessionFactory buildSqlSessionFactory(Reader reader, DataBaseConfigBean bean) {
        Properties properties = new Properties();
        properties.setProperty("url", bean.getUrl());
        properties.setProperty("driver", bean.getDriver());
        properties.setProperty("username", bean.getUsername());
        properties.setProperty("password", bean.getPassword());
        XMLConfigBuilder parser = new XMLConfigBuilder(reader, null, properties);
        Configuration configuration = parser.parse();
        if (!bean.getMappers().isEmpty()) {
            for (Class<?> c : bean.getMappers()) {
                configuration.addMapper(c);
            }
        }
        return new DefaultSqlSessionFactory(configuration);
    }

}

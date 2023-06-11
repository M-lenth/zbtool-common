package pers.zhangbin.tool.mybatis;

import java.util.Date;

/**
 * ClassName: DataBaseType <br>
 * Description: <p>  </p>  <br>
 *
 * @author zhangbin
 * @create 2022/3/11 9:57
 * @since JDK1.8
 */
public enum DataBaseType {
    /**
     *
     */
    LONG("bigint", long.class),
    INT("int", int.class),
    INTEGER("integer", Integer.class),
    STRING("varchar", String.class),
    TEXT("text", String.class),
    CHAR("char", char.class),
    DATE_TIME("datetime", Date.class),
    DATE("date", Date.class),
    TIMESTAMP("time", Date.class),
    FLOAT("float", float.class),
    DOUBLE("double", double.class);
    private String dbString;
    private Class<?> clazz;

    DataBaseType(String dbString, Class<?> clazz) {
        this.dbString = dbString;
        this.clazz = clazz;
    }

    public String getDbString() {
        return dbString;
    }

    public void setDbString(String dbString) {
        this.dbString = dbString;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return "DataBaseType{" +
            "dbString='" + dbString + '\'' +
            ", clazz=" + clazz +
            '}';
    }

    public static DataBaseType getTypeByTypeString(String type) {
        DataBaseType[] values = DataBaseType.values();
        for (DataBaseType db : values) {
            if (db.dbString.equals(type)) {
                return db;
            }
        }
        return null;
    }

    /**
     * 根据数据库类型字段，截取前面的字符串
     *
     * @param sqlType 数据库类型，如varchar(10) int(2) int
     * @return
     */
    public static String getTypeString(String sqlType) {
        int i = sqlType.indexOf("(");
        if (i == -1) {
            return sqlType;
        }
        return sqlType.substring(0, i);
    }
}

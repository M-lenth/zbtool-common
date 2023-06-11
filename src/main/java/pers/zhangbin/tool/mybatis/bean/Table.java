package pers.zhangbin.tool.mybatis.bean;

/**
 * ClassName: Table <br>
 * Description: <p>  </p>  <br>
 *
 * @author zhangbin
 * @create 2022/3/11 9:27
 * @since JDK1.8
 */
public class Table {

    private String field;
    private String type;
    private String nullable;
    private String key;
    private String extra;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNullable() {
        return nullable;
    }

    public void setNullable(String nullable) {
        this.nullable = nullable;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "Table{" +
            "field='" + field + '\'' +
            ", type='" + type + '\'' +
            ", nullable='" + nullable + '\'' +
            ", key='" + key + '\'' +
            ", extra='" + extra + '\'' +
            '}';
    }
}

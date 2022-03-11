package com.zhangbin.tool.mybatis.mapper;

import com.zhangbin.tool.mybatis.bean.Table;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * ClassName: TableMapper <br>
 * Description: <p>  </p>  <br>
 *
 * @author zhangbin
 * @create 2022/3/11 9:28
 * @since JDK1.8
 */
public interface TableMapper extends BaseMapper {

    @Select("desc ${table}")
    @Results({
        @Result(property = "field", column = "Field", javaType = String.class),
        @Result(property = "type", column = "Type", javaType = String.class),
        @Result(property = "nullable", column = "Null", javaType = String.class),
        @Result(property = "key", column = "Key", javaType = String.class),
        @Result(property = "extra", column = "Extra", javaType = String.class)
    })
    List<Table> getTable(@Param("table") String table);

    @Select("show tables")
    List<String> getTablesName();

}

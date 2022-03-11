package com.zhangbin.tool.io;

import com.zhangbin.tool.common.constant.DateConstant;
import com.zhangbin.tool.common.filestorage.FileUtil;
import com.zhangbin.tool.common.util.DateUtil;
import com.zhangbin.tool.common.util.StringUtils;
import com.zhangbin.tool.common.util.TypeUtil;
import com.zhangbin.tool.mybatis.DataBaseType;
import com.zhangbin.tool.mybatis.MybatisManager;
import com.zhangbin.tool.mybatis.bean.DataBaseConfigBean;
import com.zhangbin.tool.mybatis.bean.Table;
import com.zhangbin.tool.mybatis.mapper.TableMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ClassName: GenerateFile <br>
 * Description: <p>  </p>  <br>
 *
 * @author zhangbin
 * @create 2022/3/11 10:46
 * @since JDK1.8
 */
public class GenerateFile {

    private final DataBaseConfigBean bean;
    /**
     * 文件路径
     */
    private final String path;

    public GenerateFile(DataBaseConfigBean bean, String path) {
        this.bean = bean;
        this.path = path;
    }

    public void generateDoFile(String tableName, String packageName) throws IOException {
        File directory = FileUtil.createNewDirectory(path + File.separator + packageName.replace(".", File.separator));
        TableMapper tableMapper = MybatisManager.getMapper(TableMapper.class, bean);
        List<Table> table = tableMapper.getTable(tableName);
        if (directory == null) {
            return;
        }
        // 创建文件
        String className = StringUtils.getClassNameByTbName(tableName);
        File javaFile = FileUtil.createNewFile(directory.getPath() + File.separator + className + ".java");
        if (javaFile == null) {
            return;
        }
        // import语句
        List<String> importList = new ArrayList<>();
        List<String> setter = new ArrayList<>();
        List<String> getter = new ArrayList<>();
        // POJO类文件
        StringBuilder content = new StringBuilder("public class ").append(className).append(" {\n\n");
        for (Table tb : table) {
            // 字段
            DataBaseType type = DataBaseType.getTypeByTypeString(DataBaseType.getTypeString(tb.getType()));
            if (type == null) {
                return;
            }
            String typeName = TypeUtil.getBoxTypeString(type.getClazz());
            if (!typeName.startsWith("java.lang") && !importList.contains(typeName)) {
                importList.add("import " + typeName + ";\n");
            }
            String columnType = StringUtils.getStringExtend(typeName);
            String columnName = StringUtils.underLineToHump(tb.getField());
            content.append("\tprivate ").append(columnType).append(" ").append(columnName).append(";\n");
            StringBuilder getterBuilder = new StringBuilder("\tpublic ");
            String setterString = "\tpublic void" + " set" + StringUtils.toUpperFirstChar(columnName) +
                "(" + columnType + " " + columnName + ") {\n" +
                "\t\tthis." + columnName + " = " + columnName + ";" +
                "\n\t}\n";
            setter.add(setterString);
            getterBuilder.append(columnType).append(" get").append(StringUtils.toUpperFirstChar(columnName))
                .append("() {\n").append("\t\treturn this.").append(columnName).append(";\n\t}\n");
            getter.add(getterBuilder.toString());
        }
        content.append("\n");
        for (int i = 0; i < getter.size(); i++) {
            content.append("\n");
            content.append(getter.get(i));
            content.append("\n");
            content.append(setter.get(i));
        }
        content.append("}\n");
        // 写入文件
        try (FileWriter writer = new FileWriter(javaFile)) {
            writer.write("package " + packageName + ";\n\n");
            for (String s : importList) {
                writer.append(s).append("\n");
            }
            // 创建信息
            writer.append("\n\n" + "/**\n" + " * ClassName: GenerateFile <br>\n")
                .append(" * Description: <p>  </p>  <br>\n" + " *\n" + " * @author zhangbin\n" + " * @create ")
                .append(DateUtil.format(new Date(), DateConstant.YYYY_MM_DD_HH_MM_EM))
                .append("\n").append(" * @since JDK1.8\n").append(" */\n");
            writer.append(content);
            writer.flush();
        }
    }

    public void generateDoFile(String packageName) throws IOException {
        TableMapper mapper = MybatisManager.getMapper(TableMapper.class, bean);
        List<String> tablesName = mapper.getTablesName();
        for (String tbName : tablesName) {
            generateDoFile(tbName, packageName);
        }
    }


}

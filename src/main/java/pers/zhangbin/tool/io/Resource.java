package pers.zhangbin.tool.io;

import java.io.*;
import java.util.Objects;

/**
 * ClassName: Resource <br>
 * Description: <p> 获取Maven下的resource文件夹资源内容 </p>  <br>
 *
 * @author zhangbin
 * @create 2022/3/11 10:22
 * @since JDK1.8
 */
public class Resource {

    private final String path;

    private final File file;

    public Resource(String path) {
        this.path = path;
        String rootPath = Objects.requireNonNull(Resource.class.getResource("/")).getPath();
        this.file = new File(rootPath + path);
    }

    public String getPath() {
        return path;
    }

    /**
     * <p> 获取资源文件的内容，以Reader形式返回 </p>
     *
     * @return 文件字符Reader流
     * @see java.io.Reader
     */
    public Reader getResourceAsReader() throws FileNotFoundException {
        return new FileReader(file);
    }

    /**
     * <p> 获取资源文件内容，以字节流InputStream形式返回 </p>
     *
     * @return 文件字节流
     * @see java.io.InputStream
     */
    public InputStream getResourceAsInputStream() throws FileNotFoundException {
        return new FileInputStream(file);
    }

    /**
     * <p> 获取绝对路径 </p>
     *
     * @return 文件绝对路径
     */
    public String getAbsolutePath() {
        return this.file.getPath();
    }

    @Override
    public String toString() {
        return file.getName();
    }
}

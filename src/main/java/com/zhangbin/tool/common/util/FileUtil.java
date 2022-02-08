package com.zhangbin.tool.common.util;

import java.io.File;
import java.io.IOException;

/**
 * ClassName: FileUtil <br>
 * Description: <p>  </p>  <br>
 *
 * @author zhangbin
 * @create 2022/2/8 10:47
 * @since JDK1.8
 */
public class FileUtil {

    /**
     * 创建新文件
     *
     * @param path 文件路径
     * @return 新文件
     */
    public static File createNewFile(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            boolean newFile = file.createNewFile();
            if (!newFile) {
                return null;
            }
        }
        return file;
    }

    /**
     * 创建新目录
     *
     * @param path 目录路径
     * @return 目录文件
     */
    public static File createNewDirectory(String path) {
        File file = new File(path);
        if (!file.exists()) {
            boolean flag = file.mkdirs();
            if (!flag) {
                return null;
            }
        }
        return file;
    }

}

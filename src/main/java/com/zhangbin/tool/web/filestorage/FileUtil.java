package com.zhangbin.tool.web.filestorage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Classname: FastDfsClientUtil <br>
 * Description: <p>  </p>  <br>
 *
 * @author zhangbin
 * @create 2021/12/17 16:42
 * @since JDK1.8
 */
public class FileUtil {

    /**
     * 根据java.io.File获取文件内容的字节数组
     *
     * @param file 目标文件
     * @return 字节数组
     * @see File
     */
    public static byte[] getByteArray(File file) {
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }


    /**
     * 获取文件扩展名
     *
     * @param file 文件名
     * @return 文件扩展名
     * @see File
     */
    private static String getExt(File file) {
        String filename = file.getName();
        int index = filename.lastIndexOf(".");

        if (index == -1) {
            return null;
        }
        return filename.substring(index + 1);
    }

    /**
     * 根据文件对象获取文件不带后缀名称
     *
     * @param file 文件对象
     * @return 不带后缀名称
     * @see File
     */
    private static String getNameWithoutExt(File file) {
        String name = file.getName();
        int index = name.lastIndexOf(".");
        if (index == -1) {
            return null;
        }
        return name.substring(0, index);
    }

}

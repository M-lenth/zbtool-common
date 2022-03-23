package com.zhangbin.tool.common.filestorage;

import com.zhangbin.tool.common.util.StringUtils;

import java.io.*;
import java.util.Collections;

/**
 * ClassName: FileUtil <br>
 * Description: <p>  </p>  <br>
 *
 * @author zhangbin
 * @create 2021/12/17 16:42
 * @since JDK1.8
 */
public class FileUtil {

    /**
     * 文件大小 KB MB
     */
    public static final int KB = 1024;
    public static final int MB = 1024 * KB;
    public static final int GB = 1024 * MB;

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

    /**
     * 根据文件对象，构造上传入参
     *
     * @param file 文件对象
     * @return 上传文件对象
     */
    public static FastDfsFile createByFile(File file) {
        FastDfsFile dfsFile = new FastDfsFile();
        dfsFile.setAuthor(System.getenv("USERNAME"));
        dfsFile.setContent(getByteArray(file));
        dfsFile.setExt(getExt(file));
        dfsFile.setName(getNameWithoutExt(file));
        return dfsFile;
    }


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

    /**
     * 将字符串数据写到文件中
     *
     * @param file    目标文件
     * @param content 需要写入的数据
     */
    public static void write(File file, String content) throws IOException {
        FileWriter writer = new FileWriter(file);
        writer.write(content);
        writer.flush();
        writer.close();
    }

    /**
     * 向文件中追加字符串数据
     *
     * @param file    文件
     * @param content 追加的数据
     */
    public static void append(File file, String content) throws IOException {
        String old = read(file);
        FileWriter writer = new FileWriter(file);
        writer.append(old).append(content);
        writer.flush();
        writer.close();
    }

    /**
     * 从文件中读取字符串
     *
     * @param file 文件
     * @return 字符串
     */
    public static String read(File file) throws IOException {
        StringBuilder builder = new StringBuilder();
        FileReader reader = new FileReader(file);
        int ch;
        while ((ch = reader.read()) != -1) {
            builder.append((char) ch);
        }
        reader.close();
        return builder.toString();
    }

    /**
     * 删除文件
     *
     * @param file 需要删除的文件
     */
    public static void clear(File file) {
        boolean delete = file.delete();
        if (!delete) {
            throw new RuntimeException("删除文件[" + file.getName() + "] 失败");
        }
    }

    /**
     * 分裂文件
     *
     * @param source          源文件路径
     * @param targetDirectory 目标目录路径
     * @return 分离的文件信息
     */
    public static SplitFile split(String source, String targetDirectory) throws IOException {
        SplitFile splitFile = new SplitFile();
        File file = new File(source);
        FileInputStream fis = new FileInputStream(file);
        byte[] memory = new byte[MB];
        int index = 0;
        int len, sum = 0;
        long length = file.length();
        File newDirectory = createNewDirectory(targetDirectory);
        boolean flag = false;
        while ((len = fis.read(memory)) != -1) {
            String files = newDirectory.getPath() + File.separator + StringUtils.getFilename(file.getName()) + "_" + ++index;
            File newFile = createNewFile(files);
            byte[] tp;
            if (flag) {
                int tmp = (int) (length - sum);
                tp = new byte[tmp];
                System.arraycopy(memory, 0, tp, 0, tp.length);
            } else {
                tp = memory;
            }
            try (OutputStream fos = new FileOutputStream(newFile)) {
                fos.write(tp);
                fos.flush();
            }
            SplitFileInfo fileInfo = new SplitFileInfo();
            fileInfo.setIndex(index);
            fileInfo.setFilename(newFile.getPath());
            splitFile.getInfos().add(fileInfo);
            sum += len;
            flag = length - sum < MB;
        }
        splitFile.setExt(StringUtils.getStringExtend(file.getName()));
        splitFile.setFilename(StringUtils.getFilename(file.getName()));
        splitFile.setPath(file.getPath());
        return splitFile;
    }


    /**
     * 合并文件
     *
     * @param splitFile 分离的的文件信息 {@link SplitFile}
     * @return 合并后的文件对象
     */
    public static OutputStream merge(SplitFile splitFile) throws IOException {
        Collections.sort(splitFile.getInfos());
        // 合并后的文件路径
        String path = splitFile.getPath() + File.separator + splitFile.getFilename() + "." + splitFile.getExt();
        // 合并后的文件对象
        File newFile = FileUtil.createNewFile(path);
        if (null == newFile) {
            throw new IOException("文件[ " + path + " ]创建失败");
        }
        // 使用字节流读取分离的文件信息
        try (FileOutputStream fos = new FileOutputStream(newFile);) {
            for (SplitFileInfo fileInfo : splitFile.getInfos()) {
                byte[] bytes = FileUtil.getByteArray(new File(fileInfo.getFilename()));
                fos.write(bytes);
                fos.flush();
            }
        }
        return new FileOutputStream(newFile);
    }


}

package pers.zhangbin.tool.common.filestorage;

/**
 * Classname: FastDfsFile <br>
 * Description: <p> FastDfsFile文件 </p>  <br>
 *
 * @author zhangbin
 * @create 2021/12/17 16:39
 * @since JDK1.8
 */
public class FastDfsFile {
    /**
     * 名称
     */
    private String name;
    /**
     * 内容
     */
    private byte[] content;
    /**
     * 扩展名（后缀）
     */
    private String ext;
    /**
     * 文件的MD5摘要值
     */
    private String md5;
    /**
     * 文件的作者
     */
    private String author;

    public FastDfsFile() {
    }

    public FastDfsFile(String name, byte[] content, String ext, String md5, String author) {
        this.name = name;
        this.content = content;
        this.ext = ext;
        this.md5 = md5;
        this.author = author;
    }

    public FastDfsFile(String name, byte[] content, String ext) {
        this.name = name;
        this.content = content;
        this.ext = ext;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


}

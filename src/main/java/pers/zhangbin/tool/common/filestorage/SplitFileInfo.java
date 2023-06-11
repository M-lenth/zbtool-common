package pers.zhangbin.tool.common.filestorage;

/**
 * ClassName: FileInfo <br>
 * Description: <p>  </p>  <br>
 *
 * @author zhangbin
 * @create 2022/3/23 15:49
 * @since JDK1.8
 */
public class SplitFileInfo implements Comparable<SplitFileInfo> {
    /**
     * 子文件排序索引
     */
    private int index;
    /**
     * 分离的子文件名称
     */
    private String filename;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public int compareTo(SplitFileInfo o) {
        return index - o.index;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "index=" + index +
                ", filename='" + filename + '\'' +
                '}';
    }
}
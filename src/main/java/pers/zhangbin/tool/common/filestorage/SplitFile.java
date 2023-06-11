package pers.zhangbin.tool.common.filestorage;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: SplitFile <br>
 * Description: <p>  </p>  <br>
 *
 * @author zhangbin
 * @create 2022/3/23 15:48
 * @since JDK1.8
 */
public class SplitFile {
    /**
     * 文件路径
     */
    private String path;
    /**
     * 文件后缀
     */
    private String ext;
    /**
     * 文件名
     */
    private String filename;
    /**
     * 文件信息
     */
    private List<SplitFileInfo> infos = new ArrayList<>();


    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public List<SplitFileInfo> getInfos() {
        return infos;
    }

    public void setInfos(List<SplitFileInfo> infos) {
        this.infos = infos;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    @Override
    public String toString() {
        return "SplitFile{" +
                "path='" + path + '\'' +
                ", ext='" + ext + '\'' +
                ", filename='" + filename + '\'' +
                ", infos=" + infos +
                '}';
    }
}
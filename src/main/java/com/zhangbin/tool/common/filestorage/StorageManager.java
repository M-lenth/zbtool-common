package com.zhangbin.tool.common.filestorage;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Classname: StorageManager <br>
 * Description: <p>  </p>  <br>
 *
 * @author zhangbin
 * @create 2021/12/18 9:53
 * @since JDK1.8
 */
public class StorageManager {


    private static final AtomicBoolean INITIALIZED = new AtomicBoolean(false);

    public StorageManager() {
    }

    /**
     * 初始化连接
     *
     * @param conf 连接配置文件地址
     */
    public static void init(String conf) throws IOException, MyException {
        if (INITIALIZED.get()) {
            throw new RuntimeException("已初始化过");
        }
        // 未初始化过
        ClientGlobal.init(conf);
        INITIALIZED.set(true);
    }

    /**
     * 获取TrackerServer服务端
     *
     * @return Tracker
     */
    public static TrackerServer getTrackerServer() throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        return trackerClient.getConnection();
    }

    /**
     * 获取StorageClient客户端
     */
    public static StorageClient getStorageClient() throws IOException {
        TrackerServer server = getTrackerServer();
        return new StorageClient(server, null);
    }

    /**
     * 文件上传
     *
     * @param file 文件封装信息
     * @return 文件在服务器的信息 ip:port/[0]/[1]
     */
    public static String[] upload(FastDfsFile file) {
        // 创建 meta_list数组
        NameValuePair[] metaList = new NameValuePair[1];
        // 使用文件作者创建meta_list对象
        metaList[0] = new NameValuePair(file.getAuthor());
        // 返回值
        String[] uploadResult = null;
        try {
            // 获取StorageClient连接
            StorageClient storageClient = getStorageClient();
            uploadResult = storageClient.upload_file(file.getContent(), file.getExt(), metaList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uploadResult;
    }

    /**
     * 从服务器下载文件
     *
     * @param groupName      文件在服务器上的组名
     * @param remoteFilename 文件在服务器的虚拟磁盘全路径
     * @return 文件的流 需要处理
     */
    public static byte[] download(String groupName, String remoteFilename) {
        try {
            // 获取StorageClient对象
            StorageClient storageClient = getStorageClient();
            // 执行下载获取文件数据，以字节数组形式保存
            return storageClient.download_file(groupName, remoteFilename);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取文件的信息
     *
     * @param groupName      文件组名
     * @param remoteFilename 文件在服务器上的虚拟全路径
     * @return 文件信息 FileInfo
     */
    public static FileInfo getFileInfo(String groupName, String remoteFilename) {
        FileInfo info = null;
        try {
            // 获取连接
            StorageClient client = getStorageClient();
            // 获取文件信息
            info = client.get_file_info(groupName, remoteFilename);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }

    /**
     * 获得组的信息
     *
     * @param groupName      组名
     * @param remoteFilename 虚拟磁盘全路径
     * @return 组的信息 ServerInfo[]
     */
    public static ServerInfo[] getGroupInfo(String groupName, String remoteFilename) {
        ServerInfo[] infos = null;
        try {
            // 创建TrackerClient对象
            TrackerClient client = new TrackerClient();
            // 获取TrackerServer服务端
            TrackerServer server = client.getConnection();
            // 组对应的服务器地址  可能有多个服务器地址
            infos = client.getFetchStorages(server, groupName, remoteFilename);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return infos;
    }

    /**
     * 获得Storage信息
     *
     * @param groupName 组名
     * @return Storage信息 StorageServer
     */
    public static StorageServer getStorage(String groupName) {
        StorageServer storage = null;
        try {
            // 创建TrackerClient对象
            TrackerClient client = new TrackerClient();
            // 创建TrackerServer对象
            TrackerServer server = client.getConnection();
            // 返回组的信息
            storage = client.getStoreStorage(server, groupName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return storage;
    }

    /**
     * 获取TrackerServer的URL信息
     *
     * @return TrackerServer的URL
     */
    public static String getTrackerUrl() {
        String url = null;
        try {
            // 获得TrackerServer的信息
            TrackerServer server = getTrackerServer();
            // 需使用ClientGlobal获得端口号 为服务器外部链接端口号本次为8080
            // server.getInetSocketAddress().getPort()为TrackerServer的端口号，在本次为 22122
            url = "http://" + server.getInetSocketAddress() + ":" + ClientGlobal.getG_tracker_http_port();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * 文件删除
     *
     * @param groupName      组名
     * @param remoteFilename 文件在服务器的虚拟全路径
     * @return 整形 Integer
     */
    public static Integer delete(String groupName, String remoteFilename) {
        try {
            // 获得StorageClient对象
            StorageClient client = getStorageClient();
            // 执行删除操作
            return client.delete_file(groupName, remoteFilename);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取网络访问地址
     *
     * @param ip   服务器IP
     * @param port 服务器开放端口
     * @param url  FastDFS服务器里面的虚拟磁盘路径数组
     * @return 实际访问文件服务器的网络地址
     */
    public static String getHttpUrl(String ip, String port, String[] url) {
        return "http://" + ip + ":" + port + "/" + url[0] + "/" + url[1];
    }

}

package com.zhangbin.tool.common.filestorage;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;

import java.io.IOException;
import java.util.Collections;

/**
 * Classname: StorageManager <br>
 * Description: <p>  </p>  <br>
 *
 * @author zhangbin
 * @create 2021/12/18 9:53
 * @since JDK1.8
 */
public class StorageManager {

    public static Connection getConnection(String conf) {
        try {
            ClientGlobal.init(conf);
        } catch (IOException | MyException e) {
            e.printStackTrace();
        }
        return new Connection();
    }
}

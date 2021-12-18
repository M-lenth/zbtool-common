package com.zhangbin.tool.common.filestorage;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;

import java.io.IOException;
import java.util.Collections;

/**
 * Classname: Connection <br>
 * Description: <p>  </p>  <br>
 *
 * @author zhangbin
 * @create 2021/12/17 16:51
 * @since JDK1.8
 */
public class Connection {

    static {
        try {
            String path = Collections.class.getResource("").getPath();
            ClientGlobal.init(path);
        } catch (IOException | MyException e) {
            e.printStackTrace();
        }
    }

}

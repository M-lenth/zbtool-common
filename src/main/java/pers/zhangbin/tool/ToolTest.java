package pers.zhangbin.tool;

import pers.zhangbin.tool.common.util.StringUtils;
import pers.zhangbin.tool.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * ClassName: ToolTest <br>
 * Time 2023/6/13 23:39
 * Description: <p>  </p>  <br>
 *
 * @author zhangbin
 * @since JDK1.8
 */
public class ToolTest {

    public static void main(String[] args) throws IOException {
        InputStream is = new Resource("tst.txt").getResourceAsInputStream();
        String string = StringUtils.getStringByInStream(is, StandardCharsets.UTF_8);
        System.out.println(string);
    }
}

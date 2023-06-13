package pers.zhangbin.tool.net.function;

import pers.zhangbin.tool.net.HttpRequest;
import pers.zhangbin.tool.net.HttpResponse;

import java.io.IOException;

/**
 * ClassName: RequestMethod <br>
 * Description: <p> 请求方式的调用 </p>  <br>
 *
 * @author zhangbin
 * @create 2022/2/11 16:29
 * @since JDK1.8
 */
@FunctionalInterface
public interface RequestInvoke {
    /**
     * 实现网络请求
     *
     * @param req 请求参数
     * @return 请求相应数据
     */
    HttpResponse execute(HttpRequest req) throws IOException;
}

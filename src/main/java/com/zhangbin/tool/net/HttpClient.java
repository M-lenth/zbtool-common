package com.zhangbin.tool.net;

import com.alibaba.fastjson.JSON;
import com.zhangbin.tool.net.function.RequestInvoke;
import com.zhangbin.tool.web.Result;

import java.io.IOException;

/**
 * ClassName: HttpClient <br>
 * Description: <p>  </p>  <br>
 *
 * @author zhangbin
 * @create 2022/2/11 16:19
 * @since JDK1.8
 */
public class HttpClient {
    /**
     * 网络请求
     *
     * @param url   请求地址
     * @param body  请求参数
     * @param <REQ> 请求参数类型泛型
     * @return 请求服务器返回的数据
     */
    public static <REQ> Result<?> execute(String url, REQ body, RequestInvoke invoke) {
        // 构建请求参数
        HttpRequest req = new HttpRequest.Builder()
            .url(url)
            .post(JSON.toJSONString(body))
            .build();
        try {
            // 发起请求
            HttpResponse response = invoke.execute(req);
            return JSON.parseObject((String) response.getData(), Result.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

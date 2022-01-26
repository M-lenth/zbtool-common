import com.zhangbin.tool.common.util.DateUtil;
import com.zhangbin.tool.net.HttpRequest;
import com.zhangbin.tool.net.HttpResponse;
import com.zhangbin.tool.net.HttpUtil;

import java.text.ParseException;
import java.util.Date;

/**
 * Classname: CommonTest <br>
 * Description: <p>  </p>  <br>
 *
 * @author zhangbin
 * @create 2022/1/26 11:28
 * @since JDK1.8
 */
public class CommonTest {

    public static void main(String[] args) throws ParseException {
        System.out.println(DateUtil.format(new Date()));
        System.out.println(DateUtil.format(new Date(),"yyyyMMdd"));
        System.out.println(DateUtil.getDate("2022-01-26 14:18:54"));
        System.out.println(DateUtil.getDate("20220126","yyyyMMdd"));
    }

}

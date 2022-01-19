import com.zhangbin.tool.common.NumberUtil;

/**
 * Classname: NumberTest <br>
 * Description: <p>  </p>  <br>
 *
 * @author zhangbin
 * @create 2021/12/30 17:19
 * @since JDK1.8
 */
public class NumberTest {


    public static void main(String[] args) {
        Number n1 = 1;
        Number n2 = 2L;
        Number n3 = 3.0d;
        Number n4 = 3.0d;
        Number n5 = 3.0f;
        System.out.println(NumberUtil.getType(n1));
        System.out.println(NumberUtil.getType(n2));
        System.out.println(NumberUtil.getType(n3));
        System.out.println(NumberUtil.getType(n4));
        System.out.println(NumberUtil.getType(n5));
        System.out.println(NumberUtil.isSameType(n3, n4));
    }
}

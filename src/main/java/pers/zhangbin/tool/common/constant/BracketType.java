package pers.zhangbin.tool.common.constant;

/**
 * ClassName: BracketType <br>
 * Time 2023/6/7 18:07
 * Description: <p>  </p>  <br>
 *
 * @author zhangbin
 * @since JDK1.8
 */
public enum BracketType {

    /**
     * <p> 花括号 </p>
     */
    BRACE('{', '}'),
    /**
     * <p> 方括号 </p>
     */
    SQUARE_BRACKETS('[', ']'),
    /**
     * <p> 圆括号 </p>
     */
    ROUND_BRACKETS('(', ')');

    private final Character left;
    private final Character right;

    BracketType(Character left, Character right) {
        this.left = left;
        this.right = right;
    }

    public Character getLeft() {
        return left;
    }

    public Character getRight() {
        return right;
    }
}

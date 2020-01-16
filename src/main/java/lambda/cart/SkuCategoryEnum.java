package lambda.cart;

/**
 * 商品类型枚举
 */
public enum SkuCategoryEnum {
    CLOTHING(10,"服装类"),
    ELECTRONICE(20,"电子类"),
    SPORTS(30,"运动类"),
    BOOKS(40,"图书类");

    //商品类型编号
    private Integer code;

    //商品类型名称
    private String name;

    SkuCategoryEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}

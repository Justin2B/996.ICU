package lambda.cart;

/**
 * 下单商品信息对象
 */
public class Sku {

    //编号
    private Integer sukId;

    //商品名称
    private String skuName;

    //单价
    private Double skuPrice;

    //购买个数
    private Integer totalNum;

    //总价
    private Double totalPrice;

    //商品类型
    private Enum skuCategory;



    public Sku(Integer sukId, String skuName, Double skuPrice, Integer totalNum, Double totalPrice, Enum skuCategory) {
        this.sukId = sukId;
        this.skuName = skuName;
        this.skuPrice = skuPrice;
        this.totalNum = totalNum;
        this.totalPrice = totalPrice;
        this.skuCategory = skuCategory;
    }


    public Integer getSukId() {
        return sukId;
    }

    public void setSukId(Integer sukId) {
        this.sukId = sukId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public Double getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(Double skuPrice) {
        this.skuPrice = skuPrice;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Enum getSkuCategory() {
        return skuCategory;
    }

    public void setSkuCategory(Enum skuCategory) {
        this.skuCategory = skuCategory;
    }
}

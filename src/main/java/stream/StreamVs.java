package stream;

import com.alibaba.fastjson.JSON;
import lambda.cart.CartService;
import lambda.cart.Sku;
import lambda.cart.SkuCategoryEnum;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * 对比：原始集合操作与Stream集合操作
 */
public class StreamVs {

    /**
     * 1. 查询购物车商品列表
     * 2. 过滤图书类商品
     * 3. 其余商品中购买两件最贵的
     * 4. 查询两件商品的名称和总价
     */

    /**
     * 以原始集合操作实现需求
     */
    @Test
    public void oldCartHandle(){
        List<Sku> cartSkuList = CartService.getCartSkuList();
        /**
         * 1. 打印购物车中所有商品
         */
        for (Sku sku : cartSkuList) {
            System.out.println(JSON.toJSONString(sku,true));
        }

        /**
         * 2. 过滤图书类商品
         */
        List<Sku> notBoodsSkuList = new ArrayList<>();
        for (Sku sku : cartSkuList) {
            if (!SkuCategoryEnum.BOOKS.equals(sku.getSkuCategory())) {
                notBoodsSkuList.add(sku);
            }
        }

        /**
         * 排序
         */
        notBoodsSkuList.sort(new Comparator<Sku>() {
            @Override
            public int compare(Sku sku1, Sku sku2) {
                if (sku1.getTotalPrice() == sku2.getTotalPrice()) {
                    return 0;
                }else {
                   return sku1.getTotalPrice()>sku2.getTotalPrice()?-1:1;
                }
            }
        });

        /**
         * 最贵的两件商品
         */
        List<Sku> top2SkuList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            top2SkuList.add(notBoodsSkuList.get(i));
        }

        /**
         * 4. 两件商品的总价
         */
        Double money = 0.0;
        for (Sku sku : top2SkuList) {
            money += sku.getTotalPrice();
        }

        /**
         * 获取两件商品的名称
         */
        List<String> resultSkuNameList = new ArrayList<>();
        for (Sku sku : top2SkuList) {
            resultSkuNameList.add(sku.getSkuName());
        }

        /**
         * 打印输入结果
         */
        System.out.println(JSON.toJSONString(resultSkuNameList,true));
        System.out.println(JSON.toJSONString("商品总价："+money));

    }

    /**
     * 以Stream流方式实现需求
     */
    @Test
    public void newCartHandler(){
        List<Sku> cartSkuList = CartService.getCartSkuList();
        AtomicReference<Double> money = new AtomicReference<>(Double.valueOf(0.0));
        List<String> resultSkuNameList = cartSkuList.stream()
                /**
                 * 1. 打印商品信息
                 */
                .peek(sku -> System.out.println(JSON.toJSONString(sku,true)))
                /**
                 * 2. 过滤图书类商品
                 */
                .filter(sku -> !SkuCategoryEnum.BOOKS.equals(sku.getSkuCategory()))
                /**
                 * 排序
                 */
                .sorted(Comparator.comparing(Sku::getTotalPrice).reversed())
                /**
                 * Top2
                 */
                .limit(2)
                /**
                 * 累加商品总金额
                 */
                .peek(sku -> money.set(money.get() + sku.getTotalPrice()))
                /**
                 * 获取商品名称
                 */
                .map(sku -> sku.getSkuName())
                /**
                 * 收集结果
                 */
                .collect(Collectors.toList());

        /**
         * 打印输入结果
         */
        System.out.println(JSON.toJSONString(resultSkuNameList,true));
        System.out.println(JSON.toJSONString("商品总价："+money));



    }
}

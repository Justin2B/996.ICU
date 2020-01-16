package lambda.cart;

import java.util.ArrayList;
import java.util.List;

/**
 * 购物车服务类
 */
public class CartService {

    //加入到购物车中的商品信息
    private static List<Sku> cartSkuList=new ArrayList<Sku>(){
        {
            add(new Sku(5235,"无人机",
                    4999.00,1,
                    4999.00,SkuCategoryEnum.ELECTRONICE));

            add(new Sku(5236,"VR一体机",
                    2999.00,1,
                    2999.00,SkuCategoryEnum.ELECTRONICE));

            add(new Sku(5237,"纯色衬衫",
                    355.00,1,
                    355.00,SkuCategoryEnum.CLOTHING));

            add(new Sku(5238,"牛仔裤",
                    599.00,2,
                    599.00,SkuCategoryEnum.CLOTHING));

            add(new Sku(5239,"跑步机",
                    3258.00,2,
                    3258.00,SkuCategoryEnum.SPORTS));

            add(new Sku(52311,"Java编程思想",
                    25.00,3,
                    25.00,SkuCategoryEnum.BOOKS));

            add(new Sku(523512,"Java核心技术",
                    55.00,2,
                    55.00,SkuCategoryEnum.BOOKS));

            add(new Sku(523513,"算法",
                    18.00,2,
                    18.00,SkuCategoryEnum.BOOKS));

            add(new Sku(523514,"进阶指南",
                    85.00,1,
                    85.00,SkuCategoryEnum.BOOKS));

            add(new Sku(523515,"篮球",
                    155.00,2,
                    155.00,SkuCategoryEnum.SPORTS));

            add(new Sku(523516,"羽毛球",
                    25.00,2,
                    25.00,SkuCategoryEnum.SPORTS));

            add(new Sku(523517,"铅球",
                    50.00,1,
                    50.00,SkuCategoryEnum.SPORTS));

        }
    };

    /**
     * 获取商品列表信息
     * @return
     */
    public static List<Sku> getCartSkuList(){
        return cartSkuList;
    }

    /**
     * Version 1.0.0
     * 找出购物车中所有电子产品
     * @param cartSkuList 商品列表
     * @return
     */
    public static List<Sku> filterElectronicsSkus(List<Sku> cartSkuList){
        List<Sku> result = new ArrayList<Sku>();
        for (Sku sku : cartSkuList) {
            //如果商品类型为电子类
            if (SkuCategoryEnum.ELECTRONICE.equals(sku.getSkuCategory())) {
                result.add(sku);
            }
        }
        return result;
    }

    /**
     * Version 2.0.0
     * 根据传入商品类型参数，找出购物车中同种商品类型的商品列表
     * @param cartSkuList
     * @param category
     * @return
     */
    public static List<Sku> filterSkusByCategory(List<Sku> cartSkuList,
                                                 SkuCategoryEnum category){
        List<Sku> result = new ArrayList<Sku>();
        for (Sku sku : cartSkuList) {
            //如果商品类型为传入商品类型
            if (category.equals(sku.getSkuCategory())) {
                result.add(sku);
            }
        }
        return result;
    }

    /**
     * Version 3.0.0
     * 根据不同的Sku判断标准，对Sku列表进行过滤
     * @param cartSkuList
     * @param predicate 不同的Sku判断策略
     * @return
     */
    public static List<Sku> filterSkus(List<Sku> cartSkuList,SkuPredicate predicate){
        List<Sku> result = new ArrayList<Sku>();
        for (Sku sku : cartSkuList) {
            //根据不同的Sku判断标准策略，对Sku进行判断
            if (predicate.test(sku)) {
                result.add(sku);
            }
        }
        return result;
    }



}

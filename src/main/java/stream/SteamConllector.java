package stream;

import com.alibaba.fastjson.JSON;
import lambda.cart.CartService;
import lambda.cart.Sku;
import lambda.cart.SkuCategoryEnum;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 常见预定义收集器使用
 */
public class SteamConllector {

    /**
     * 集合收集器
     */
    @Test
    public void toList(){
        List<Sku> list = CartService.getCartSkuList();
        List<Sku> result = list.stream()
                .filter(sku -> SkuCategoryEnum.BOOKS.equals(sku.getSkuCategory()))
                .collect(Collectors.toList());
        System.out.println(JSON.toJSONString(result,true));
    }

    /**
     * 分组收集器
     */
    @Test
    public void group(){
        List<Sku> list = CartService.getCartSkuList();
        Map<Object,List<Sku>> group = list.stream()
                .collect(Collectors.groupingBy(sku -> sku.getSkuCategory()));
        System.out.println(JSON.toJSONString(group,true));
    }

    /**
     * 分区收集器
     */
    @Test
    public void partition(){
        List<Sku> list = CartService.getCartSkuList();
        Map<Boolean,List<Sku>> partition = list.stream()
                .collect(Collectors.partitioningBy(sku -> SkuCategoryEnum.BOOKS.equals(sku.getSkuCategory())));
        System.out.println(JSON.toJSONString(partition,true));
    }


}

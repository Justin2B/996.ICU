package lambda.cart;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.List;

public class VersionFiveTest {

    @Test
    public void filterSkus(){
        List<Sku> cartSkuList = CartService.getCartSkuList();
        //List<Sku> result = CartService.filterSkus(cartSkuList,(Sku sku)->SkuCategoryEnum.BOOKS.equals(sku.getSkuCategory()));
        List<Sku> result = CartService.filterSkus(cartSkuList,(Sku sku) -> sku.getTotalPrice() > 2000);
        System.out.println(JSON.toJSONString(result,true));
    }
}

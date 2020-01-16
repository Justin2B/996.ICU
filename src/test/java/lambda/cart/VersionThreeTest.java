package lambda.cart;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.List;

public class VersionThreeTest {

    @Test
    public void filterSkus(){
        List<Sku> cartSkuList = CartService.getCartSkuList();
        List<Sku> result = CartService.filterSkus(cartSkuList,new SkuTotalPricePredicate());
        System.out.println(JSON.toJSONString(result,true));
    }
}

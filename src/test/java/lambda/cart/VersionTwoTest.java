package lambda.cart;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.List;

public class VersionTwoTest {

    @Test
    public void filterSkusByCategory(){
        List<Sku> cartSkuList=CartService.getCartSkuList();
        List<Sku> result= CartService.filterSkusByCategory(cartSkuList,SkuCategoryEnum.CLOTHING);
        System.out.println(JSON.toJSONString(result,true));
    }
}

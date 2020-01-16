package lambda.cart;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.List;

public class VersionOneTest {

    @Test
    public void filterElectronicsSkus(){
        List<Sku> cartSkuList=CartService.getCartSkuList();
        List<Sku> result=CartService.filterElectronicsSkus(cartSkuList);
        System.out.println(JSON.toJSONString(result,true));
    }
}

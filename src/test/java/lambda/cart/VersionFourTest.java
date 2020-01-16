package lambda.cart;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.List;

public class VersionFourTest {

    @Test
    public void filterSkus(){
        List<Sku> cartSkuList = CartService.getCartSkuList();
        List<Sku> result = CartService.filterSkus(cartSkuList, new SkuPredicate() {
            @Override
            public boolean test(Sku sku) {
                return SkuCategoryEnum.BOOKS.equals(sku.getSkuCategory());
            }
        });
        System.out.println(JSON.toJSONString(result,true));
    }
}

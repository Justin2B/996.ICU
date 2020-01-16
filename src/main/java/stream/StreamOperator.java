package stream;

import com.alibaba.fastjson.JSON;
import lambda.cart.CartService;
import lambda.cart.Sku;
import lambda.cart.SkuCategoryEnum;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * 流的各种操作
 */
public class StreamOperator {

    List<Sku> list;

    @Before
    public void init(){
        list= CartService.getCartSkuList();
    }

    /**
     * filter使用：过滤掉不符合断言判断的数据
     */
    @Test
    public void filterTest(){
        list.stream()
                //filter
                .filter(sku -> SkuCategoryEnum.BOOKS.equals(sku.getSkuCategory()))
                .forEach(item -> System.out.println(JSON.toJSONString(item,true)));
    }

    /**
     * map使用：将一个元素转换成另一个元素
     */
    @Test
    public void mapTest(){
        list.stream()
                //map
                .map(Sku::getSkuName)
                .forEach(item -> System.out.println(JSON.toJSONString(item,true)));
    }

    /**
     * flatMap使用：将一个对象转换成流
     */
    @Test
    public void flatMapTest(){
        list.stream()
                //flatMap
                .flatMap(sku -> Arrays.stream(sku.getSkuName().split("")))
                .forEach(item -> System.out.println(JSON.toJSONString(item,true)));
    }

    /**
     * peek使用：对流中元素进行便利操作，与forEach类似，但不会销毁流元素
     */
    @Test
    public void peekTest(){
        list.stream()
                //peek
                .peek(sku -> System.out.println(sku.getSkuName()))
                .forEach(item -> System.out.println(JSON.toJSONString(item,true)));
    }

    /**
     * sort使用：对流中元素进行排序，可选择自然排序或指定排序规则，有状态操作
     */
    @Test
    public void sortTest(){
        list.stream()
                .peek(sku -> System.out.println(sku.getSkuName()))
                //sorted
                .sorted(Comparator.comparing(Sku::getTotalPrice))
                .forEach(item -> System.out.println(JSON.toJSONString(item,true)));
    }

    /**
     * distinct使用：对流元素进行去重，有状态操作
     */
    @Test
    public void distinctTest(){
        list.stream()
                .map(Sku::getSkuName)
                //distinct
                .distinct()
                .forEach(item -> System.out.println(JSON.toJSONString(item,true)));
    }

    /**
     * skip使用：跳过前N条记录，有状态操作
     */
    @Test
    public void skipTest(){
        list.stream()
                .sorted(Comparator.comparing(Sku::getTotalPrice))
                //skip
                .skip(3)
                .forEach(item -> System.out.println(JSON.toJSONString(item,true)));
    }

    /**
     * limit使用：截断前N条记录，有状态操作
     */
    @Test
    public void limitTest(){
        list.stream()
                .sorted(Comparator.comparing(Sku::getTotalPrice))
                //limit
                .limit(3)
                .forEach(item -> System.out.println(JSON.toJSONString(item,true)));
    }

    /**
     * allMatch使用：终端操作，短路操作.所有元素匹配返回true
     */
    @Test
    public void allMatchTest(){
         boolean match = list.stream()
                 .peek(sku -> System.out.println(sku.getSkuName()))
                 //allMatch
                .allMatch(sku -> sku.getTotalPrice() > 100);
        System.out.println(match);
    }

    /**
     *anyMatch使用：任何元素匹配返回true
     */
    @Test
    public void anyMatchTest(){
        boolean match = list.stream()
                .peek(sku -> System.out.println(sku.getSkuName()))
                //anyMatch
                .anyMatch(sku -> sku.getTotalPrice() > 100);
        System.out.println(match);
    }

    /**
     * noneMatch使用：任何元素都不匹配返回true
     */
    @Test
    public void noneMatchTest(){
        boolean match = list.stream()
                .peek(sku -> System.out.println(sku.getSkuName()))
                //noneMatch
                .noneMatch(sku -> sku.getTotalPrice() > 10000);
        System.out.println(match);
    }

    /**
     * 找到第一个元素
     */
    @Test
    public void findFirstTest(){
         Optional<Sku> optional = list.stream()
                 //findFirst
                .findFirst();
        System.out.println(JSON.toJSONString(optional,true));
    }

    /**
     * 找到任意一个元素
     */
    @Test
    public void findAnyTest(){
        Optional<Sku> optional = list.stream()
                //findAny
                .findAny();
        System.out.println(JSON.toJSONString(optional,true));
    }

    /**
     * max使用：获取最大值
     */
    @Test
    public void maxTest(){
        OptionalDouble optionalDouble = list.stream()
                //获取总价
                .mapToDouble(Sku::getTotalPrice)
                //max
                .max();
        System.out.println(optionalDouble.getAsDouble());
    }

    /**
     * min使用：获取最小值
     */
    @Test
    public void minTest(){
        OptionalDouble optionalDouble = list.stream()
                //获取总价
                .mapToDouble(Sku::getTotalPrice)
                //min
                .min();
        System.out.println(optionalDouble.getAsDouble());
    }

    /**
     * 获取元素总数
     */
    @Test
    public void countTest(){
        long count = list.stream()
                //count
                .count();
        System.out.println(count);
    }
}

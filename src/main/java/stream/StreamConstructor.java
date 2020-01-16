package stream;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 流的四种构建形式
 */
public class StreamConstructor {

    /**
     * 由数值构建流
     */
    @Test
    public void streamFromValue(){
        Stream<Integer> value = Stream.of(1, 2, 3, 4, 5);
        value.forEach(System.out::println);
    }

    /**
     * 由数组构建流
     */
    @Test
    public void streamFromArray(){
        int[] numbers = {1,2,3,4,5};
        IntStream arrays = Arrays.stream(numbers);
        arrays.forEach(System.out::println);
    }

    /**
     * 由文件构建流
     */
    @Test
    public void streamFromFile() throws IOException {
        Stream<String> file = Files.lines(Paths.get("/home/cbpro/IdeaProjects/cbpro996.ICU/src/main/java/stream/StreamConstructor.java"));
        file.forEach(System.out::println);
    }

    /**
     * 由函数构建流(无限流)
     */
    @Test
    public void streamFromFunction(){
        //生成以0开始，每次递增2的流(每次生成的数基于前一次数的递增)
        Stream stream1 = Stream.iterate(0,n -> n + 2);
        //生成随机数的流(每次生成的数与前一次的数无关)
        Stream stream2 = Stream.generate(Math::random);
        stream1.limit(100).forEach(System.out::println);
        stream2.limit(100).forEach(System.out::println);

    }
}

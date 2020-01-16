package threadPool;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程池提交任务的两种方法
 */
public class ThreadPoolSubmit {

    /**
     * submit
     */
    @Test
    public void submit() throws ExecutionException, InterruptedException {
        //创建线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();

        //使用submit提交任务，并接收返回结果
        Future<Integer> future = threadPool.submit(() ->{
            Thread.sleep(1000L * 5);
            return 2 * 5;
        });

        //阻塞方法，知道任务有返回值后才向下执行
        Integer num = future.get();

        System.out.println(num);
    }

    /**
     * execute
     */
    @Test
    public void execute() throws InterruptedException {
        //创建线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();

        //使用execute提交任务，没有返回结果
        threadPool.execute(() ->{
            try {
                Thread.sleep(1000L * 5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Integer num = 2 * 5;
            System.out.println(num);
        });

        Thread.sleep(1000L * 8);
    }
}

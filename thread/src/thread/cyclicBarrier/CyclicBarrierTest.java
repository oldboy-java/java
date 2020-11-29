package thread.cyclicBarrier;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * Created by Administrator on 2020-6-14.
 */
public class CyclicBarrierTest {
    private static final Integer THREAD_COUNT = 10;
    private static  ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
    private static CyclicBarrier barrier = new CyclicBarrier(THREAD_COUNT);

    public static void main(String[] args) {

        IntStream.range(0, THREAD_COUNT).forEach( i -> {
            executor.execute(()->{

                try {

                    // 模拟线程处理业务
                    System.err.println(Thread.currentThread().getName() + " is doing somethings");
                    //模拟耗时
                    TimeUnit.SECONDS.sleep(i );

                    // 线程都等待，直到所有线程都运行到这步
                    barrier.await();

                    // 模拟并发去做某件事情，譬如获取数据库连接，可以模拟测试并发情况
                    System.err.println(Thread.currentThread().getName()  + "从数据库获取连接.....");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        });
        executor.shutdown();
    }
}

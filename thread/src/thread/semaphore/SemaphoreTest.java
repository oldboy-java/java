package thread.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Created by Administrator on 2019-11-17.
 * 控制并发线程数
 * 30个线程，同一时刻只能有10个线程执行。
 */
public class SemaphoreTest {
    private static  final int THREAD_COUNT = 30;
    private static ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
    private static Semaphore s = new Semaphore(10);

    public static void main(String[] args) {


        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.execute(() -> {
                try {
                    s.acquire(); //获取许可
                    System.out.println(Thread.currentThread().getName() + "---save data");

                    TimeUnit.MINUTES.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    s.release(); //释放许可
                }
            });
        }

        executorService.shutdown();
    }
}

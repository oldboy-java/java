package thread.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Created by Administrator on 2019-11-17.
 * 控制并发线程数,流量控制（限流）
 *
 */
public class SemaphoreTest {
    private static  final int THREAD_COUNT = 30;
    private static ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
    private static Semaphore s = new Semaphore(10);

    public static void main(String[] args) {

//        acquire();
        tryAcquire();
        executorService.shutdown();
    }


    /**
     * 30个线程，同一时刻只能有10个线程执行，其他线程会一直处理阻塞状态，直到获取到令牌
     * 所有等待线程的业务处理都会被执行
     */
    private static void acquire(){
        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.execute(() -> {
                try {
                    s.acquire(); //获取许可（令牌）
                    // 模拟处理业务逻辑
                    System.out.println(Thread.currentThread().getName() + "---save data");
                    TimeUnit.MINUTES.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    // 左后释放令牌
                    s.release(); //释放许可
                }
            });
        }
    }


    /**
     * 30个线程，同一时刻只能有10个线程执行，其他线程会尝试获取许可，等待指定时间后，未获取到令牌
     * 则不再等待，不会一直阻塞
     */
    private static void tryAcquire(){
        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.execute(() -> {
                try {
                    boolean acquire  = s.tryAcquire(1, TimeUnit.MINUTES); //尝试在1秒内去获取许可（令牌）
                    // 获取到许可
                    if (acquire) {
                        // 模拟处理业务逻辑
                        System.out.println(Thread.currentThread().getName() + "---save data");
                        TimeUnit.MINUTES.sleep(1);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    // 左后释放令牌
                    s.release(); //释放许可
                }
            });
        }
    }
}

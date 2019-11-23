package thread.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * Created by Administrator on 2019-11-17.
 */
public class 多个线程同时执行 {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new  Thread(() ->m1(countDownLatch),"T1").start();
        new  Thread(() ->m1(countDownLatch),"T2").start();

        countDownLatch.countDown();
    }


    public static  void  m1(CountDownLatch countDownLatch){
       try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        IntStream.range(0,9).forEach(i ->{
            System.out.println(Thread.currentThread().getName() + "******"+i);
        });
    }
}

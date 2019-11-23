package thread.waitNotify;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2019-11-17.
 */
public class WatiNotify {

    public static volatile  boolean flag = true; //没有物品
    public  static Object lock = new Object();


    public  static void consumer(CountDownLatch countDownLatch ){
        synchronized (lock){
            //条件不满足
            while(flag) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":没有物品可消费，先等等。。");

                    // wait,同时释放lock锁，同时进入等待队列中，此时改线程处于WAITING状态
                    lock.wait();

                    //当producer释放对象锁后，consumer获取到对象锁后从wait方法返回,执行后续动作
                    System.out.println(Thread.currentThread().getName() + "我被通知有物品了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //条件满足，执行业务逻辑
            System.out.println(Thread.currentThread().getName()+"开始获取物品,好好吃一顿。。。");
        }
        countDownLatch.countDown();
    }

    public   static void producer(CountDownLatch countDownLatch ){
        synchronized (lock){
            //没有物品，生产物品
                System.out.println(Thread.currentThread().getName() + ":开始生产一次物品。");

                // 模拟耗时生产
                try {
                    TimeUnit.SECONDS.sleep(120);  // producer处于Timed_WAITING状态，休眠
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

               flag = false;

                //通知
                lock.notifyAll();  //执行通知后，consumer从等待队列中移动到同步队列中，且consumer的状态
                                    //由WAITING -> BLOCKED

                System.out.println(Thread.currentThread().getName() + ":各位小朋友现在有物品了，请注意。。。");
                try {
                    TimeUnit.SECONDS.sleep(120);    // producer再次处于Timed_WAITING状态，休眠
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
        countDownLatch.countDown();
        //方法结束，producer释放对象锁资源，consumer获取到对象锁，并从wait方法返回
    }

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(() ->consumer(countDownLatch),"consumer").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->producer(countDownLatch),"producer").start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            TimeUnit.SECONDS.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


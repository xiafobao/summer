package com.xia.demo.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author fuli
 */
public class VisibilityDemo {
    private CountDownLatch countDownLatch;

    private volatile boolean flag = true;

    public VisibilityDemo(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public void foo(int n) {
        for (int i = 0; i < n; i++) {
            //轮询状态
            while (!flag){

            }
            print("Foo");
            flag = false;
        }
        countDownLatch.countDown();
    }


    public void bar(int n) {
        for (int i = 0; i < n; i++) {
            //轮询状态
            while (flag){

            }
            print("Bar");
            flag = true;

        }
        countDownLatch.countDown();
    }


    private void print(String foo) {
        System.out.print(foo);
    }

    public static void main(String[] args) throws InterruptedException {
        //创建一个对象做为锁实例
        Object object = new Object();
        //用于主线程等待两个子线程执行完成
        CountDownLatch countDownLatch = new CountDownLatch(2);
        VisibilityDemo threadWaitDemo = new VisibilityDemo(countDownLatch);
        //创建线程一并启动
        int n = 15;
        Thread thread1 = new Thread(() -> {
            threadWaitDemo.foo(n);
        });
        thread1.start();
        //创建线程二并启动
        Thread thread2 = new Thread(() -> {
            threadWaitDemo.bar(n);
        });
        thread2.start();
        //阻塞等待其他两个线程执行结束
        countDownLatch.await();

    }


}

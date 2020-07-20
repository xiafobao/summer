package com.xia.demo.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author xiafb
 * @date Created in 2020/7/2 16:32
 * description 线程间通信实例：两个线程顺序交替打印
 * letcode题目：
 * 两个不同的线程将会共用一个 VisibilityDemo 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。
 *
 * 请设计修改程序，以确保 "foobar" 被输出 n 次。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-foobar-alternately
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * modified By
 * version  1.0
 */
public class ThreadWaitDemo {

    private final Object object;
    private CountDownLatch countDownLatch;
    private volatile boolean flag = true;

    public ThreadWaitDemo(Object object, CountDownLatch countDownLatch) {
        this.object = object;
        this.countDownLatch = countDownLatch;
    }

    public void foo(int n){
        for (int i = 0; i < n; i++) {
            //加对象锁
            synchronized (object){
                //轮询状态
                while (!flag){
                    try {
                        //等待
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                print("Foo");
                flag = false;
                //唤醒
                object.notify();
            }

        }
        countDownLatch.countDown();
    }


    public void bar(int n){
        for (int i = 0; i < n; i++) {
            //加对象锁
            synchronized (object){
                //轮询状态
                while (flag){
                    try {
                        //等待
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                print("Bar");
                flag = true;
                //唤醒
                object.notify();
            }

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
        ThreadWaitDemo threadWaitDemo = new ThreadWaitDemo(object, countDownLatch);
        //创建线程一并启动
        Thread thread1 = new Thread(() -> {
            threadWaitDemo.foo(5);
        });
        thread1.start();
        //创建线程二并启动
        Thread thread2 = new Thread(() -> {
            threadWaitDemo.bar(5);
        });
        thread2.start();
        //阻塞等待其他两个线程执行结束
        countDownLatch.await();
    }
}

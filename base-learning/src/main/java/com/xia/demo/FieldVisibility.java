package com.xia.demo;

import java.util.concurrent.TimeUnit;

/**
 * @author xiafb
 * @date Created in 2020/5/29 11:01
 * description 变量的可见
 * modified By
 * version
 */
public class FieldVisibility {

    //可见
    //private volatile static boolean flag = true;

    /**
     * 不可见
     */
    private static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        //创建并启动一个线程
        Thread thread = new Thread(() -> {
            while (flag) {
                //可见
                System.out.println("task a run...");
            }
        }, "线程A");
        thread.start();
        //休眠
        TimeUnit.SECONDS.sleep(1);
        //改变标志状态看线程thread中是否能获取最新状态
        flag = false;
        System.out.println("Main end");
    }

}

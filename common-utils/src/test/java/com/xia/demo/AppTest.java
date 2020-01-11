package com.xia.demo;

import java.awt.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private static boolean flag = true;

    public static void main(String[] args) throws AWTException, InterruptedException {
        Robot robot = new Robot();
        while (true){
            change(robot,  50, 50);
            Thread.sleep(5000);
        }

    }

    private static void change(Robot robot, int x, int y){
        System.out.println("我动了一下。。。");
        Point p = MouseInfo.getPointerInfo().getLocation();
        if(flag){
            x = -x;
            y = -y;
            flag = false;
        }else{
            flag = true;
        }
        int width = (int) p.getX() + x;
        int heigh = (int) p.getY() + y;
        System.out.println("当前坐标x:"+width+"---->y:"+heigh);
        robot.mouseMove(width,heigh);
    }
}

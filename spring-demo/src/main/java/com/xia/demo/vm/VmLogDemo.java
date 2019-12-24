package com.xia.demo.vm;

/**
 * @author xiafb
 * @date Created in 2019/12/23 15:22
 * description
 * modified By
 * version
 */
public class VmLogDemo {

    private static final int _1MB = 1024 * 1024;
    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        //出现一次minor GC
        allocation4 = new byte[4 * _1MB];

    }

    public static void main(String[] args) {
        testAllocation();
    }
}

package com.xia;

import java.util.function.Function;

/**
 * Copyright @ 2019 SHANZHEN fuli Co. Ltd.
 * All right reserved.
 *
 * @Author xiafobao
 * @Date 2019/4/9
 */
public class FunctionTest {
    public static void main(String[] args) {
        System.out.println(demo2(2));
    }


    private static String demo1(Integer a){
        /**
         * Function接口接收一个参数，并返回单一的结果。
         */
        Function<Integer, String> join = FunctionTest::formateStr;
        return join.apply(a);
    }

    private static String formateStr(Integer a){
        String val = "";
        if (a == -1) {
            val = "小于";
        } else if (a == 0) {
            val = "等于";
        } else if (a == 1) {
            val = "大于";
        }
        return val;
    }

    private static Integer demo2(Integer a){
        Function<Integer, Integer> join1 = i -> i*2;
        Function<Integer, Integer> join2 = i -> i*i;
        /**
         * 先执行compose(join1)中的函数，然后将结果放入join2中计算
         */
        return join2.compose(join1).apply(a);
    }
}

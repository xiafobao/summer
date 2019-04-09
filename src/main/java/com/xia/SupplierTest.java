package com.xia;

import org.apache.commons.lang3.RandomUtils;

import java.util.function.Supplier;

/**
 * Copyright @ 2019 SHANZHEN fuli Co. Ltd.
 * All right reserved.
 *
 * @Author xiafobao
 * @Date 2019/4/9
 */
public class SupplierTest {
    public static void main(String[] args) {

        /**
         * Supplier接口产生一个给定类型的结果。Supplier没有输入参数。
         */
        Supplier<Long> result = RandomUtils::nextLong;
        System.out.println(result.get());
    }

}

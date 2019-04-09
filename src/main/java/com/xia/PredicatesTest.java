package com.xia;

import org.apache.commons.lang3.StringUtils;

import java.util.function.Predicate;

/**
 * Copyright @ 2019 SHANZHEN fuli Co. Ltd.
 * All right reserved.
 *
 * @Author xiafobao
 * @Date 2019/4/9
 */
public class PredicatesTest {
    public static void main(String[] args) {
        System.out.println(isWithSpaceAndNotNull("aaaaa   bbbb"));
    }

    private static boolean isWithSpaceAndNotNull(String val){
        /**
         * Predicate是一个布尔类型的函数，该函数只有一个输入参数。
         */
        Predicate<String> stringNotNull = StringUtils::isNoneBlank;
        Predicate<String> and = stringNotNull.and(StringUtils::containsWhitespace);
        return and.test(val);
    }
}

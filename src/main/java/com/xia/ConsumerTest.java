package com.xia;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Copyright @ 2019 SHANZHEN fuli Co. Ltd.
 * All right reserved.
 *
 * @Author xiafobao
 * @Date 2019/4/9
 */
public class ConsumerTest {
    public static void main(String[] args) {
        /**
         * Consumer代表了在一个输入参数上需要进行的操作。
         */
        Consumer<Map<String, Object>> consumer1 = e -> System.out.println("hello -----> " + e.get("a"));
        Consumer<Map<String, Object>> consumer2 = consumer1.andThen(e -> System.out.println("other -----> " + e.get("b")));
        Map<String, Object> params = new HashMap<>();
        params.put("a", "张三");
        params.put("b", "李氏");
        consumer2.accept(params);
    }
}

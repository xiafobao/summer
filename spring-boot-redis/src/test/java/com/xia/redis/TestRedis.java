package com.xia.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author xiafb
 * @date Created in 2020/3/12 17:18
 * description
 * modified By
 * version
 */
@SpringBootTest
public class TestRedis {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void setValueKey() {
        Boolean a = redisTemplate.opsForValue().setIfAbsent("a", "hello word!");
        if(a){
            System.out.println("保存成功!");
        }else{
            System.out.println("保存失败!");
        }
    }

    @Test
    public void getValueKey() {
        String a = (String) redisTemplate.opsForValue().get("a");
        System.out.println("取出值为："+a);

    }

    @Test
    public void setList() {

        Long aLong2 = redisTemplate.opsForList().leftPush("list:a", "张三2");
        Long aLong3 = redisTemplate.opsForList().leftPush("list:a", "张三3");
        Long aLong4 = redisTemplate.opsForList().leftPush("list:a", "张三4");
        for (int a = 0; a < 3; a++){
            Long aLong1 = redisTemplate.opsForList().leftPush("list:a", "张三"+a);
            System.out.println("返回值："+aLong1);
        }
        while (true) {
            Object o = redisTemplate.opsForList().rightPop("list:a");
            System.out.println("列表中取出的值：" + o);
        }
        //System.out.println("已取完");
    }
}

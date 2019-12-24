package com.xia.demo;

import com.xia.demo.utils.BeanCopierUtils;
import com.xia.demo.vo.User;
import com.xia.demo.vo.UserPro;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        User user = new User();
        user.setName("张三");
        user.setSex("男");
        UserPro userPro = BeanCopierUtils.copy(user, UserPro.class, e -> {
            e.setDesc("这是备注");
        });
        System.out.println(userPro.toString());
    }
}
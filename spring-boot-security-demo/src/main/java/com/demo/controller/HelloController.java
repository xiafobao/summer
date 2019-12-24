package com.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiafb
 * @date Created in 2019/7/26 17:33
 * description
 * modified By
 * version
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }


    public static void main(String[] args) {
        System.out.println(Integer.SIZE);
        System.out.println(Integer.SIZE-3);
        System.out.println(-1 << (Integer.SIZE - 3));
        System.out.println(1 << (Integer.SIZE - 3) - 1);
    }
}

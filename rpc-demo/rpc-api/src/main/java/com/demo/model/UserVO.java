package com.demo.model;

import java.io.Serializable;

/**
 * @author xiafb
 * @date Created in 2019/7/26 14:42
 * description
 * modified By
 * version
 */

public class UserVO implements Serializable {

    private static final long serialVersionUID = 7691196072390593120L;

    private String userNo;

    private String userName;

    private int age;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "UserNo='" + userNo + '\'' +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}

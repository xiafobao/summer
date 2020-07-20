package com.demo.enums;

/**
 * @author xiafb
 * @date Created in 2020/5/19 10:51
 * description 用户状态
 * modified By
 * version  1.0
 */
public enum UserStatusEnum {
    /**
     * PROHIBIT：禁用
     */
    PROHIBIT("PROHIBIT", "禁用"),

    /**
     * NORMAL：正常
     */
    NORMAL("NORMAL", "正常"),

    ;

    private String code, name;

    UserStatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

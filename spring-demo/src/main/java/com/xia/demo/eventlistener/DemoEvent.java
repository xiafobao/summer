package com.xia.demo.eventlistener;

import org.springframework.context.ApplicationEvent;

/**
 * @author xiafb
 * @date Created in 2019/12/24 15:43
 * description 被监听类
 * modified By
 * version
 */
public class DemoEvent extends ApplicationEvent {

    private static final long serialVersionUID = -2385671831440006146L;

    private String msg;

    public DemoEvent(Object source) {
        super(source);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "DemoEvent{" +
                "msg='" + msg + '\'' +
                '}';
    }
}

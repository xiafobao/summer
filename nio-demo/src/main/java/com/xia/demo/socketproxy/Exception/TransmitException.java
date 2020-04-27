package com.xia.demo.socketproxy.Exception;

/**
 * 定义传输异常
 * @author xiafb
 */
public class TransmitException extends Exception{
    private static final long serialVersionUID = -1726855635725234192L;

    public TransmitException() {
    }
    public TransmitException(String msg) {
        super(msg);
    }
}

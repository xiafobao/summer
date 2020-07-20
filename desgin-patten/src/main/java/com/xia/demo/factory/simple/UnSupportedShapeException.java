package com.xia.demo.factory.simple;

/**
 * @author xiafb
 * @date Created in 2020/5/4 15:54
 * description 未知图形异常
 * modified By
 * version  1.0
 */
public class UnSupportedShapeException extends RuntimeException{
    private static final long serialVersionUID = 2828109622365611014L;

    public UnSupportedShapeException(String message) {
        super(message);
    }

    public UnSupportedShapeException(String message, Throwable cause) {
        super(message, cause);
    }
}

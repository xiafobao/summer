package com.xia.demo.socketproxy.Exception;

/**
 * 定义鉴权异常
 * @author xiafb
 */
public class AuthticationException extends Exception{
    private static final long serialVersionUID = 1276050403113940671L;

    public AuthticationException(){
    }
    public AuthticationException(String msg){
        super(msg);
    }
}

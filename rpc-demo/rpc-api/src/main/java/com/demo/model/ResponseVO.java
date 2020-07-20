package com.demo.model;

import java.io.Serializable;

/**
 * @author xiafb
 * @date Created in 2019/7/26 16:28
 * description
 * modified By
 * version
 */
public class ResponseVO<T> implements Serializable {

    private static final String SUCCESS = "success";

    private static final String FAIL = "fail";
    private static final long serialVersionUID = -3400385033673238972L;

    private String code;

    private String status;

    private T data;

    private String msg;

    public ResponseVO(){

    }

    public ResponseVO(String status, T data) {
        this.status = status;
        this.data = data;
    }

    public ResponseVO(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ResponseVO(String code, String status, T data, String msg) {
        this.code = code;
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    public static <T> ResponseVO<T> success(T data){
        return new ResponseVO<>(SUCCESS, data);
    }

    public static <T> ResponseVO<T> error(String msg){
        return new ResponseVO<>(FAIL, msg);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResponseVO{" +
                "code='" + code + '\'' +
                ", status='" + status + '\'' +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}

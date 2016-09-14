package com.demo.base.Exception;

/**
 * Created by Administrator on 2016/9/13.
 */
public class DemoException extends Exception{
    private String errorCode;

    public DemoException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public DemoException(String message) {
        super(message);
    }
}

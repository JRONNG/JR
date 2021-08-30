package com.jr.gitdemo.common;

/**
 * Service 异常
 * @author wujiangwei
 * @date 2019/5/15 11:08
 */
public class ServiceException extends Exception{
    public ServiceException(String message){
        super(message);
    }
}

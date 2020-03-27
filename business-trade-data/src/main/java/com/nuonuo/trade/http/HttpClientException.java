package com.nuonuo.trade.http;

import lombok.Getter;

/**
 * @program: dzfp
 * @description: http连接异常
 * @author: liujiajun
 * @create: 2019-12-20 14:41
 */
public class HttpClientException extends Exception {
    @Getter
    private String msg;
    @Getter
    private Throwable ex;

    public HttpClientException(String msg,Throwable t){
        this.msg = msg;
        this.ex = t;
    }

}

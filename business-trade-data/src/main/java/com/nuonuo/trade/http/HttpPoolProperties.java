package com.nuonuo.trade.http;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 连接池参数
 */

@Component
@ConfigurationProperties(prefix = "http.pool")
public class HttpPoolProperties {

    /**
     * 整个连接池最大连接数
     */
    private static int maxTotal = 200;

    /**
     * 每路由最大连接数
     */
    private static int maxPerRoute = 20;

    /**
     * socket读超时
     */
    private static int socketTimeout = 10000;

    /**
     * socket连接超时
     */
    private static int connectTimeout = 5000;

    /**
     * 获取连接超时
     */
    private static int requestTimeout = 2000;

    public void setSocketTimeout(int socketTimeout) {
        HttpPoolProperties.socketTimeout = socketTimeout;
    }

    public void setMaxTotal(int maxTotal){
        HttpPoolProperties.maxTotal=maxTotal;
    }

    public void setMaxPerRoute(int maxPerRoute){
        HttpPoolProperties.maxPerRoute = maxPerRoute;
    }

    public void setConnectTimeout(int connectTimeout) {
        HttpPoolProperties.connectTimeout = connectTimeout;
    }

    public void setRequestTimeout(int requestTimeout) {
        HttpPoolProperties.requestTimeout = requestTimeout;
    }

    public static int getMaxTotal(){
        return maxTotal;
    }

    public static int getMaxPerRoute(){
        return maxPerRoute;
    }

    public static int getSocketTimeout() {
        return socketTimeout;
    }

    public static int getRequestTimeout() {
        return requestTimeout;
    }

    public static int getConnectTimeout() {
        return connectTimeout;
    }

}

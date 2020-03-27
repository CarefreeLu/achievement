package com.nuonuo.trade.exception;

/**
 * 功能描述：TODO
 *
 * @author Jianhui Lu
 * @createtime 2019/3/9 10:39
 */
public class ElinRuntimeException extends RuntimeException implements IElinException{

    private static final long serialVersionUID = -2370889915172046686L;
    private static final String DEFAULT_CODE = "9999";


    /**
     * 路由表空间不足
     */
    public static final String ROUTE_SPACE_NOT_ENOUGH = "5000";

    /**
     * 描述:通常用于异常码传递
     *
     */
    private String code;

    /**
     * 描述:异常数据传递，主要用于日志记录
     *
     */
    private Object data;

    public ElinRuntimeException(String message) {
        super(message);
    }

    public ElinRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ElinRuntimeException(Throwable cause) {
        super(cause);
    }

    public ElinRuntimeException(String code, String message) {
        super(message);
        this.code = code;
    }

    public ElinRuntimeException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ElinRuntimeException(String code, String message, Object data, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.data = data;
    }

    public String getCode() {
        return code == null? DEFAULT_CODE : code;
    }

    public Object getData() {
        return data;
    }
}

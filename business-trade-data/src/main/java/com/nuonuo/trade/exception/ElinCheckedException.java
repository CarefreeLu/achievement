package com.nuonuo.trade.exception;


/**
 * 功能描述：TODO
 *
 * @author Jianhui Lu
 * @createtime 2019/3/9 10:15
 */
public class ElinCheckedException extends Exception implements IElinException{

    private static final long serialVersionUID = 8991147363331161462L;
    private static final String DEFAULT_CODE = "9999";

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

    public ElinCheckedException(String message) {
        super(message);
    }

    public ElinCheckedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ElinCheckedException(Throwable cause) {
        super(cause);
    }

    public ElinCheckedException(String code, String message) {
        super(message);
        this.code = code;
    }

    public ElinCheckedException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ElinCheckedException(String code, String message, Object data, Throwable cause) {
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

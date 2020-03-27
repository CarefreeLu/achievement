package com.nuonuo.trade.exception;

import com.nuonuo.trade.constant.BusinessTradeErrorE;

/**
 * 类描述：行业交易服务异常处理类
 *
 * @author Jianhui Lu
 * @date 2019/8/5 17:15
 */
public class BusinessTradeException extends ElinCheckedException
{
    public BusinessTradeException(String message)
    {
        super(message);
    }

    public BusinessTradeException(BusinessTradeErrorE errorE)
    {
        super(errorE.code, errorE.description);
    }

    public BusinessTradeException(String code, String msg)
    {
        super(code, msg);
    }

    public BusinessTradeException(Throwable cause)
    {
        super(cause);
    }
}

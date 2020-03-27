package com.nuonuo.trade.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.nuonuo.trade.exception.IElinException;

/**
 * 类描述：响应报文对象（前端）
 *
 * @author Jianhui Lu
 * @date 2019/8/15 14:30
 */
public class ResponseModeView<T> extends ResponseMode<T>
{

    @JSONField(name = "status", ordinal = 1)
    @Override
    public String getResultCode()
    {
        return super.getResultCode();
    }

    @JSONField(name = "msg", ordinal = 2)
    @Override
    public String getResultMsg()
    {
        return super.getResultMsg();
    }

    @JSONField(name = "data", ordinal = 3)
    @Override
    public T getResultBody()
    {
        return super.getResultBody();
    }

    public static <T> ResponseModeView<T> buildSuccessResponse(String resultMsg, T resultBody)
    {
        ResponseModeView<T> responseMode = new ResponseModeView<>();
        responseMode.setResultMsg(resultMsg);
        responseMode.setResultBody(resultBody);
        return responseMode;
    }

    public static ResponseModeView buildExceptionResponse(IElinException e)
    {
        ResponseModeView responseMode = new ResponseModeView();
        responseMode.setResultCode(e.getCode());
        responseMode.setResultMsg(e.getMessage());
        return responseMode;
    }

    public static ResponseModeView buildSuccessResponse(String resultMsg)
    {
        ResponseModeView responseMode = new ResponseModeView();
        responseMode.setResultMsg(resultMsg);
        return responseMode;
    }
}

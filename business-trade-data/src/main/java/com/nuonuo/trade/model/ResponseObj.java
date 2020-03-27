package com.nuonuo.trade.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.nuonuo.trade.exception.IElinException;
import lombok.Data;

/**
 * 类描述：响应结果对象
 *
 * @author Jianhui Lu
 * @date 2019/4/2 14:22
 */
@Data
public class ResponseObj<T>
{
    private static final String UNIFICATION_RESPONSE_SUCCESS_CODE = "0000";
    /**
     * 响应结果码
     */
    @JSONField(ordinal = 1)
    private String resultCode = UNIFICATION_RESPONSE_SUCCESS_CODE;

    /**
     * 响应结果信息
     */
    @JSONField(ordinal = 2)
    private String resultMsg;

    /**
     * 响应结果体
     */
    @JSONField(ordinal = 3)
    private T resultBody;

    public static ResponseObj buildExceptionResponse(IElinException e)
    {
        ResponseObj responseMode = new ResponseObj();
        responseMode.setResultCode(e.getCode());
        responseMode.setResultMsg(e.getMessage());
        return responseMode;
    }

    public static ResponseObj buildResponse(String resultCode, String resultMsg)
    {
        ResponseObj responseMode = new ResponseObj();
        responseMode.setResultCode(resultCode);
        responseMode.setResultMsg(resultMsg);
        return responseMode;
    }

    public static ResponseObj buildSuccessResponse(String resultMsg)
    {
        ResponseObj responseMode = new ResponseObj();
        responseMode.setResultMsg(resultMsg);
        return responseMode;
    }

    public static <T> ResponseObj<T> buildSuccessResponse(String resultMsg, T resultBody)
    {
        ResponseObj<T> responseMode = new ResponseObj();
        responseMode.setResultMsg(resultMsg);
        responseMode.setResultBody(resultBody);
        return responseMode;
    }

    @JSONField(serialize = false)
    public boolean isSuccess()
    {
        return UNIFICATION_RESPONSE_SUCCESS_CODE.equals(resultCode);
    }
}

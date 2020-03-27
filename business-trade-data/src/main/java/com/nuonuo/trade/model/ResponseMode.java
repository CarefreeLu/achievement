package com.nuonuo.trade.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.nuonuo.trade.exception.IElinException;
import lombok.Data;

/**
 * 类描述：TODO
 *
 * @author Jianhui Lu
 * @date 2019/8/8 15:38
 */
@Data
public class ResponseMode<T>
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

    public static ResponseMode buildExceptionResponse(IElinException e)
    {
        ResponseMode responseMode = new ResponseMode();
        responseMode.setResultCode(e.getCode());
        responseMode.setResultMsg(e.getMessage());
        return responseMode;
    }

    public static ResponseMode buildResponse(String resultCode, String resultMsg)
    {
        ResponseMode responseMode = new ResponseMode();
        responseMode.setResultCode(resultCode);
        responseMode.setResultMsg(resultMsg);
        return responseMode;
    }

    public static ResponseMode buildSuccessResponse(String resultMsg)
    {
        ResponseMode responseMode = new ResponseMode();
        responseMode.setResultMsg(resultMsg);
        return responseMode;
    }

    public static <T> ResponseMode<T> buildSuccessResponse(String resultMsg, T resultBody)
    {
        ResponseMode<T> responseMode = new ResponseMode();
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

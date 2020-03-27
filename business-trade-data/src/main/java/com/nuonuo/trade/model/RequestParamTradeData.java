package com.nuonuo.trade.model;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.nuonuo.trade.constant.BusinessPlatformE;
import com.nuonuo.trade.constant.BusinessTypeE;
import com.nuonuo.trade.constant.OperationE;
import com.nuonuo.trade.constant.TradeDataPlatformE;
import com.nuonuo.trade.util.CryptoUtils;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * 类描述：TODO
 *
 * @author Jianhui Lu
 * @date 2019/8/6 14:11
 */
@Data
public class RequestParamTradeData
{
    /**
     * 交易订单号
     */
    private String tradeNo;
    /**
     * 企业税号
     */
    private String taxNo;
    /**
     * 行业类型
     */
    @JSONField(serialize = false)
    private String businessType;
    /**
     * 交易数据所属平台
     */
    private String businessPlatformId;
    /**
     * 身份ID
     */
    @JSONField(serialize = false)
    private String identityId;
    /**
     * 身份来源（目前指微信或支付宝）
     */
    @JSONField(serialize = false)
    private String identitySource;
    /**
     * 交易数据索引表ID
     */
    @JSONField(serialize = false)
    private String tradeDataId;
    /**
     * 操作（交易相关的操作）
     */
    @JSONField(serialize = false)
    private String operation;
    /**
     * 操作流水号
     */
    @JSONField(serialize = false)
    private String operationSerialNo;
    /**
     * 页对象
     */
    @JSONField(serialize = false)
    private Page page;
    /**
     * 订单号加密状态（默认true 加密）
     */
    @JSONField(serialize = false)
    private Boolean encryptStatus = true;

    @JSONField(serialize = false)
    private BusinessTypeE businessTypeE;

    @JSONField(serialize = false)
    private TradeDataPlatformE tradeDataPlatformE;

    @JSONField(serialize = false)
    private OperationE operationE;

    @JSONField(serialize = false)
    private String secretKey;

    @JSONField(serialize = false)
    public String getDecryptTradeNo()
    {
        if (StringUtils.isNotBlank(taxNo))
        {
            try
            {
                return CryptoUtils.decrypt(tradeNo, secretKey);
            }
            catch (Exception e)
            {
                //不处理
            }
        }
        return tradeNo;
    }

    public BusinessTypeE getBusinessTypeE()
    {
        if (businessTypeE == null)
        {
            businessTypeE = BusinessTypeE.getBusinessTypeE(businessType);
        }
        return businessTypeE;
    }

    public TradeDataPlatformE getTradeDataPlatformE()
    {
        if (tradeDataPlatformE == null)
        {
            tradeDataPlatformE = TradeDataPlatformE.getTradeDataPlatformE(businessPlatformId);
        }
        return tradeDataPlatformE;
    }

    public OperationE getOperationE()
    {
        if (operationE == null)
        {
            operationE = OperationE.getOperationE(operation);
        }
        return operationE;
    }

    @JSONField(serialize = false)
    public BusinessPlatformE getBusinessPlatformE()
    {
        return BusinessPlatformE.getBusinessPlatformE(getBusinessTypeE(), getTradeDataPlatformE());
    }

    public Page getPage()
    {
        return page == null ? new Page() : page;
    }

    public static void main(String[] args)
    {
        RequestParamTradeData requestParamTradeData = new RequestParamTradeData();
        System.out.println(requestParamTradeData.getBusinessTypeE());
        System.out.println(requestParamTradeData.getTradeDataPlatformE());
        System.out.println(requestParamTradeData.getBusinessPlatformE());
        requestParamTradeData.setBusinessType("001");
        requestParamTradeData.setBusinessPlatformId("001");
        requestParamTradeData.setTradeNo("21");
        requestParamTradeData.setTaxNo("twt");
        System.out.println(JSONObject.toJSONString(requestParamTradeData));
        String str = "{\"businessPlatformId\":\"2\",\"businessType\":\"1\",\"tradeNo\":\"3\"}";
        str = "{\"businessPlatformId\":\"001\",\"encryptStatus\":false,\"identityId\":\"8888888888888\",\"identitySource\":\"01\",\"page\":{\"currentPage\":3,\"pageSize\":9},\"taxNo\":\"339901999999142\",\"tradeNo\":\"a7039a6703bc257d595bfc6b83a20f23631b34c03f043431\"}";
        requestParamTradeData = JSONObject.parseObject(str, RequestParamTradeData.class);
        System.out.println(requestParamTradeData);
        System.out.println(JSONObject.toJSONString(requestParamTradeData));

    }
}

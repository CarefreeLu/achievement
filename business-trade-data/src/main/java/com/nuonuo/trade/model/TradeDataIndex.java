package com.nuonuo.trade.model;

import lombok.Data;

/**
 * 类描述：TODO
 *
 * @author Jianhui Lu
 * @date 2019/8/16 10:53
 */
@Data
public class TradeDataIndex
{
    /**
     * 交易订单号
     */
    private String tradeNo;
    /**
     * 行业类型
     */
    private String businessType;
    /**
     * 平台ID(交易所属行业平台身份)
     */
    private String businessPlatformId;
    /**
     * 身份ID(归属人/企业身份ID)
     */
    private String identityId;
    /**
     * 身份来源(微信/支付宝/诺诺)
     */
    private String identitySource;
}

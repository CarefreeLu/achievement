package com.nuonuo.trade.entity;

import lombok.Data;
import lombok.ToString;

/**
 * 类描述：交易数据索引对象
 *
 * @author Jianhui Lu
 * @date 2019/8/6 14:03
 */
@Data
@ToString
public class TradeDataIndexDB
{
    /**
     * 唯一键
     */
    private String id;
    /**
     * tradeDataIndex对象主键
     */
    private String tradeDataIndexId;
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

    private UniKey uniKey;

    public UniKey getUniKey()
    {
        if (uniKey == null)
        {
            uniKey = new UniKey();
        }

        uniKey.setTradeNo(this.tradeNo);
        uniKey.setBusinessPlatformId(this.businessPlatformId);
        uniKey.setBusinessType(this.businessType);

        return uniKey;
    }

    @Data
    public class UniKey
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
    }
}

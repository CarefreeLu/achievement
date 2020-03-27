package com.nuonuo.trade.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 类描述：行业交易数据对象
 *
 * @author Jianhui Lu
 * @date 2019/8/5 13:45
 */
@Data
public class BusinessTradeData<T>
{
    /**
     * 交易所属行业类型
     */
    @JSONField(ordinal = 1)
    private String businessType;
    /**
     * 交易所属行业平台ID
     */
    @JSONField(ordinal = 2)
    private String businessPlatformId;
    /**
     * 交易所属行业数据对象
     */
    @JSONField(ordinal = 3)
    private T businessTradeData;
    /**
     * 加解密类型
     */
    @JSONField(ordinal = 5)
    private String cipherType;
    /**
     * 交易数据关联的税号
     */
    @JSONField(ordinal = 6)
    private String taxNo;
}

package com.nuonuo.trade.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * 类描述：行业交易数据集对象
 *
 * @author Jianhui Lu
 * @date 2019/8/15 19:01
 */
@Data
public class BusinessTradeDataSet<T>
{
    /**
     * 行业类型
     */
    @JSONField(ordinal = 1)
    private String businessType;
    /**
     * 交易数据所属平台
     */
    @JSONField(ordinal = 2)
    private String businessPlatformId;
    /**
     * 身份ID
     */
    @JSONField(ordinal = 3)
    private String identityId;
    /**
     * 身份来源（目前指微信或支付宝）
     */
    @JSONField(ordinal = 4)
    private String identitySource;
    /**
     * 交易数据总数
     */
    @JSONField(ordinal = 5)
    private int tradeDataTotal;
    /**
     * 交易所属行业数据对象
     */
    @JSONField(ordinal = 6)
    private List<T> tradeDataSet;
}

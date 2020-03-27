package com.nuonuo.trade.entity;

import lombok.Data;
import lombok.ToString;

/**
 * 类描述：TODO
 *
 * @author Jianhui Lu
 * @date 2019/8/14 10:04
 */
@Data
@ToString
public class TradeDataOperationIndexDB
{
    /**
     * 唯一键
     */
    private String id;
    /**
     * 交易数据索引表ID
     */
    private String tradeDataIndexId;
    /**
     * 操作对应流水
     */
    private String operationSerialNo;
}

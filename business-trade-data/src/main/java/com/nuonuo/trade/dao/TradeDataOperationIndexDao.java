package com.nuonuo.trade.dao;

import com.nuonuo.trade.entity.TradeDataOperationIndexDB;

/**
 * 功能描述：TODO
 *
 * @author Jianhui Lu
 * @createtime 2019/8/14 10:02
 */
public interface TradeDataOperationIndexDao
{
    int save(String tableName, TradeDataOperationIndexDB tradeDataOperationIndexDB);
}

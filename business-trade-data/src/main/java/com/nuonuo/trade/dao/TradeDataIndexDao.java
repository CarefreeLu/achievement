package com.nuonuo.trade.dao;

import com.nuonuo.trade.entity.TradeDataIndexDB;

/**
 * 类描述：TODO
 *
 * @author Jianhui Lu
 * @date 2019/8/6 14:01
 */
public interface TradeDataIndexDao
{
    int save(TradeDataIndexDB tradeDataIndexDB);

    TradeDataIndexDB getByUniKey(TradeDataIndexDB.UniKey uniKey);

    int countTradeData(TradeDataIndexDB tradeDataIndexDB);
}

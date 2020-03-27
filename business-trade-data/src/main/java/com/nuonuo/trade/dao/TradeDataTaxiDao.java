package com.nuonuo.trade.dao;

import com.nuonuo.trade.entity.TradeDataIndexDB;
import com.nuonuo.trade.entity.TradeDataTaxiDB;

import java.util.List;

/**
 * 类描述：TODO
 *
 * @author Jianhui Lu
 * @date 2019/8/6 11:23
 */
public interface TradeDataTaxiDao
{
    int save(TradeDataTaxiDB tradeDataTaxiDB);

    TradeDataTaxiDB getByUniKey(TradeDataTaxiDB.UniKey uniKey);

    List<TradeDataTaxiDB> getTradeDataTaxiDBList(TradeDataIndexDB tradeDataIndex, int offset, int size);
}

package com.nuonuo.trade.mapper;

import com.nuonuo.trade.entity.TradeDataIndexDB;
import com.nuonuo.trade.entity.TradeDataTaxiDB;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类描述：TODO
 *
 * @author Jianhui Lu
 * @date 2019/8/6 13:57
 */
public interface TradeDataTaxiMapper
{
    int insert(TradeDataTaxiDB tradeDataTaxiDB);

    int update(TradeDataTaxiDB tradeDataTaxiDB);

    TradeDataTaxiDB getByUniKey(@Param("UniKey") TradeDataTaxiDB.UniKey uniKey);

    List<TradeDataTaxiDB> getTradeDataTaxiDBList(@Param("record") TradeDataIndexDB tradeDataIndex, @Param("offset") int offset, @Param("size") int size);
}

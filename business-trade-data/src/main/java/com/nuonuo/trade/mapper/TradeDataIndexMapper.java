package com.nuonuo.trade.mapper;

import com.nuonuo.trade.entity.TradeDataIndexDB;
import org.apache.ibatis.annotations.Param;

/**
 * 类描述：TODO
 *
 * @author Jianhui Lu
 * @date 2019/8/6 16:50
 */
public interface TradeDataIndexMapper
{
    int save(TradeDataIndexDB tradeDataIndexDB);

    TradeDataIndexDB getByUniKey(@Param("UniKey") TradeDataIndexDB.UniKey uniKey);

    int countTradeData(TradeDataIndexDB tradeDataIndexDB);
}

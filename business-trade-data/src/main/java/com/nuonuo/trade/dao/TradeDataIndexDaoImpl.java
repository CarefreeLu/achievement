package com.nuonuo.trade.dao;

import com.nuonuo.trade.entity.TradeDataIndexDB;
import com.nuonuo.trade.mapper.TradeDataIndexMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 类描述：TODO
 *
 * @author Jianhui Lu
 * @date 2019/8/6 14:01
 */
@Repository
public class TradeDataIndexDaoImpl implements TradeDataIndexDao
{
    @Autowired
    TradeDataIndexMapper tradeDataIndexMapper;

    @Override
    public int save(TradeDataIndexDB tradeDataIndexDB)
    {
        return tradeDataIndexMapper.save(tradeDataIndexDB);
    }

    @Override
    public TradeDataIndexDB getByUniKey(TradeDataIndexDB.UniKey uniKey)
    {
        return tradeDataIndexMapper.getByUniKey(uniKey);
    }

    @Override
    public int countTradeData(TradeDataIndexDB tradeDataIndexDB)
    {
        return tradeDataIndexMapper.countTradeData(tradeDataIndexDB);
    }
}

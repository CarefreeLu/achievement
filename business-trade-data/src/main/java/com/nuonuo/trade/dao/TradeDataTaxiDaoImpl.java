package com.nuonuo.trade.dao;

import com.nuonuo.trade.entity.TradeDataIndexDB;
import com.nuonuo.trade.entity.TradeDataTaxiDB;
import com.nuonuo.trade.mapper.TradeDataTaxiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 类描述：出租车交易数据Dao实现
 *
 * @author Jianhui Lu
 * @date 2019/8/6 13:58
 */
@Repository
public class TradeDataTaxiDaoImpl implements TradeDataTaxiDao
{
    @Autowired
    private TradeDataTaxiMapper tradeDataTaxiMapper;

    @Override
    public int save(TradeDataTaxiDB tradeDataTaxiDB)
    {
        try
        {
            return tradeDataTaxiMapper.insert(tradeDataTaxiDB);
        }
        catch (DuplicateKeyException e)
        {
            return tradeDataTaxiMapper.update(tradeDataTaxiDB);
        }
    }

    @Override
    public TradeDataTaxiDB getByUniKey(TradeDataTaxiDB.UniKey uniKey)
    {
        return tradeDataTaxiMapper.getByUniKey(uniKey);
    }

    @Override
    public List<TradeDataTaxiDB> getTradeDataTaxiDBList(TradeDataIndexDB tradeDataIndex, int offset, int size)
    {
        return tradeDataTaxiMapper.getTradeDataTaxiDBList(tradeDataIndex, offset, size);
    }

    public static void main(String[] args)
    {
        System.out.println(TradeDataTaxiDB.class.getName());
    }
}

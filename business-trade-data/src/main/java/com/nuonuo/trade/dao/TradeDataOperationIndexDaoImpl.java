package com.nuonuo.trade.dao;

import com.nuonuo.trade.entity.TradeDataOperationIndexDB;
import com.nuonuo.trade.mapper.TradeDataOperationIndexMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 类描述：TODO
 *
 * @author Jianhui Lu
 * @date 2019/8/14 10:11
 */
@Repository
public class TradeDataOperationIndexDaoImpl implements TradeDataOperationIndexDao
{
    @Autowired
    private TradeDataOperationIndexMapper mapper;

    @Override
    public int save(String tableName, TradeDataOperationIndexDB tradeDataOperationIndexDB)
    {
        return mapper.save(tableName, tradeDataOperationIndexDB);
    }
}

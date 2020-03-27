package com.nuonuo.trade.mapper;

import com.nuonuo.trade.entity.TradeDataOperationIndexDB;
import org.apache.ibatis.annotations.Param;

/**
 * 功能描述：TODO
 *
 * @author Jianhui Lu
 * @createtime 2019/8/14 10:02
 */
public interface TradeDataOperationIndexMapper
{
    int save(@Param("tableName") String tableName, @Param("record") TradeDataOperationIndexDB tradeDataOperationIndexDB);
}

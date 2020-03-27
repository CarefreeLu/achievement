package com.nuonuo.trade.service;

import com.nuonuo.trade.exception.BusinessTradeException;
import com.nuonuo.trade.model.BusinessTradeData;
import com.nuonuo.trade.model.BusinessTradeDataSet;
import com.nuonuo.trade.model.RequestParamTradeData;

/**
 * 功能描述：交易数据服务接口
 *
 * @author Jianhui Lu
 * @createtime 2019/8/9 10:54
 */
public interface TradeDataService
{
    BusinessTradeData<?> pullTradeData(RequestParamTradeData requestParam) throws BusinessTradeException;

    <T> T pullTradeDataFromDB(RequestParamTradeData requestParam, Class<T> clazz);

    void pushTradeData(BusinessTradeData<?> businessTradeData) throws BusinessTradeException;

    void createTradeDataIndex(RequestParamTradeData requestParam) throws BusinessTradeException;

    void createTradeDataOperationIndex(RequestParamTradeData requestParam) throws BusinessTradeException;

    BusinessTradeDataSet<?> getTradeDataSet4IdentityId(RequestParamTradeData requestParam) throws BusinessTradeException;
}

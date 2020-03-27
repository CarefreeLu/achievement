package com.nuonuo.trade.service;

import com.nuonuo.trade.model.BusinessTradeData;
import com.nuonuo.trade.model.BusinessTradeDataSet;
import com.nuonuo.trade.model.RequestParamTradeData;
import com.nuonuo.trade.model.TradeDataIndex;

/**
 * 类描述：TODO
 *
 * @author Jianhui Lu
 * @date 2019/8/7 10:31
 */
public interface DaoService
{
    /**
     * 功能描述：判断是否创建交易数据索引
     *
     * @param requestParam
     * @return {@link boolean}
     * @throws
     * @author Jianhui Lu
     * @date 2019/8/7 10:33
     */
    boolean isCreateTradeDataIndex(RequestParamTradeData requestParam);
    /**
     * 功能描述：保存交易数据并创建交易数据索引
     *
     * @param requestParam
     * @param tradeDataObj
     * @return
     * @throws
     * @author Jianhui Lu
     * @date 2019/8/7 10:49
     */
    void saveTradeDataAndCreateIndex(RequestParamTradeData requestParam, BusinessTradeData<String> tradeDataObj);

    void saveTradeData(BusinessTradeData<String> tradeDataObj);
    /**
     * 功能描述：获取交易数据
     *
     * @param 
     * @return {@link String}
     * @throws 
     * @author Jianhui Lu
     * @date 2019/8/9 9:59
     */
    String getTradeData(RequestParamTradeData requestParam);

    /**
     * 功能描述：创建交易数据的延深操作索引
     *
     * @param requestParam
     * @return {@link int}
     * @throws
     * @author Jianhui Lu
     * @date 2019/8/14 10:00
     */
    int createTradeDataOperationIndex(RequestParamTradeData requestParam);

    /**
     * 功能描述：获取用户身份下的交易数据
     *
     * @param requestParam
     * @return {@link BusinessTradeDataSet<?>}
     * @throws
     * @author Jianhui Lu
     * @date 2019/8/15 18:06
     */
    BusinessTradeDataSet<?> getTradeDataSet4IdentityId(RequestParamTradeData requestParam);

    /**
     * 功能描述：获取交易数据索引对象
     *
     * @param requestParam
     * @return {@link TradeDataIndex}
     * @throws
     * @author Jianhui Lu
     * @date 2019/8/16 11:01
     */
    TradeDataIndex getTradeDataIndex(RequestParamTradeData requestParam);
}

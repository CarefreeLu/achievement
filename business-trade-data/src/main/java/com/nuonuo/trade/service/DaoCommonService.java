package com.nuonuo.trade.service;

import com.nuonuo.trade.dao.TradeDataIndexDao;
import com.nuonuo.trade.dao.TradeDataOperationIndexDao;
import com.nuonuo.trade.entity.TradeDataIndexDB;
import com.nuonuo.trade.entity.TradeDataOperationIndexDB;
import com.nuonuo.trade.model.RequestParamTradeData;
import com.nuonuo.trade.model.TradeDataIndex;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 类描述：TODO
 *
 * @author Jianhui Lu
 * @date 2019/8/7 10:36
 */
abstract public class DaoCommonService implements DaoService
{
    @Autowired
    protected TradeDataIndexDao tradeDataIndexDao;
    @Autowired
    protected TradeDataOperationIndexDao tradeDataOperationIndexDao;

    protected ThreadLocal<TradeDataIndexDB> tradeDataIndexDB = ThreadLocal.withInitial(TradeDataIndexDB::new);

    @Override
    public boolean isCreateTradeDataIndex(RequestParamTradeData requestParam)
    {
        TradeDataIndexDB tdi = tradeDataIndexDB.get();
        tdi.setTradeNo(requestParam.getDecryptTradeNo());
        tdi.setBusinessPlatformId(requestParam.getBusinessPlatformId());
        tdi.setBusinessType(requestParam.getBusinessType());
        TradeDataIndexDB.UniKey uniKey = tdi.getUniKey();
//        LogUtils.outLogInfoLocal("isCreateTradeDataIndex操作，查询参数为：" + JSONObject.toJSONString(uniKey));
        tdi = tradeDataIndexDao.getByUniKey(uniKey);
        boolean isCreate = false;
        if (tdi != null)
        {
            tradeDataIndexDB.set(tdi);
            isCreate =  true;
        }

        return isCreate;
    }

    @Override
    public int createTradeDataOperationIndex(RequestParamTradeData requestParam)
    {
        TradeDataOperationIndexDB tradeDataOperationIndexDB = new TradeDataOperationIndexDB();
        tradeDataOperationIndexDB.setTradeDataIndexId(tradeDataIndexDB.get().getId());
        tradeDataOperationIndexDB.setOperationSerialNo(requestParam.getOperationSerialNo());
        return tradeDataOperationIndexDao.save(requestParam.getOperationE().tableName, tradeDataOperationIndexDB);
    }

    @Override
    public TradeDataIndex getTradeDataIndex(RequestParamTradeData requestParam)
    {
        TradeDataIndexDB tdi = tradeDataIndexDB.get();
        if (StringUtils.isBlank(tdi.getId()))
        {
            tdi.setTradeNo(requestParam.getDecryptTradeNo());
            tdi.setBusinessPlatformId(requestParam.getBusinessPlatformId());
            tdi.setBusinessType(requestParam.getBusinessType());

            tdi = tradeDataIndexDao.getByUniKey(tdi.getUniKey());
            if (tdi == null)
            {
                return null;
            }

            tradeDataIndexDB.set(tdi);
        }

        TradeDataIndex tradeDataIndex = new TradeDataIndex();
        tradeDataIndex.setIdentityId(tdi.getIdentityId());
        tradeDataIndex.setIdentitySource(tdi.getIdentitySource());

        return tradeDataIndex;
    }
}

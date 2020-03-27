package com.nuonuo.trade.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.nuonuo.trade.dao.TradeDataTaxiDao;
import com.nuonuo.trade.entity.TradeDataIndexDB;
import com.nuonuo.trade.entity.TradeDataTaxiDB;
import com.nuonuo.trade.model.BusinessTradeData;
import com.nuonuo.trade.model.BusinessTradeDataSet;
import com.nuonuo.trade.model.RequestParamTradeData;
import com.nuonuo.trade.model.TradeData4TaxiView;
import com.nuonuo.trade.service.DaoCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 类描述：出租车行业相关数据层服务类
 *
 * @author Jianhui Lu
 * @date 2019/8/7 10:36
 */
@Service
public class Dao4TaxiServiceImpl extends DaoCommonService
{
    @Autowired
    private TradeDataTaxiDao tradeDataTaxiDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveTradeDataAndCreateIndex(RequestParamTradeData requestParam, BusinessTradeData<String> tradeDataObj)
    {
        String tradeDataJson = tradeDataObj.getBusinessTradeData();

        tradeDataIndexDB.get().setIdentityId(requestParam.getIdentityId());
        tradeDataIndexDB.get().setIdentitySource(requestParam.getIdentitySource());
        tradeDataIndexDao.save(tradeDataIndexDB.get());
        TradeDataTaxiDB tradeDataTaxiDB = JSONObject.parseObject(tradeDataJson, TradeDataTaxiDB.class);
        tradeDataTaxiDB.setBusinessPlatformId(requestParam.getBusinessPlatformId());
        tradeDataTaxiDB.setTradeDataIndexId(tradeDataIndexDB.get().getId());
        tradeDataTaxiDao.save(tradeDataTaxiDB);
    }

    @Override
    public void saveTradeData(BusinessTradeData<String> tradeDataObj)
    {
        String tradeDataJson = tradeDataObj.getBusinessTradeData();
        TradeDataTaxiDB tradeDataTaxiDB = JSONObject.parseObject(tradeDataJson, TradeDataTaxiDB.class);
        tradeDataTaxiDB.setBusinessPlatformId(tradeDataObj.getBusinessPlatformId());
        tradeDataTaxiDB.setTradeDataIndexId(tradeDataIndexDB.get().getId());
        tradeDataTaxiDao.save(tradeDataTaxiDB);
    }

    @Override
    public String getTradeData(RequestParamTradeData requestParam)
    {
        TradeDataTaxiDB tradeDataTaxiDB = new TradeDataTaxiDB();
        tradeDataTaxiDB.setTradeNo(requestParam.getDecryptTradeNo());
        tradeDataTaxiDB.setBusinessPlatformId(requestParam.getBusinessPlatformId());
        tradeDataTaxiDB = tradeDataTaxiDao.getByUniKey(tradeDataTaxiDB.getUniKey());
        if (tradeDataTaxiDB != null)
        {
            JSONObject.DEFFAULT_DATE_FORMAT = "yyyyMMddHHmmss";
            return JSONObject.toJSONString(tradeDataTaxiDB, SerializerFeature.WriteDateUseDateFormat);
        }
        return null;
    }

    @Override
    public BusinessTradeDataSet<?> getTradeDataSet4IdentityId(RequestParamTradeData requestParam)
    {
        BusinessTradeDataSet<TradeData4TaxiView> businessTradeDataSet = null;
        TradeDataIndexDB tradeDataIndex = new TradeDataIndexDB();
        tradeDataIndex.setIdentityId(requestParam.getIdentityId());
        tradeDataIndex.setIdentitySource(requestParam.getIdentitySource());
        tradeDataIndex.setBusinessType(requestParam.getBusinessType());
        tradeDataIndex.setBusinessPlatformId(requestParam.getBusinessPlatformId());

        //计算总条数
        int count = tradeDataIndexDao.countTradeData(tradeDataIndex);
        if (count > 0)
        {
            int size = requestParam.getPage().getPageSize();
            int offset = (requestParam.getPage().getCurrentPage()-1)*size;
            List<TradeDataTaxiDB> list = tradeDataTaxiDao.getTradeDataTaxiDBList(tradeDataIndex, offset, size);
            List<TradeData4TaxiView> listView =
                    list.stream()
                        .map(tradeDataTaxiDB ->
                                        JSONObject.parseObject(
                                                JSONObject.toJSONStringWithDateFormat(tradeDataTaxiDB, "yyyyMMddHHmmss"),
                                                TradeData4TaxiView.class)
                        ).collect(Collectors.toList());
            businessTradeDataSet = new BusinessTradeDataSet<>();
            businessTradeDataSet.setTradeDataSet(listView);
            businessTradeDataSet.setTradeDataTotal(count);
        }

        return businessTradeDataSet;
    }


}

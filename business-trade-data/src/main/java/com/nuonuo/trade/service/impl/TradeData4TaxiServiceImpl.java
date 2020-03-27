package com.nuonuo.trade.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.nuonuo.trade.constant.BusinessTradeErrorE;
import com.nuonuo.trade.constant.BusinessTypeE;
import com.nuonuo.trade.constant.CipherE;
import com.nuonuo.trade.constant.IdentitySourceE;
import com.nuonuo.trade.constant.TradeDataPlatformE;
import com.nuonuo.trade.exception.BusinessTradeException;
import com.nuonuo.trade.model.*;
import com.nuonuo.trade.service.DaoService;
import com.nuonuo.trade.service.TradeDataCommonService;
import com.nuonuo.trade.util.LogUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


/**
 * 类描述：交易数据服务类
 *
 * @author Jianhui Lu
 * @date 2019/8/5 15:08
 */
@Service
public class TradeData4TaxiServiceImpl extends TradeDataCommonService
{
    @Autowired
    public TradeData4TaxiServiceImpl(
            @Qualifier("dao4TaxiServiceImpl") DaoService daoService)
    {
        super.daoService = daoService;
    }

    /**
     * 功能描述：拉取交易数据
     *
     * @param requestParam 获取数据的请求参数
     * @return {@link String}
     *
     * @author Jianhui Lu
     * @date 2019/8/12 9:46
     */
    @Override
    public BusinessTradeData<?> pullTradeData(RequestParamTradeData requestParam) throws BusinessTradeException
    {
        //获取并设置税号对应的加解密key
        setTaxNoSecretKey(requestParam);
        //校验拉取交易数据的参数
        paramVerify4Common(requestParam);
        if (daoService.isCreateTradeDataIndex(requestParam))
        {
            TradeDataIndex tradeDataIndex = daoService.getTradeDataIndex(requestParam);
            if (!requestParam.getIdentityId().equals(tradeDataIndex.getIdentityId())
                    || !requestParam.getIdentitySource().equals(tradeDataIndex.getIdentitySource()))
            {
                throw new BusinessTradeException(BusinessTradeErrorE.E0006);
            }
            //拉取数据
            BusinessTradeData<String> tradeData = pullTradeDataFromPlatform(requestParam, String.class);
            LogUtils.outLogInfoLocal(JSONObject.toJSONString(tradeData));
            //转换从对象
            TradeData4TaxiView tradeData4TaxiView = JSONObject.parseObject(tradeData.getBusinessTradeData(), TradeData4TaxiView.class);
            tradeData4TaxiView.setCreateOwnershipFlag(true);
            //行程结束 或者 行程异常 更新记录
            if (!TradeData4Taxi.TravelStatusE.doing.status.equals(tradeData4TaxiView.getTravelStatus()))
            {
                daoService.saveTradeData(tradeData);
            }

            BusinessTradeData<TradeData4TaxiView> businessTradeData = new BusinessTradeData();
            businessTradeData.setBusinessType(tradeData.getBusinessType());
            businessTradeData.setBusinessPlatformId(tradeData.getBusinessPlatformId());
            businessTradeData.setBusinessTradeData(tradeData4TaxiView);
            return businessTradeData;
        }

        //没有再从第三方平台获取
        return pullTradeDataFromPlatform(requestParam, TradeData4TaxiView.class);
    }

    @Override
    public void createTradeDataOperationIndex(RequestParamTradeData requestParam) throws BusinessTradeException
    {
        paramVerify4CreateTradeDataOperationIndex(requestParam);
        //获取税号对应密钥并设置
        setTaxNoSecretKey(requestParam);
        //判断是否已创建交易数据索引
        if (!daoService.isCreateTradeDataIndex(requestParam))
        {
            throw new BusinessTradeException(BusinessTradeErrorE.E0014);
        }
        String tradeDataJson;
        if ((tradeDataJson = daoService.getTradeData(requestParam)) == null)
        {
            throw new BusinessTradeException(BusinessTradeErrorE.E0011);
        }
        TradeData4Taxi tradeData4Taxi = JSONObject.parseObject(tradeDataJson, TradeData4Taxi.class);
        if (tradeData4Taxi == null || !TradeData4Taxi.TravelStatusE.end.status.equals(tradeData4Taxi.getTravelStatus()))
        {
            throw new BusinessTradeException(BusinessTradeErrorE.E0011);
        }
        //创建交易数据操作索引
        daoService.createTradeDataOperationIndex(requestParam);
    }

    @Override
    public void pushTradeData(BusinessTradeData<?> businessTradeData) throws BusinessTradeException
    {
        RequestParamTradeData requestParam = new RequestParamTradeData();
        requestParam.setBusinessType(businessTradeData.getBusinessType());
        requestParam.setBusinessPlatformId(businessTradeData.getBusinessPlatformId());
        requestParam.setTaxNo(businessTradeData.getTaxNo());
        //交易推送数据的参数
        paramVerify4PushTradeData(businessTradeData);
        String tradeDataJson;
        if (StringUtils.isNotBlank(businessTradeData.getCipherType()))
        {
            //获取并设置税号对应的加解密key
            setTaxNoSecretKey(requestParam);
            tradeDataJson = decryptTradeData((String)businessTradeData.getBusinessTradeData(), businessTradeData.getCipherType());
        }
        else
        {
            tradeDataJson = JSONObject.toJSONString(businessTradeData.getBusinessTradeData());
        }
        //解析交易数据
        TradeData4Taxi tradeData4Taxi = JSONObject.parseObject(tradeDataJson, TradeData4Taxi.class);
        if (StringUtils.isNotBlank(businessTradeData.getCipherType()))
        {
            if(!businessTradeData.getTaxNo().equals(tradeData4Taxi.getPayeeTaxNo()))
            {
                throw new BusinessTradeException(BusinessTradeErrorE.E0011);
            }
        }
        //校验入库的基本数据
        tradeData4Taxi.verifyData();
        //设置交易订单号
        requestParam.setTradeNo(tradeData4Taxi.getTradeNo());
        if (!daoService.isCreateTradeDataIndex(requestParam))
        {
            throw new BusinessTradeException(BusinessTradeErrorE.E0021);
        }

        BusinessTradeData<String> tradeData = new BusinessTradeData<>();
        tradeData.setBusinessType(businessTradeData.getBusinessType());
        tradeData.setBusinessPlatformId(businessTradeData.getBusinessPlatformId());
        tradeData.setBusinessTradeData(tradeDataJson);
        //保存第三方推送的交易数据
        daoService.saveTradeData(tradeData);
    }

    /**
     * 功能描述：推送交易数据参数校验
     *
     * @param businessTradeData
     * @return
     * @throws
     * @author Jianhui Lu
     * @date 2019/8/22 13:11
     */
    private void paramVerify4PushTradeData(BusinessTradeData businessTradeData) throws BusinessTradeException
    {
        if (BusinessTypeE.getBusinessTypeE(businessTradeData.getBusinessType()) == null)
        {
            throw new BusinessTradeException(BusinessTradeErrorE.E0001);
        }
        if (TradeDataPlatformE.getTradeDataPlatformE(businessTradeData.getBusinessPlatformId()) == null)
        {
            throw new BusinessTradeException(BusinessTradeErrorE.E0001);
        }
        if (businessTradeData.getBusinessTradeData() == null)
        {
            throw new BusinessTradeException(BusinessTradeErrorE.E0020);
        }
        if (StringUtils.isNotBlank(businessTradeData.getCipherType()))
        {
            if(CipherE.getCipherE(businessTradeData.getCipherType()) == null)
            {
                throw new BusinessTradeException(BusinessTradeErrorE.E0018);
            }
            if (StringUtils.isBlank(businessTradeData.getTaxNo()))
            {
                throw new BusinessTradeException(BusinessTradeErrorE.E0019);
            }
        }
    }

    @Override
    public BusinessTradeDataSet<?> getTradeDataSet4IdentityId(RequestParamTradeData requestParam) throws BusinessTradeException
    {
        //参数校验
        paramVerify4GetTradeDataSet4IdentityId(requestParam);
        return daoService.getTradeDataSet4IdentityId(requestParam);
    }

    private void paramVerify4GetTradeDataSet4IdentityId(RequestParamTradeData requestParam) throws BusinessTradeException
    {
        if (requestParam.getBusinessTypeE() == null)
        {
            throw new BusinessTradeException(BusinessTradeErrorE.E0001);
        }
        if (StringUtils.isBlank(requestParam.getIdentityId()))
        {
            throw new BusinessTradeException(BusinessTradeErrorE.E0003);
        }
        if (IdentitySourceE.getIdentitySourceE(requestParam.getIdentitySource()) == null)
        {
            throw new BusinessTradeException(BusinessTradeErrorE.E0003);
        }
    }
}

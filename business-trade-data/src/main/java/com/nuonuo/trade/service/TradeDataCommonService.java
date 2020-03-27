package com.nuonuo.trade.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.nuonuo.trade.constant.BusinessPlatformE;
import com.nuonuo.trade.constant.BusinessTradeErrorE;
import com.nuonuo.trade.constant.CipherE;
import com.nuonuo.trade.constant.IdentitySourceE;
import com.nuonuo.trade.constant.PlatformConfig;
import com.nuonuo.trade.entity.CorpInvoice;
import com.nuonuo.trade.exception.BusinessTradeException;
import com.nuonuo.trade.function.DecryptFun;
import com.nuonuo.trade.model.BusinessTradeData;
import com.nuonuo.trade.model.RequestParamTradeData;
import com.nuonuo.trade.model.ResponseMode;
import com.nuonuo.trade.util.CorpRedisUtils;
import com.nuonuo.trade.util.CryptoUtils;
import com.nuonuo.trade.util.HttpClientUtil;
import com.nuonuo.trade.util.LogUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Map;

/**
 * 功能描述：抽取交易数据服务的通用部分
 *
 * @author Jianhui Lu
 * @createtime 2019/8/9 10:54
 */
abstract public class TradeDataCommonService implements TradeDataService
{
    private static final String CHARSET = "UTF-8";

    private static final String CONTENT_TYPE = "application/json";

    private static final int TIME_OUT = 15000;

    private static final String PULL_URL = "/invoice/pull/trade-business-data";

    private static final boolean isTest = false;

    @Autowired
    private PlatformConfig platformConfig;

    @Autowired
    private CorpRedisUtils corpRedisUtils;

    protected DaoService daoService;

    private final ThreadLocal<CorpInvoice> corpInvoice = new ThreadLocal<>();

    protected final ThreadLocal<BusinessPlatformE> businessPlatformE = new ThreadLocal<>();

    /**
     * 功能描述：拉取交易数据
     *
     * @param requestParam
     * @return {@link String}
     * @throws
     * @author Jianhui Lu
     * @date 2019/8/6 15:25
     */
    public <T> T pullTradeDataFromDB(RequestParamTradeData requestParam, Class<T> clazz)
    {
        T tradeData = null;
        String tradeDataJson = daoService.getTradeData(requestParam);
        if (StringUtils.isNotBlank(tradeDataJson))
        {
            tradeData = JSONObject.parseObject(tradeDataJson, clazz);
        }
        return tradeData;
    }

    /**
     * 功能描述：创建交易数据索引
     *
     * @param requestParam
     * @return {@link String} 返回处理结果Json报文
     * @throws
     * @author Jianhui Lu
     * @date 2019/8/6 15:17
     */
    @Override
    public void createTradeDataIndex(RequestParamTradeData requestParam) throws BusinessTradeException
    {
        //校验创建交易索引参数
        paramVerify4Common(requestParam);
        //获取税号对应密钥并设置
        setTaxNoSecretKey(requestParam);
        //判断是否已创建交易数据索引
        if (daoService.isCreateTradeDataIndex(requestParam))
        {
            throw new BusinessTradeException(BusinessTradeErrorE.E0006);
        }
        //拉取最新交易数据
        BusinessTradeData<String> tradeData = pullTradeDataFromPlatform(requestParam, String.class);
        //保存交易数据并创建索引
        daoService.saveTradeDataAndCreateIndex(requestParam, tradeData);
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
        //创建交易数据操作索引
        daoService.createTradeDataOperationIndex(requestParam);
    }

    /**
     * 功能描述：获取并设置税号对用的加密Key
     *
     * @param requestParam
     * @return
     * @throws
     * @author Jianhui Lu
     * @date 2019/8/8 15:45
     */
    protected void setTaxNoSecretKey(RequestParamTradeData requestParam) throws BusinessTradeException
    {
        if (StringUtils.isNotBlank(requestParam.getTaxNo()))
        {
            LogUtils.outLogInfoLocal("请求税号为：" + requestParam.getTaxNo());
            if (corpInvoice.get() == null || !corpInvoice.get().getTaxNo().equals(requestParam.getTaxNo()))
            {
                corpInvoice.set(corpRedisUtils.getCorpInvoice(requestParam.getTaxNo()));
                if (corpInvoice.get() == null)
                {
                    throw new BusinessTradeException(BusinessTradeErrorE.E0009);
                }
            }
            LogUtils.outLogInfoLocal("企业资质为：【" + corpInvoice.get().toString()+"】");
            requestParam.setSecretKey(corpInvoice.get().getAppSecret());
        }
    }

    /**
     * 功能描述：参数校验
     *
     * @param requestParam 请求参数对象
     * @throws
     * @author Jianhui Lu
     * @date 2019/8/6 14:33
     */
    protected void paramVerify4CreateTradeDataOperationIndex(RequestParamTradeData requestParam) throws BusinessTradeException
    {
        if (StringUtils.isBlank(requestParam.getTradeNo()))
        {
            throw new BusinessTradeException(BusinessTradeErrorE.E0002);
        }
        if (requestParam.getBusinessTypeE() == null)
        {
            throw new BusinessTradeException(BusinessTradeErrorE.E0001);
        }
        if (requestParam.getTradeDataPlatformE() == null)
        {
            throw new BusinessTradeException(BusinessTradeErrorE.E0001);
        }
        if (requestParam.getOperationE() == null)
        {
            throw new BusinessTradeException(BusinessTradeErrorE.E0012);
        }
        if (StringUtils.isBlank(requestParam.getOperationSerialNo()))
        {
            throw new BusinessTradeException(BusinessTradeErrorE.E0013);
        }
    }

    /**
     * 功能描述：统一参数校验
     *
     * @param requestParam
     * @return
     * @throws
     * @author Jianhui Lu
     * @date 2019/8/16 16:08
     */
    protected void paramVerify4Common(RequestParamTradeData requestParam) throws BusinessTradeException
    {
        if (StringUtils.isBlank(requestParam.getTradeNo()))
        {
            throw new BusinessTradeException(BusinessTradeErrorE.E0002);
        }
        if (StringUtils.isBlank(requestParam.getIdentityId()))
        {
            throw new BusinessTradeException(BusinessTradeErrorE.E0003);
        }
        if (IdentitySourceE.getIdentitySourceE(requestParam.getIdentitySource()) == null)
        {
            throw new BusinessTradeException(BusinessTradeErrorE.E0003);
        }
        if (requestParam.getBusinessTypeE() == null)
        {
            throw new BusinessTradeException(BusinessTradeErrorE.E0001);
        }
        if (requestParam.getTradeDataPlatformE() == null)
        {
            throw new BusinessTradeException(BusinessTradeErrorE.E0001);
        }
    }

    /**
     * 功能描述：从第三方交易数据平台获取指定行业类型的交易数据对象
     *
     * @param requestParam 请求参数对象
     * @param clazz        交易数据对象Class
     * @return {@link BusinessTradeData<T>}
     * @throws
     * @author Jianhui Lu
     * @date 2019/8/15 14:16
     */
    protected <T> BusinessTradeData<T> pullTradeDataFromPlatform(RequestParamTradeData requestParam, Class<T> clazz) throws BusinessTradeException
    {
        //从第三方平台获取交易数据
        String responseMsg = pullTradeDataFromPlatform(requestParam);
        LogUtils.outLogInfoLocal(responseMsg);
        businessPlatformE.set(requestParam.getBusinessPlatformE());
        //解析交易数据
        BusinessTradeData<String> tradeData = parseTradeData(responseMsg);
        businessPlatformE.remove();
        if (clazz == String.class)
        {
            return (BusinessTradeData<T>)tradeData;
        }
        //构建成功报文
        BusinessTradeData<T> businessTradeData = new BusinessTradeData();
        businessTradeData.setBusinessType(tradeData.getBusinessType());
        businessTradeData.setBusinessPlatformId(tradeData.getBusinessPlatformId());

        businessTradeData.setBusinessTradeData(JSONObject.parseObject(tradeData.getBusinessTradeData(), clazz));
        return businessTradeData;
    }

    /**
     * 功能描述：从响应平台拉取交易数据
     *
     * @param requestParam 请求参数对象
     * @return {@link String} 响应报文
     * @throws
     * @author Jianhui Lu
     * @date 2019/8/6 16:42
     */
    protected String pullTradeDataFromPlatform(RequestParamTradeData requestParam) throws BusinessTradeException
    {
        Map<String, String> platform2UrlMap = platformConfig.getUrlMap();
        if (platform2UrlMap == null)
        {
            throw new BusinessTradeException(BusinessTradeErrorE.E0004);
        }
        String url = platform2UrlMap.get(requestParam.getBusinessPlatformE().name());
        if (StringUtils.isBlank(url))
        {
            throw new BusinessTradeException(BusinessTradeErrorE.E0004);
        }

        if (!isTest)
        {
            url += PULL_URL;
        }
        /**
         * 订单号没有加密的，且该行业平台必须加密请求的，进行加密处理
         */
        if (!requestParam.getEncryptStatus() && requestParam.getBusinessPlatformE().requiredEncrypt)
        {
            try
            {
                String value = CryptoUtils.encrypt(requestParam.getTradeNo(), requestParam.getSecretKey());
                requestParam.setTradeNo(value);
            }
            catch (Exception e)
            {
                //加密失败，保持原有订单号传输
            }
        }
        String requestJson = JSONObject.toJSONString(requestParam);
        String jsonResult;
        StopWatch stopWatch = StopWatch.createStarted();
        try
        {
            jsonResult = HttpClientUtil.httpPost(url,
                    new StringEntity(requestJson, CHARSET), CONTENT_TYPE, TIME_OUT, CHARSET);
        }
        catch (IOException e)
        {
            throw new BusinessTradeException(e);
        }
        finally
        {
            stopWatch.stop();
            LogUtils.outLogInfoBySid(requestParam.getTradeNo(), requestParam.getTaxNo(), "TradeData", "pull-trade-data",
                    "耗时：" + stopWatch.getTime() + " 毫秒");
        }


        if (StringUtils.isBlank(jsonResult))
        {
            throw new BusinessTradeException(BusinessTradeErrorE.E0005);
        }

        return jsonResult;
    }

    /**
     * 功能描述：解析交易数据对象
     *
     * @param tradeDataJson 交易数据Json字符串
     * @return {@link BusinessTradeData} 解析的交易数据对象
     * @throws
     * @author Jianhui Lu
     * @date 2019/8/6 13:34
     */
    private BusinessTradeData<String> parseTradeData(String tradeDataJson) throws BusinessTradeException
    {
        ResponseMode<BusinessTradeData<String>> resultObj =
                JSONObject.parseObject(tradeDataJson,
                        new TypeReference<ResponseMode<BusinessTradeData<String>>>() {});
        if (!resultObj.isSuccess())
        {
            String code = businessPlatformE.get().name() + "-" + resultObj.getResultCode();
            throw new BusinessTradeException(code, resultObj.getResultMsg());
        }

        if (resultObj.getResultBody() == null || resultObj.getResultBody().getBusinessTradeData() == null)
        {
            throw new BusinessTradeException(BusinessTradeErrorE.E0007);
        }

        BusinessTradeData<String> tradeData = resultObj.getResultBody();
        //有加解密类型，有税号，进行解密操作
        if (StringUtils.isNotBlank(tradeData.getCipherType()) && StringUtils.isNotBlank(tradeData.getTaxNo()))
        {
            if(!corpInvoice.get().getTaxNo().equals(tradeData.getTaxNo()))
            {
                throw new BusinessTradeException(BusinessTradeErrorE.E0017);
            }
            tradeData.setBusinessTradeData(decryptTradeData(tradeData.getBusinessTradeData(), tradeData.getCipherType()));
        }

        return tradeData;
    }

    /**
     * 功能描述：解密交易数据
     *
     * @param tradeData  交易数据字符串
     * @param cipherType 加解密类型
     * @return {@link String}
     * @throws
     * @author Jianhui Lu
     * @date 2019/8/16 18:02
     */
    protected String decryptTradeData(String tradeData, String cipherType) throws BusinessTradeException
    {
        CipherE cipherE = CipherE.getCipherE(cipherType);
        if (cipherE == null)
        {
            throw new BusinessTradeException(BusinessTradeErrorE.E0018);
        }

        DecryptFun<String> decryptFun = data -> data;
        switch (cipherE)
        {
            case DES:
                decryptFun = this::cipherE01;
                break;
        }

        return decryptFun.decrypt(tradeData);
    }

    /**
     * 功能描述：解密算法01
     *
     * @param data
     * @return {@link String}
     * @throws
     * @author Jianhui Lu
     * @date 2019/8/20 17:08
     */
    private String cipherE01(String data) throws BusinessTradeException
    {
        try
        {
            return CryptoUtils.decrypt(data, corpInvoice.get().getAppSecret());

        }
        catch (Exception e)
        {
            throw new BusinessTradeException(BusinessTradeErrorE.E0015);
        }
    }
}

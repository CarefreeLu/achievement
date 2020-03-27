package com.nuonuo.trade.constant;

import com.nuonuo.trade.model.TradeData4Taxi;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述：行业、平台、操作对象关系映射枚举
 *
 * @author Jianhui Lu
 * @createtime 2019/8/6 10:33
 */
public enum BusinessPlatformE
{
    TAXI_ZHENGSHANG(BusinessTypeE.TAXI, TradeDataPlatformE.ZHENGSHANG, TradeData4Taxi.class, Boolean.TRUE);

    public final BusinessTypeE businessTypeE;

    public final TradeDataPlatformE tradeDataPlatformE;

    public final Class clazz;
    /**
     * 是否必须加密
     */
    public final Boolean requiredEncrypt;

    private static final Map<String, BusinessPlatformE> businessPlatform2Enum;

    BusinessPlatformE(BusinessTypeE businessTypeE, TradeDataPlatformE tradeDataPlatformE, Class clazz, Boolean requiredEncrypt)
    {
        this.businessTypeE = businessTypeE;
        this.tradeDataPlatformE = tradeDataPlatformE;
        this.clazz = clazz;
        this.requiredEncrypt = requiredEncrypt;
    }

    static
    {
        Map<String, BusinessPlatformE> businessPlatform2EnumTemp = new HashMap<>();
        Arrays.stream(BusinessPlatformE.values())
              .forEach(
                      businessPlatformE -> businessPlatform2EnumTemp.put(
                              businessPlatformE.businessTypeE.name()+businessPlatformE.tradeDataPlatformE.name(),
                              businessPlatformE)
              );

        businessPlatform2Enum = Collections.unmodifiableMap(businessPlatform2EnumTemp);
    }

    public static BusinessPlatformE getBusinessPlatformE(BusinessTypeE businessTypeE, TradeDataPlatformE tradeDataPlatformE)
    {
        if (businessTypeE == null || tradeDataPlatformE == null)
        {
            return null;
        }
        return businessPlatform2Enum.get(businessTypeE.name()+tradeDataPlatformE.name());
    }

    public static void main(String[] args)
    {
        System.out.println(BusinessPlatformE.getBusinessPlatformE(null, TradeDataPlatformE.ZHENGSHANG));
    }
}

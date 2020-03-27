package com.nuonuo.trade.constant;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 类描述：交易数据提供平台枚举类
 *
 * @author Jianhui Lu
 * @date 2019/8/5 14:01
 */
public enum TradeDataPlatformE
{
    ZHENGSHANG("001", "正尚出租车计价平台");

    /**
     * 平台码（平台ID）
     */
    public final String code;
    /**
     * 平台描述
     */
    public final String description;

    public final static Map<String, TradeDataPlatformE> code2Enum;

    TradeDataPlatformE(String code, String description)
    {
        this.code = code;
        this.description = description;
    }

    static
    {
        Map<String, TradeDataPlatformE> code2EnumTemp = new HashMap<>();
        Arrays.stream(TradeDataPlatformE.values())
              .forEach(
                      type -> code2EnumTemp.put(type.code, type)
              );
        code2Enum = Collections.unmodifiableMap(code2EnumTemp);
    }

    public static TradeDataPlatformE getTradeDataPlatformE(String code)
    {
        return code2Enum.get(code);
    }

    public static void main(String[] args)
    {
        System.out.println(TradeDataPlatformE.getTradeDataPlatformE("sdfs"));
    }
}

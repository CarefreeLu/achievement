package com.nuonuo.trade.constant;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 类描述：行业类型枚举类
 *
 * @author Jianhui Lu
 * @date 2019/8/5 13:52
 */
public enum BusinessTypeE
{
    TAXI("001", "出租车行业");

    public final String code;

    public final String description;

    public final static Map<String, BusinessTypeE> code2Enum;

    BusinessTypeE(String code, String description)
    {
        this.code = code;
        this.description = description;
    }

    static
    {
        Map<String, BusinessTypeE> code2EnumTemp = new HashMap<>();
        Arrays.stream(BusinessTypeE.values())
              .forEach(
                      type -> code2EnumTemp.put(type.code, type)
              );
        code2Enum = Collections.unmodifiableMap(code2EnumTemp);
    }

    public static BusinessTypeE getBusinessTypeE(String code)
    {
        return code2Enum.get(code);
    }

    public static void main(String[] args)
    {
        System.out.println(BusinessTypeE.getBusinessTypeE("12"));
    }
}

package com.nuonuo.trade.constant;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述：TODO
 *
 * @author Jianhui Lu
 * @createtime 2019/8/12 10:45
 */
public enum IdentitySourceE
{
    WeChat("01"),

    Alipay("02");

    public final String code;

    static private final Map<String, IdentitySourceE> code2Enum;

    IdentitySourceE(String code)
    {
        this.code = code;
    }

    static
    {
        Map<String, IdentitySourceE> code2EnumTemp = new HashMap<>();
        Arrays.stream(IdentitySourceE.values()).forEach(
                source -> code2EnumTemp.put(source.code, source));
        code2Enum = Collections.unmodifiableMap(code2EnumTemp);
    }

    public static IdentitySourceE getIdentitySourceE(String code)
    {
        return code2Enum.get(code);
    }
}

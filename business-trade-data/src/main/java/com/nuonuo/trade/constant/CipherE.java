package com.nuonuo.trade.constant;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述：TODO
 *
 * @author Jianhui Lu
 * @createtime 2019/8/16 17:47
 */
public enum CipherE
{
    DES("01");

    public final String code;

    public static final Map<String, CipherE> code2Enum;

    CipherE(String code)
    {
        this.code = code;
    }

    static
    {
        Map<String, CipherE> code2EnumTemp = new HashMap<>();
        Arrays.stream(CipherE.values()).forEach(cipher -> code2EnumTemp.put(cipher.code, cipher));
        code2Enum = Collections.unmodifiableMap(code2EnumTemp);
    }

    public static CipherE getCipherE(String code)
    {
        return code2Enum.get(code);
    }
}

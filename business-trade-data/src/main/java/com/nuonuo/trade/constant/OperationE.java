package com.nuonuo.trade.constant;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 类描述：交易数据关联的操作枚举
 *
 * @author Jianhui Lu
 * @date 2019/8/14 16:42
 */
public enum OperationE
{
    /**
     * 开票操作
     */
    invoice("001", "trade_data_operation_invoice_index");

    /**
     * 操作编码
     */
    public final String code;
    /**
     * 操作对应表
     */
    public final String tableName;

    private static final Map<String, OperationE> code2Enum;

    OperationE(String code, String tableName)
    {
        this.code = code;
        this.tableName = tableName;
    }

    static
    {
        Map<String, OperationE> code2EnumTemp = new HashMap<>();
        Arrays.stream(OperationE.values()).forEach(
                operation -> code2EnumTemp.put(operation.code, operation)
        );
        code2Enum = Collections.unmodifiableMap(code2EnumTemp);
    }

    public static OperationE getOperationE(String code)
    {
        return code2Enum.get(code);
    }
}

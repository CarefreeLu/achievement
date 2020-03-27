package com.nuonuo.trade.constant;

/**
 * 类描述：日志打印常量类
 *
 * @author Jianhui Lu
 * @date 2019/8/8 15:17
 */
public class LogConstant
{
    /**
     * 模块枚举
     */
    public enum ModuleE
    {
        BUSINESS_TRADE
    }

    /**
     * 动作枚举
     * 以控制层入口URL标识
     */
    public enum ActionE
    {
        pull_trade_data,
        create_ownership_index,
        create_operation_index
    }
}

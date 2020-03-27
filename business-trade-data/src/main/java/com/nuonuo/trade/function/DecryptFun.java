package com.nuonuo.trade.function;

import com.nuonuo.trade.exception.BusinessTradeException;

/**
 * 类描述：TODO
 *
 * @author Jianhui Lu
 * @date 2019/8/20 16:17
 */
@FunctionalInterface
public interface DecryptFun<String>
{
    String decrypt(String data) throws BusinessTradeException;
}

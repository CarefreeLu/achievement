package com.nuonuo.trade.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 类描述：TODO
 *
 * @author Jianhui Lu
 * @date 2019/8/15 19:08
 */
@Data
public class BusinessTradeDataSetView<T> extends BusinessTradeDataSet<T>
{
    @JSONField(serialize = false)
    @Override
    public String getBusinessType()
    {
        return super.getBusinessType();
    }

    @JSONField(serialize = false)
    @Override
    public String getBusinessPlatformId()
    {
        return super.getBusinessPlatformId();
    }

    @JSONField(serialize = false)
    @Override
    public String getIdentityId()
    {
        return super.getIdentityId();
    }

    @JSONField(serialize = false)
    @Override
    public String getIdentitySource()
    {
        return super.getIdentitySource();
    }

    @JSONField(name = "aCount")
    @Override
    public int getTradeDataTotal()
    {
        return super.getTradeDataTotal();
    }
}

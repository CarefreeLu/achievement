package com.nuonuo.trade.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 类描述：出租车行业-交易数据对象 (前端用)
 *
 * @author Jianhui Lu
 * @date 2019/8/15 10:51
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class TradeData4TaxiView extends TradeData4Taxi
{
    @JSONField(serialize = false)
    @Override
    public String getDeviceNo()
    {
        return super.getDeviceNo();
    }
    @JSONField(serialize = false)
    @Override
    public String getStartLongitude()
    {
        return super.getStartLongitude();
    }
    @JSONField(serialize = false)
    @Override
    public String getStartLatitude()
    {
        return super.getStartLatitude();
    }
    @JSONField(serialize = false)
    @Override
    public String getStartAzimuth()
    {
        return super.getStartAzimuth();
    }
    @JSONField(serialize = false)
    @Override
    public String getEndLongitude()
    {
        return super.getEndLongitude();
    }
    @JSONField(serialize = false)
    @Override
    public String getEndLatitude()
    {
        return super.getEndLatitude();
    }
    @JSONField(serialize = false)
    @Override
    public String getEndAzimuth()
    {
        return super.getEndAzimuth();
    }

    /**
     * 是否创建交易数据所属关系
     */
    @JSONField(ordinal = 20)
    private Boolean createOwnershipFlag = false;
}

package com.nuonuo.trade.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.nuonuo.trade.constant.BusinessTradeErrorE;
import com.nuonuo.trade.exception.BusinessTradeException;
import com.nuonuo.trade.util.TimeUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 类描述：出租车行业-交易数据对象
 *
 * @author Jianhui Lu
 * @date 2019/8/5 13:41
 */
@Data
public class TradeData4Taxi
{
    /**
     * 交易订单号
     */
    @JSONField(ordinal = 1)
    private String tradeNo;
    /**
     * 收款方税号（销方税号）
     */
    @JSONField(ordinal = 2)
    private String payeeTaxNo;
    /**
     * 交易金额
     */
    @JSONField(ordinal = 3)
    private String amount;
    /**
     * 车牌号
     */
    @JSONField(ordinal = 4)
    private String licensePlateNo;
    /**
     * 设备号(产生交易的设备)
     */
    @JSONField(ordinal = 5)
    private String deviceNo;
    /**
     * 上车时间
     */
    @JSONField(ordinal = 6)
    private String startTime;
    /**
     * 下车时间
     */
    @JSONField(ordinal = 7)
    private String endTime;
    /**
     * 上车地点
     */
    @JSONField(ordinal = 8)
    private Site startSite;
    /**
     * 下车地点
     */
    @JSONField(ordinal = 9)
    private Site endSite;
    /**
     * 上车经度
     */
    @JSONField(ordinal = 10)
    private String startLongitude;
    /**
     * 上车纬度
     */
    @JSONField(ordinal = 11)
    private String startLatitude;
    /**
     * 上车方位角
     */
    @JSONField(ordinal = 12)
    private String startAzimuth;
    /**
     * 下车经度
     */
    @JSONField(ordinal = 13)
    private String endLongitude;
    /**
     * 下车纬度
     */
    @JSONField(ordinal = 14)
    private String endLatitude;
    /**
     * 下车方位角
     */
    @JSONField(ordinal = 15)
    private String endAzimuth;
    /**
     * 单价
     */
    @JSONField(ordinal = 16)
    private String unitPrice;
    /**
     * 里程
     */
    @JSONField(ordinal = 17)
    private String mileage;
    /**
     * 等候时间
     */
    @JSONField(ordinal = 18)
    private String waitingTime;
    /**
     * 行程状态
     */
    @JSONField(ordinal = 19)
    private String travelStatus;

    public enum TravelStatusE
    {
        //行程中
        doing("0"),
        //行程结束
        end("1"),
        //行程异常
        exception("2");

        TravelStatusE(String status)
        {
            this.status = status;
        }

        public final String status;

        private final static Map<String, TravelStatusE> status2Enum;

        static
        {
            Map<String, TravelStatusE> status2EnumTemp = new HashMap<>();
            Arrays.stream(TravelStatusE.values())
                  .forEach(statusE -> status2EnumTemp.put(statusE.status, statusE));
            status2Enum = Collections.unmodifiableMap(status2EnumTemp);
        }

        public static TravelStatusE getTravelStatusE(String status)
        {
            return status2Enum.get(status);
        }
    }

    /**
     * 功能描述：校验数据的有效性
     *
     * @param
     * @return
     * @throws
     * @author Jianhui Lu
     * @date 2019/9/12 13:24
     */
    public void verifyData() throws BusinessTradeException
    {
        if (TravelStatusE.getTravelStatusE(this.travelStatus) == null)
        {
            throw new BusinessTradeException(BusinessTradeErrorE.E0011);
        }
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime))
        {
            Date start = TimeUtil.parseyyyyMMddHHmmss(startTime);
            Date end = TimeUtil.parseyyyyMMddHHmmss(endTime);
            if (start == null || end == null)
            {
                throw new BusinessTradeException(BusinessTradeErrorE.E0011);
            }
            if (start.compareTo(end) > 0)
            {
                throw new BusinessTradeException(BusinessTradeErrorE.E0011);
            }
        }
    }
}

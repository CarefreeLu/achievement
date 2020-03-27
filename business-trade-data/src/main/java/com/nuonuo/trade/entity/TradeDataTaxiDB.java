package com.nuonuo.trade.entity;


import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 类描述：TODO
 *
 * @author Jianhui Lu
 * @date 2019/8/6 11:29
 */
@Data
@ToString
public class TradeDataTaxiDB
{
    private String id;
    /**
     * 交易数据索引表ID
     */
    private String tradeDataIndexId;
    /**
     * 平台ID(交易所属行业平台身份)
     */
    private String businessPlatformId;
    /**
     * 交易订单号
     */
    private String tradeNo;
    /**
     * 收款方税号（销方税号）
     */
    private String payeeTaxNo;
    /**
     * 交易金额
     */
    private String amount;
    /**
     * 车牌号
     */
    private String licensePlateNo;
    /**
     * 设备号(产生交易的设备)
     */
    private String deviceNo;
    /**
     * 上车时间
     */
    private Date startTime;
    /**
     * 下车时间
     */
    private Date endTime;
    /**
     * 上车地点(数据库字段)
     */
    private String startSiteStr;
    /**
     * 下车地点(数据库字段)
     */
    private String endSiteStr;
    /**
     * 上车地点
     */
    private Site startSite;
    /**
     * 下车地点
     */
    private Site endSite;
    /**
     * 上车经度
     */
    private BigDecimal startLongitude;
    /**
     * 上车纬度
     */
    private BigDecimal startLatitude;
    /**
     * 上车方位角
     */
    private BigDecimal startAzimuth;
    /**
     * 下车经度
     */
    private BigDecimal endLongitude;
    /**
     * 下车纬度
     */
    private BigDecimal endLatitude;
    /**
     * 下车方位角
     */
    private BigDecimal endAzimuth;
    /**
     * 单价
     */
    private BigDecimal unitPrice;
    /**
     * 里程
     */
    private BigDecimal mileage;
    /**
     * 等候时间
     */
    private long waitingTime;
    /**
     * 行程状态
     */
    private String travelStatus;

    private UniKey uniKey;

    public UniKey getUniKey()
    {
        if (uniKey == null)
        {
            uniKey = new UniKey();
        }

        uniKey.setTradeNo(this.tradeNo);
        uniKey.setBusinessPlatformId(this.businessPlatformId);

        return uniKey;
    }

    public String getStartSiteStr()
    {
        if (StringUtils.isNotBlank(this.startSiteStr))
        {
            return this.startSiteStr;
        }
        if (this.startSite == null)
        {
            return null;
        }
        StringBuilder siteStr = new StringBuilder();
        siteStr.append(startSite.getProvince()==null?"":startSite.getProvince()).append(",");
        siteStr.append(startSite.getCity()==null?"":startSite.getCity()).append(",");
        siteStr.append(startSite.getDistrict()==null?"":startSite.getDistrict()).append(",");
        siteStr.append(startSite.getTownship()==null?"":startSite.getTownship()).append(",");
        siteStr.append(startSite.getAddress()==null?"":startSite.getAddress());
        this.startSiteStr = siteStr.toString();
        return this.startSiteStr;
    }

    public String getEndSiteStr()
    {
        if (StringUtils.isNotBlank(this.endSiteStr))
        {
            return this.endSiteStr;
        }
        if (this.endSite == null)
        {
            return null;
        }
        StringBuilder siteStr = new StringBuilder();
        siteStr.append(endSite.getProvince()==null?"":endSite.getProvince()).append(",");
        siteStr.append(endSite.getCity()==null?"":endSite.getCity()).append(",");
        siteStr.append(endSite.getDistrict()==null?"":endSite.getDistrict()).append(",");
        siteStr.append(endSite.getTownship()==null?"":endSite.getTownship()).append(",");
        siteStr.append(endSite.getAddress()==null?"":endSite.getAddress());
        this.endSiteStr = siteStr.toString();
        return this.endSiteStr;
    }

    public Site getStartSite()
    {
        return this.getSite(this.startSite, this.startSiteStr);
    }

    public Site getEndSite()
    {
        return this.getSite(this.endSite, this.endSiteStr);
    }

    private Site getSite(Site site, String siteStr)
    {
        if (site != null)
        {
            return site;
        }
        site = new Site();
        if (StringUtils.isBlank(siteStr))
        {
            return site;
        }
        String[] adds = siteStr.split(",", 5);
        if (adds.length < 5)
        {
            site.setProvince(StringUtils.EMPTY);
            site.setCity(StringUtils.EMPTY);
            site.setDistrict(StringUtils.EMPTY);
            site.setTownship(StringUtils.EMPTY);
            site.setAddress(siteStr);
            return site;
        }

        site.setProvince(adds[0]);
        site.setCity(adds[1]);
        site.setDistrict(adds[2]);
        site.setTownship(adds[3]);
        site.setAddress(adds[4]);
        return site;
    }

    @Data
    public class UniKey
    {
        /**
         * 交易订单号
         */
        private String tradeNo;
        /**
         * 平台ID(交易所属行业平台身份)
         */
        private String businessPlatformId;
    }

    @Data
    @ToString
    public static class Site
    {
        /**
         * 省名称
         */
        private String province;
        /**
         * 市名称
         */
        private String city;
        /**
         * 区名称
         */
        private String district;
        /**
         * 乡镇/街道名称
         */
        private String township;
        /**
         * 详细地址
         */
        private String address;
    }
}

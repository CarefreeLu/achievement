package com.nuonuo.trade.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 类描述：TODO
 *
 * @author Jianhui Lu
 * @date 2019/9/24 14:14
 */
@Data
public class Site
{
    /**
     * 省名称
     */
    @JSONField(ordinal = 1)
    private String province;
    /**
     * 市名称
     */
    @JSONField(ordinal = 2)
    private String city;
    /**
     * 区名称
     */
    @JSONField(ordinal = 3)
    private String district;
    /**
     * 乡镇/街道名称
     */
    @JSONField(ordinal = 4)
    private String township;
    /**
     * 详细地址
     */
    @JSONField(ordinal = 5)
    private String address;
}

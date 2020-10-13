package com.nuonuo.trade.constant;

/**
 * 功能描述：TODO
 *
 * @author Jianhui Lu
 * @createtime 2019/8/5 17:21
 */
public enum BusinessTradeErrorE
{
    E0001("0001","行业或平台编码有误，拒绝服务。"),
    E0002("0002","交易订单号不能为空。"),
    E0003("0003","身份ID或者身份来源为空或者错误，拒绝服务。"),
    E0004("0004","拉取交易数据平台的url配置为空。"),
    E0005("0005","响应报文为空。"),
    E0006("0006","交易数据所属关系已建立。"),
    E0007("0007","拉取的交易数据为空。"),
    E0008("0008","企业税号不能为空。"),
    E0009("0009","企业税号未在诺诺平台注册。"),
    E0010("0010","尚未提供该行业平台交易数据服务，请检查行业平台码是否正确。"),
    E0011("0011","交易数据异常。"),
    E0012("0012","操作编码为空或错误。"),
    E0013("0013","操作流水号不能为空。"),
    E0014("0014","未创建交易数据索引。"),
    E0015("0015","交易数据加解密失败。"),
    E0016("0016","交易数据缺失或不存在。"),
    E0017("0017","请求和响应的税号不一致。"),
    E0018("0018","加解密类型不在可提供的范围内。"),
    E0019("0019","需要加解密报文时，必须提供交易数据关联的企业税号。"),
    E0020("0020","推送的交易数据为空。"),
    E0021("0021","未建立所属关系的交易数据不接收处理");

    /**
     * 错误码
     */
    public final String code;
    /**
     * 错误描述
     */
    public final String description;

    BusinessTradeErrorE(String code, String description)
    {
        this.code = code;
        this.description = description;
    }
}
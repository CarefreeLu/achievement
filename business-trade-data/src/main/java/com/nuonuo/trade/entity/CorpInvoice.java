package com.nuonuo.trade.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 企业开票资质
 *
 * Created by fangxm on 17-1-9.
 */
@Data
@ToString
public class CorpInvoice implements Serializable {

    /**
     * 余量不足
     */
    public static final byte ST_NOT_ENOUGH = 0;
    /**
     * 已提醒
     */
    public static final byte ST_REMINDED = 1;

    /**
     * 余量充足
     */
    public static final byte ST_ENOUGH = 2;

    /**
     * 开票成功发短信
     */
    public static final int SEND_SMS_AFTER_INVOICE = 0;

    /**
     * 签章成功发短信
     */
    public static final int SEND_SMS_AFTER_SIGNATURE = 1;

    /**
     * 不发送短信
     */
    public static final int NO_SEND_SMS = -1;

    /**
     * 不发送邮件
     */
    public static final int NO_SEND_MAIL = 0;

    /**
     * 签章成功发邮件
     */
    public static final int SEND_MAIL_AFTER_SIGNATURE = 1;

    /**
     * 数据库自增主键
     */
    private Integer autoId;

    /**
     * 数据库记录ID
     */
    private String id = "";

    /**
     * 纳税人名称
     */
    private String taxpayer = "";

    /**
     * 税号
     */
    private String taxNo = "";

    /**
     * 纳税人授权码
     */
    private String authorizationCode = "";

    /**
     * 电商平台编码
     */
    private String username = "";

    /**
     * 电商平台密码
     */
    private String password = "";

    /**
     * 请求发出方代码
     */
    private String requestCode = "";

    /**
     * 身份(加密)
     */
    private String identity = "";

    /**
     * 企业电话
     */
    private String phone = "";

    /**
     * 企业状态（0:关闭 1:使用中）
     */
    private int state = 1;

    /**
     * 开票服务器地址
     */
    private String invoiceServerUrl = "";

    /**
     * 短信发送时机，0开票成功发短信，1:签章成功发短信
     */
    private short smsSendingTime = SEND_SMS_AFTER_INVOICE;

    /**
     * 短信套餐类型，0无限制，1包月，2包年
     */
    private short smsPackageType = 0;

    /**
     * 短信套餐条数
     */
    private Integer messageNumber;

    /**
     * 短信通知号码
     */
    private String notifyPhoneForSmsPackage = "";

    /**
     * 短信到期时间
     */
    private Date smsExpireDate;

    /**
     * 小于N条提醒
     */
    private String notifyRemindNumber;

    /**
     * 到期前N天提醒
     */
    private Integer notifyExpireDays;

    /**
     * 发票余量
     */
    private Integer invoiceRemainder;

    /**
     * 发票余量提醒手机
     */
    private String notifyPhoneForInvoiceRemainder = "";

    /**
     * 发票余量提醒数
     */
    private String notifyForInvoiceRemainder;

    /**
     * 发票余量提醒状态(0:余票不足 1:已提醒 2:余票充足)
     */
    private String notifyStatusForInvoiceRemainder = "";

    /**
     * 短信模版标记(0:默认模版 1:jpg图片模版)
     */
    private String smsTemplateForInvoiceRemainder = "";

    /**
     * 开票平台(0:51平台 1:省平台)
     */
    private String invoicePlat = "";

    /**
     * 开票模式(1:开票服务器开票; 2:51平台开票)
     */
    private int invoiceMode = 1;

    /**
     * 签章模式（2：51签章，3：单机版签章）
     */
    private short signatureMode = 2;

    /**
     * 客户端解密cer文件
     */
    private String clientDecryptCer = "";

    /**
     * 服务端解密pfx文件
     */
    private String serverDecryptPfx = "";

    /**
     * 服务端解密key文件
     */
    private String serverDecryptKey = "";

    /**
     * 企业回调通知地址
     */
    private String notifyCallbackUrl = "";

    /**
     * 企业appId
     */
    private String appId = "";

    /**
     * 企业appSecret
     */
    private String appSecret = "";

    /**
     * 通道类型
     */
    private short  channelType;

    /**
     * 开票服务器类型（0:windows系统;1:linux系统(ARM9);2:单机版;3:A9）
     */
    private String kpServerOs = "";

    /**
     * 单次开票最大限额
     */
    private BigDecimal singleBillingLimit = new BigDecimal("0");

    /**
     * 开票软件的最新版本号
     */
    private String softVersion="";

    /**
     * 开票软件的最新版本下载地址
     */
    private String softUrl = "";

    /**
     * 发票卡券模板
     */
    private String cardId = "";

    /**
     * 是否可以重复冲红(0：不可以 1：可以)
     */
    private short isRepeatedRed;


    /**
     * 二维码有效时间类型  0: 时间段（小时），1：按时间点（当月月底）
     */
    private int ewmEffectiveFlag;

    /**
     * 扫码开票二维码有效时间，单位小时
     */
    private double ewmEffectiveTime;

    /**
     * 是否删除无效二维码； 0 ：不允许删除，1：允许删除
     */
    private int ewmDelInvalid;

    /**
     * 二维码失效后自动开票；0：不自动开票，1：自动开票
     */
    private int ewmAutoInvoice;

    /**
     * 二维码自动开票 发票抬头
     */
    private String ewmInvoiceTitle;

    /**
     * 税号变更前的税号
     */
    private String oldTaxNo;

    /**
     * 所属服务单位id
     */
    private String companyid;

    /**
     * 手机号是否必填 0:不必填,1:必填
     */
    private short phoneRequired;

    /**
     * 发票预览模板0：图片模板，1：文字模板'
     */
    private int invoicePreviewTemplate;

    /**
     * 发票种类   p--电子增值税普通发票，c--增值税普通发票(纸票)，s--增值税专用发票
     */
    private String invoiceLine = "p";
    /**
     * 追加商品编码简称标记：0不追加，1追加
     */
    private short addGoodsCodeAbb;
    /**
     * 默认分机号
     */
    private String fjh;
    
    /**
	 * 企业类型 0：一般企业 ，1：进出口企业
	 */
	private String enterpriseType;
    /**
     * 初始批次 开票员数量
     */
    private Integer defaultClerkNum;


    /**
     * 添加人
     */
    private String addUser;
    /**
     * 添加时间
     */
    private Date addTime;
    /**
     * 修改人
     */
    private String editUser;
    /**
     * 修改时间
     */
    private Date editTime;
    /**
     * 客户端解密key文件
     */
    private String clientDecryptKey;
    /**
     * 注册码
     */
    private String zcm;
    
    /**
     * 成品油标志：0非成品油，1成品油，默认为0
     */
    private String cpybz = "0";


    private String accountManage;//客户经理
    private String accountManageNO;//工号
    private String accountManageId;//客户经理id
    private int issuer;//发行方
    /**
     *拥有分机号配置标识 0：没有分机号配置，1：有分机号配置
     */
    private Integer hasExtConfig = 0;

    /**
     * 客户来源，1：数衍科技，2：龙图，3：海通，4：其他
     */
    private Integer customerSource;

    /**
     * 分平台加密 key(目前主要用于宁波航信平台)
     */
    private String subplatEncryptKey;

    /**
     * 分平台签名key(目前主要用于宁波航信平台)
     */
    private String subplatSignatureKey;

    /**
     * 分平台编码
     */
    private String subplatId;
    /**
     * 发票存储费控制，0：不控制使用 ，1：控制使用
     */
    private Short invoiceStorageChargesLimited;

    /**
     * 短信使用控制，0：不控制使用 ，1：控制使用
     */
    private Short msgLimited;
    /**
     * 合同开始时间
     */
    private String htglStartTime;
    /**
     * 合同到期时间
     */
    private String htglEndTime;
    /**
     * 支持静态扫码开票，0：否 ，1：是
     */
    private int staticScan;
    /**
     * 是否包含购方信息 0： 否 ，1：是
     */
    private int includeBuyerInfo;
    /**
     * pfx文件是否上传
     */
    private String  pfxIsUpload;

    /**
     * cer文件是否上传
     */
    private String cerIsUpload;
    /**
     * 合同到期后 0： 不关闭电子发票资质 ，1：关闭电子发票资质
     */
    private int contractExpire ;
    /**
     * 用户付费状态标志，0：付费用户，1：免费试用用户
     */
    private int payStatus;

    /**
     * 邮箱交付时机:0,不发送  1,发送（签章后发送）2,固定地址发送
     */
    private int emailSendingTime=1;

    /**
     * 交付邮箱:0:不必填,1:必填
     */
    private int emailRequired;

    /**
     * 邮箱地址
     */
    private String  emailAddress;

    /**
     * 冲红时备注是否追加蓝票信息:0:否,1:是
     */
    private String isAppend;

    /**
     * 发票是否允许拆分（0：不允许，1：允许）
     */
    private int allowSplit;

    /**
     * 提醒手机号
     */
    private String txsj;

    /**
     * 提醒邮箱
     */
    private String txemail;

    //行业 id
    private String industryFirst = "";
    private String industrySecond = "";
    private String industryThird = "";

    /**
     * 代开标志
     */
    private int dkbz;


    /**
     * 邮件交付模板
     */
    private int emailTemplateFlag;


    /**
     * 提交成功后回调标识 0： 否，1：是
     */
    private int invoiceSubmitCallback;

    /**
     * 机柜编号
     */
    private String equipmentCabinetId = "";

    /**
     * 税盘证书口令
     */
    private String taxMachinePwd;

    /**
     * 是否提供发票高级信息
     */
    private int isOfferInvoiceAdvanceInfo;
    /**
     * 是否需要发票明细
     */
    private int isOfferInvoiceDetail;

    /**
     * 点下户标识
     */
    private Integer underPoint;

//    /**
//     * 纸票PDF（可套打）
//     */
//    private Integer paperExcludeBaseMap;
    /**
     * 纸票PDF（含底图）
     */
    private int paperIncludeBaseMap;
//    /**
//     * 纸票盖章（含底图）
//     */
//    private Integer paperPrintWithStamp;

//    /**
//     * 企业发票专用章
//     */
//    private String  paperStamp;

    /**
     * 是否可隐藏编码表版本号, 0 ： 否，1：是
     */
    private int hideBbh;

    /**
     * 税盘口令 （百旺使用）
     */
    private String taxPlatPwd;

    /**
     * 是否可合并开票，2019-12-24需求
     * 1: 可合并开票  0: 不可合并开票
     */
    private int mergeOrder;

    /**
     * ukey版签章ofd文件下载地址前缀
     */
    private String ukeyDownloadOfdPrefix;



    @Data
    public static class SignatureMode{
        public static final SignatureMode ON_PLAT = new SignatureMode("2","服务器版");
        public static final SignatureMode STAND_ALONE = new SignatureMode("3","单机版");
        public static final SignatureMode BOX = new SignatureMode("5","51盒子版");
        public static final SignatureMode NUO_NUO = new SignatureMode("6","诺诺签章");
        public static final SignatureMode U_KEY = new SignatureMode("7","公共服务平台签章");

        public SignatureMode(String id, String text) {
            this.id = id;
            this.text = text;
        }

        private String id ;

        private String text ;
    }
}

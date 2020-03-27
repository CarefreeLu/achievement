package com.nuonuo.trade.constant;

public class LogCodeConstant
{

    public static final String INVOICE_LOG_ID = "[SID]:";
    public static final String INVOICE_ORDERNO = "ORDER_NO_";
    public static final String LOG_MSG = "[MSG]:";
    public static final String TOPIC_LOG_ID = "[TOPIC]:";

    /**
     * 统计用关键字
     */
    public static final String BEGIN = "begin";
    public static final String END = "end";

    public static final String REQUEST = "request";
    public static final String RESPONSE = "response";

    public static final String START = "start";
    public static final String FINISH = "finish";

    public static final String STATISTIC = "statistic";

    public static final String EXCEPTION = "exception";
    public static final String CALL = "call";
    public static final String PROCESS = "PROCESS";
    public static final String PARSE = "PARSE";
    /**
     * 单机版拉取开票报文
     */
    public static final String GET_KPXML = "GET_KPXML";

    /**-------------关键字---------------**/

    /**
     * baseService公用方法调用
     */
    public static final String INTERFACE_REQUEST = "INTERFACE_REQUEST";

    /**
     * http处理异常
     */
    public static final String HTTP_CLIENT = "HTTP_CLIENT";

    /**
     * http连接池处理异常
     */
    public static final String HTTP_CLIENT_POOL = "HTTP_CLIENT_POOL";


    /**
     * 重推
     */
    public static final String RETRY = "RETRY";

    /**
     * 自动删除过期后超过配置时间的二维码
     */
    public static final String EWM_AUTODEL_TASK = "EWM_AUTODEL_TASK";

    /**
     * redis key过期
     */
    public static final String EXPIRED_CHANNEL = "EXPIRED_CHANNEL";

    /**
     * 内部驱动
     */
    public static final String INTERNAL_DRIVE = "INTERNAL_DRIVE";

    /**
     * Xss过滤
     */
    public static final String XSS_FILTER = "XSS_FILTER";

    /**
     * 发票查验
     */
    public static final String INVOICE_CHECK = "INVOICE_CHECK";

    /**
     * 单机版开票
     */
    public static final String INVOICE_ALONE = "INVOICE_ALONE";

    /**
     * 单机版imid上报
     */
    public static final String INVOICE_ALONE_REPORT = "INVOICE_ALONE_REPORT";

    /**
     * 自动开票任务
     */
    public static final String INVOICE_AUTO = "INVOICE_AUTO";

    /**
     * 批量导入数据错误清除任务
     */
    public static final String IMPORT_ERROR_CLEAR = "IMPORT_ERROR_CLEAR";

    /**
     * 获取汇率任务
     */
    public static final String EXCHANGE_RATE = "EXCHANGE_RATE";

    /**
     * 发票余量任务
     */
    public static final String INVOICE_REMAINDER = "INVOICE_REMAINDER";

    /**
     * 短信余量任务
     */
    public static final String SMS_REMAINDER = "SMS_REMAINDER";

    /**
     * 发票余量任务
     */
    public static final String INVOICE_REMAINDER_BOX = "INVOICE_REMAINDER_BOX";

    /**
     * 单机版无响应数据处理
     */
    public static final String INVOICE_EXCEPTION_HANDLE = "INVOICE_EXCEPTION_HANDLE";

    /**
     * 签章
     */
    public static final String SIGNATURE = "SIGNATURE";

    /**
     * 签章
     */
    public static final String SIGNATURE_BOX = "SIGNATURE_BOX";

    /**
     * 51云开票
     */
    public static final String INVOICE_CLOUD = "INVOICE_CLOUD";

    /**
     * 四川迪孚开票
     */
    public static final String INVOICE_SICHUAN_DIFU = "INVOICE_SICHUAN_DIFU";

    /**
     * 开票请求
     */
    public static final String INVOICE_REQUEST = "INVOICE_REQUEST";

    /**
     * 邮箱交付
     */
    public static final String INVOICE_EMAIL_DELIVER = "EMAIL_DELIVER";

    /**
     * 短信交付
     */
    public static final String INVOICE_PHONE_DELIVER = "PHONE_DELIVER";

    /**
     * 企业接口交付（开放平台或直调）
     *
     */
    public static final String INVOICE_INTERFACE_DELIVER = "INVOICE_INTERFACE_DELIVER";

    /**
     * 第三方接口交付（开放平台）
     *
     */
    public static final String ISV_INVOICE_CARD = "ISV_INVOICE_CARD";

    /**
     * 支付宝发票管家交付
     */
    public static final String INVOICE_FPGJ_DELIVER = "FPGJ_DELIVER";

    /**
     * 发票数据交付支付宝蚂蚁区块链
     */
    public static final String INVOICE_ANT_BLOCK_CHAIN_DELIVER = "ANT_BLOCK_CHAIN_DELIVER";

    /**
     * 发票申请结果通知(发票管家)
     */
    public static final String INVOICE_APPLY_RESULT_FPGJ = "INVOICE_APPLY_RESULT_FPGJ";

    /**
     * 发票申请结果通知(蚂蚁区块链)
     */
    public static final String INVOICE_APPLY_RESULT_ANT_BLOCK_CHAIN = "INVOICE_APPLY_RESULT_ANT_BLOCK_CHAIN";

    /**
     * 支付宝接口通知
     */
    public static final String INVOICE_ALIPAY_NOTIFY = "ALIPAY_NOTIFY";

    /**
     * 微信卡包交付
     */
    public static final String INVOICE_WEIXIN_DELIVER = "WEIXIN_DELIVER";

    /**
     * 微信token
     */
    public static final String INVOICE_WEI_XIN_ACCESS_TOKEN = "INVOICE_WEI_XIN_ACCESS_TOKEN";

    /**
     * 微信js_api_ticket
     */
    public static final String INVOICE_WEI_XIN_JS_API_TICKET = "INVOICE_WEI_XIN_JS_API_TICKET";

    /**
     * 微信api_ticket
     */
    public static final String INVOICE_WEI_XIN_API_TICKET = "INVOICE_WEI_XIN_API_TICKET";

    /**
     * 密钥中心
     */
    public static final String SECRET_KEY_CENTER = "SECRET_KEY_CENTER";

    /**
     * 诺言交付
     */
    public static final String INVOICE_NUOYAN_DELIVER = "NUOYAN_DELIVER";

    /**
     * 支付宝开票
     */
    public static final String INVOICE_ALIPAY = "INVOICE_ALIPAY";

    /**
     * 支付开票（新版）
     */
    public static final String PAY_INVOICE = "PAY_INVOICE";

    /**
     * A9纸电一体开票
     */
    public static final String A9_PAPER_ELECTRICITY_INTEGRATION = "A9_PAPER_ELECTRICITY_INTEGRATION";

    /**
     * 组装开票请求
     */
    public static final String ASSEMBLE_INVOICE = "ASSEMBLE_INVOICE";

    /**
     * 红字发票申请
     */
    public static final String INVOICE_RED_APPLY = "INVOICE_RED_APPLY";

    /**
     * 查询红字发票申请
     */
    public static final String QUERY_INVOICE_RED_APPLY = "QUERY_INVOICE_RED_APPLY";
    /**
     * 发票信息同步分库失败
     */
    public static final String INVOICE_SYNC_FAILURE = "INVOICE_SYNC_FAILURE";

    /**
     * 发票明细信息同步分库失败
     */
    public static final String INVOICE_DETAIL_SYNC_FAILURE = "INVOICE_DETAIL_SYNC_FAILURE";

    /**
     * 发票高级信息同步分库失败
     */
    public static final String INVOICE_ADVANCE_SYNC_FAILURE = "INVOICE_ADVANCE_SYNC_FAILURE";

    /**
     * kafka 消费者
     */
    public static final String KAFKA_CONSUMER_RECEIVE = "CONSUMER_RECEIVE";

    /**
     * kafka 生产者
     */
    public static final String KAFKA_PRODUCER_SEND = "PRODUCER_SEND";

    /**
     * 纸票作废
     */
    public static final String INVALID_INVOICE = "INVALID_INVOICE";

    /**
     * 查询发票库存
     */
    public static final String QUERY_INVENTORY = "QUERY_INVENTORY";

    /**
     * 查询报税信息
     */
    public static final String QUERY_REPORT_TAX = "QUERY_REPORT_TAX";

    /**
     * 查询发票信息
     */
    public static final String INVOICE_QUERY = "INVOICE_QUERY";

    /**
     * 更新企业资质
     */
    public static final String UPDATE_CORP_INVOICE = "UPDATE_CORP_INVOICE";

    /**
     * 后台重推
     */
    public static final String REDO_OSS = "REDO_OSS";

    /**
     * 发票状态处理
     */
    public static final String INVOICE_STATE = "INVOICE_STATE";

    /**
     * 扫码开票
     */
    public static final String INVOICE_SCAN = "INVOICE_SCAN";

    /**
     * 德士打印
     */
    public static final String PRINT_DESHI = "PRINT_DESHI";

    /**
     * 同步盒子配置
     */
    public static final String SYNCBOX_CONFIG = "SYNCBOX_CONFIG";

    /**
     * 盒子配置
     */
    public static final String BOX_CONFIG = "BOX_CONFIG";

    /**
     * 盒子登录任务
     */
    public static final String LOGIN_BOX = "LOGIN_BOX";

    /**
     * 用户登录
     */
    public static final String USER_LOGIN = "USER_LOGIN";

    /**
     * 获取用户信息
     */
    public static final String USER_INFO = "USER_INFO";

    /**
     * 访问控制
     */
    public static final String ACCESS_FILTER = "ACCESS_FILTER";

    /**
     * 短信库存
     */
    public static final String SMS_REPERTORY = "SMS_REPERTORY";

    /**
     * 分平台同步
     */
    public static final String SUB_PLATFORM_UPSYNC = "SUB_PLATFORM_UPSYNC";

    /**
     * 分平台同步
     */
    public static final String CORP_INVOICE_TASK = "CORP_INVOICE_TASK";
    /**
     * 发票报销
     */
    public static final String REIMURSEMENT = "Reimursement";

    /**
     * 税率升级任务
     */
    public static final String TAX_RATE_TASK = "TAX_RATE_TASK";

    /**
     * 税盘信息任务
     */
    public static final String TAX_DISK_TASK = "TAX_DISK_TASK";

    /**
     * 其它异常
     */
    public static final String OTHER_ERROR = "OTHER_ERROR";
    public static final String COMMON_MODULE = "COMMON_MODULE";



    /**
     * 分平台同步信息
     */
    public static final String SUB_UPSYNC = "SUB_UPSYNC";

    /**
     * 聚合支付开票
     */
    public static final String INTEGRATION_KP_INFO = "INTEGRATION_KP_INFO";

    /**
     * 处理作废无响应异常任务
     */
    public static final String INVALID_EXCEPTION_HANDLE = "INVALID_EXCEPTION_HANDLE";

    /**
     * 山西领航开票信息
     */
    public static final String SANXI_INVOICE = "SANXI_INVOICE";

    /**
     * 客户端获取企业身份id异常
     */
    public static final String CLIENT_GET_INDETITY = "CLIENT_GET_INDETITY";

    /**
     * 发票归集
     */
    public static final String INVOICE_COLLECTION = "INVOICE_COLLECTION";

    /**
     * 开票模块启动参数
     */
    public static final String INVOICE_START_PARAMS = "INVOICE_START_PARAMS";

    /**
     * 客户端安装
     */
    public static final String CLIENT_INSTALL = "CLIENT_INSTALL";

    /**
     * 描述:开放平台
     */
    public static final String ACCESS_PLATFORM = "ACCESS_PLATFORM";

    /**
     * 部分共用方法-使用方法名
     */
    public static final String GET_CORPINVOICE_BY_TAXNO = "GET_CORPINVOICE_BY_TAXNO";
    public static final String SEND_MESSAGE = "SEND_MESSAGE";
    public static final String TASK_RUN = "TASK_RUN";

    public static final String INVOICE_SPLIT = "INVOICE_SPLIT";

    /**
     * 描述:个人发票定时任务归集
     *
     */
    public static final String TASK_COLLECTION_PERSONAL = "TASK_COLLECTION_PERSONAL";

    /**
     * 描述:归集功能-定时任务去用户中心拉取新注册用户
     *
     */
    public static final String TASK_COLLECTION_POLL_USER = "TASK_COLLECTION_POLL_USER";

    /**
     * 支付宝绑定手机号，历史数据交付
     *
     */
    public static final String TASK_PHONE_ALIPAY_HISTORY = "TASK_PHONE_ALIPAY_HISTORY";

    public static final String TASK_REPAIR_STATISTIC_INVOICE_STATUS = "TASK_REPAIR_STATISTIC_INVOICE_STATUS";

    /**
     * 描述：项目版本写入版本文件
     */
    public static final String APPLICATION_READY_EVENT_LISTENER = "APPLICATION_READY_EVENT_LISTENER";

    /**
     * 生成pdf
     */
    public static final String GENERATE_INVOICE_PDF = "GENERATE_INVOICE_PDF";

    /**
     * 获取企业身份信息
     */
    public static final String CORPORATION_INFO = "CORPORATION_INFO";

    /**
     * ES数据同步
     */
    public static final String ELASTIC_SEARCH_PST = "ELASTIC_SEARCH_PST";

    /**
     * 分表路由关系
     */
    public static final String SHARDING_ROUTE = "SHARDING_ROUTE";

    /**
     * 数据迁移
     */
    public static final String DATA_MIGRATION = "DATA_MIGRATION";


    /**
     * 销方发票按月统计定时任务
     */
    public static final String MONTH_STATISTIC_TASK = "MONTH_STATISTIC_TASK";

    /**
     * 热表清理定时任务
     */
    public static final String INVOICE_RECENT_CLEAN_TASK = "INVOICE_RECENT_CLEAN_TASK";

    /**
     * 失败同步记录重试任务
     */
    public static final String INVOICE_FAILURE_DATA_SYNC_TASK = "INVOICE_FAILURE_DATA_SYNC_TASK";

    /**
     * 统计开票信息
     */
    public static final String STATISTIC_INVOICE_INFO = "STATISTIC_INVOICE_INFO";

    /**
     * 机柜上报税清卡接口
     */
    public static final String REPORT_TAX_CLEAR_CARD = "REPORT_TAX_CLEAR_CARD";

    /**
     * 机柜上报发票库存接口
     */
    public static final String REPORT_STOCK = "REPORT_STOCK";

    /**
     * 描述:税盘同步清卡信息
     */
    public static final String TAX_DISK_SYNC_CLEAR_CARD = "TAX_DISK_SYNC_CLEAR_CARD";

    /**
     * 描述:税盘报税清卡
     */
    public static final String TAX_DISK_CLEAR_CARD = "TAX_DISK_CLEAR_CARD";

    /**
     * 描述:税盘下载发票
     */
    public static final String TAX_DISK_DOWNLOAD_INVOICE = "TAX_DISK_DOWNLOAD_INVOICE";

    /**
     * 描述:税盘同步库存信息
     */
    public static final String TAX_DISK_SYNC_STOCK = "TAX_DISK_SYNC_STOCK";

    /**
     * 接口调用监控TAG
     */
    public static final String REQUEST_MONITOR = "REQUEST_MONITOR";

    /**
     * 代开备注信息解析
     */
    public static final String PROXY_REMARK_ANALYSIS = "PROXY_REMARK_ANALYSIS";

    /**
     * 工单系统功能
     */
    public static final String WORK_ORDER = "WORK_ORDER";

    /**
     * 增值服务
     */
    public static final String INVOICE_VALUE_ADDED = "INVOICE_VALUE_ADDED";
    /**
     * 发票webservice代理
     */
    public static final String INVOICE_WS_AGENT =  "INVOICE_WS_AGENT";

    /**
     * 微信获取union_id
     */
    public static final String WEIXIN_UNION_ID = "WEIXIN_UNION_ID";

    /**
     * 支付宝商家入驻
     */
    public static final String ALIPAY_ENTRY = "ALIPAY_ENTRY";

    /**
     * 限流日志
     */
    public static final String RATE_LIMIT = "RATE_LIMIT";

    /**
     * 消息中心
     */
    public static final String MESSAGE_CENTER = "MESSAGE_CENTER";

    /**
     * 最大并发线程数
     */
    public static final String THREAD_COUNT = "THREAD_COUNT";

    /**
     * 发票按税率统计功能
     */
    public static final String INVOICE_STATISTICS = "INVOICE_STATISTICS";

    public static final String INVOICE_DATA_RECEIVE = "InvoiceDataReceive";

    /**
     * redis 异常
     */
    public static final String REDIS_ERROR = "redis_error";

    /**
     * 操作ftp服务器异常
     */
    public static final String FTP_ERROR = "FTP_ERROR";

    /**
     * 访问小程序服务异常
     */
    public static final String XCX_ERROR = "XCX_ERROR";

    /**
     * 宁波定时任务
     */
    public static final String NING_BO_TASK = "NING_BO_TASK";

}

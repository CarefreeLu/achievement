package com.nuonuo.trade.util;

import com.nuonuo.trade.constant.LogCodeConstant;

/**
 * //todo 为了减少依赖牵扯，特将方法置为空实现
 * 基础服务类
 */
public class LogUtils
{
    /**
     * 本地输出日志-debug
     * @param message 日志信息
     */
    public static void outLogDebugLocal(String message){

    }

    /**
     * 本地输出日志-info
     * @param message 日志信息
     */
    public static void outLogInfoLocal(String message){

    }

    /**
     * 本地输出日志-error
     * @param message 日志信息
     */
    public static void outLogErrorLocal(String message,Throwable throwable){

    }

    /**
     * 输出日志-info
     * @param guid 发票请求流水号，没有的放特殊号
     * @param userId 用户ID或者税号
     * @param ip 访问ip
     * @param module 行为可放方法名称等
     * @param action 可放订单号,请求参数等关键信息
     * @param message 日志详情
     */
    public static void outLogInfo(String guid,String userId,String ip,String module,String action,String message,String remark){

        outNuonuoLogInfo(guid,ip,userId,module,remark,action,message);
    }

    /**
     * 输出日志-info
     * @param guid 发票请求流水号，没有的放特殊号
     * @param userId 用户ID或者税号
     * @param module 行为可放方法名称等
     * @param action 可放订单号,请求参数等关键信息
     * @param message 日志详情
     */
    public static void outLogInfo(String guid,String userId,String module,String action,String message){
        outLogInfo( guid, userId, null, module, action, message, null);
    }

    /**
     * 输出日志-info
     * @param guid 发票请求流水号，没有的放特殊号
     * @param userId 用户ID或者税号
     * @param module 行为可放方法名称等
     * @param action 可放订单号,请求参数等关键信息
     * @param message 日志详情
     */
    public static void outLogInfoBySid(String guid,String userId,String module,String action,String message){

        outNuonuoLogInfo(guid,null,userId,module,null,action,message);
    }

    /**
     * 输出日志-error
     * @param guid 发票请求流水号，没有的放特殊号
     * @param userId 用户ID或者税号
     * @param ip 访问ip
     * @param module 行为可放方法名称等
     * @param action 可放订单号,请求参数等关键信息
     * @param remark 备注扩展字段
     * @param message 日志详情
     * @param throwable 异常对象
     */
    public static void outLogError(String guid,String userId,String ip,String module,String action,String message,String remark,Throwable throwable){

        outNuonuoLogError(guid,ip,userId,module,remark,action,message,throwable);
    }

    /**
     * 输出日志-error
     * @param guid 发票请求流水号，没有的放特殊号
     * @param userId 用户ID或者税号
     * @param module 行为可放方法名称等
     * @param action 可放订单号,请求参数等关键信息
     * @param message 日志详情
     * @param throwable 异常对象
     */
    public static void outLogError(String guid,String userId,String module,String action,String message,Throwable throwable){
        outLogError(  guid, userId, null, module, action, message, null, throwable);
    }

    /**
     * 输出日志-error
     * @param guid 发票请求流水号，没有的放特殊号
     * @param userId 用户ID或者税号
     * @param module 行为可放方法名称等
     * @param action 可放订单号,请求参数等关键信息
     * @param message 日志详情
     * @param throwable 异常对象
     */
    public static void outLogErrorBySid(String guid,String userId,String module,String action,String message,Throwable throwable){

        outNuonuoLogError(guid,null,userId,module,null,action,message,throwable);
    }



    /**
     * 输出日志-info
     * @param guid 发票请求流水号，没有的放特殊号
     * @param userId 用户ID或者税号
     * @param ip 访问ip
     * @param module 行为可放方法名称等
     * @param action 可放订单号,请求参数等关键信息
     * @param message 日志详情
     */
    public static void outMessageInLogInfo(String guid,String userId,String ip,String module,String action,String message,String remark){

        outNuonuoLogInfo(guid,ip,userId,module,remark,action,message);
    }

    /**
     * 输出日志-info
     * @param guid 发票请求流水号，没有的放特殊号
     * @param userId 用户ID或者税号
     * @param module 可放订单号,请求参数等关键信息
     * @param message 日志详情
     */
    public static void outMessageInLogInfo(String guid,String userId,String module,String message){
        outMessageInLogInfo( guid, userId, null, module, LogCodeConstant.BEGIN, message, null);
    }

    /**
     * 输出日志-error
     * @param guid 发票请求流水号，没有的放特殊号
     * @param userId 用户ID或者税号
     * @param ip 访问ip
     * @param module 行为可放方法名称等
     * @param action 可放订单号,请求参数等关键信息
     * @param message 日志详情
     * @param throwable 异常对象
     */
    public static void outMessageInLogError(String guid,String userId,String ip,String module,String action,String message,String remark,Throwable throwable){

        outNuonuoLogError(guid,ip,userId,module,remark,action,message,throwable);
    }

    /**
     * 输出日志-error
     * @param guid 发票请求流水号，没有的放特殊号
     * @param userId 用户ID或者税号
     * @param module 行为可放方法名称等
     * @param action 可放订单号,请求参数等关键信息
     * @param message 日志详情
     * @param throwable 异常对象
     */
    public static void outMessageInLogError(String guid,String userId,String module,String action,String message,Throwable throwable){
        outMessageInLogError( guid, userId, null, module, action, message, null, throwable);
    }

    /**
     * message-out输出日志-info
     * @param guid 发票请求流水号，没有的放特殊号
     * @param userId 用户ID或者税号
     * @param ip 访问ip
     * @param module 行为可放方法名称等
     * @param action 可放订单号,请求参数等关键信息
     * @param message 日志详情
     */
    public static void outMessageOutLogInfo(String guid,String userId,String ip,String module,String action,String message,String remark){

        outNuonuoLogInfo(guid,ip,userId,module,remark,action,message);
    }

    /**
     * message-out输出日志-info
     * @param guid 发票请求流水号，没有的放特殊号
     * @param userId 用户ID或者税号
     * @param module 可放订单号,请求参数等关键信息
     * @param message 日志详情
     */
    public static void outMessageOutLogInfo(String guid,String userId,String module,String message){
        outMessageOutLogInfo( guid, userId, null, module, LogCodeConstant.END, message, null);
    }

    /**
     * message-out输出日志-error
     * @param guid 发票请求流水号，没有的放特殊号
     * @param userId 用户ID或者税号
     * @param ip 访问ip
     * @param module 行为可放方法名称等
     * @param action 可放订单号,请求参数等关键信息
     * @param message 日志详情
     * @param throwable 异常对象
     */
    public static void outMessageOutLogError(String guid,String userId,String ip,String module,String action,String message,String remark,Throwable throwable){

    }

    /**
     * message-out输出日志-error
     * @param guid 发票请求流水号，没有的放特殊号
     * @param userId 用户ID或者税号
     * @param module 行为可放方法名称等
     * @param action 可放订单号,请求参数等关键信息
     * @param message 日志详情
     * @param throwable 异常对象
     */
    public static void outMessageOutLogError(String guid,String userId,String module,String action,String message,Throwable throwable){
        outMessageOutLogError( guid, userId, null, module, action, message, null, throwable);
    }

    /**
     * 输出接口信息日志-info
     * @param guid 发票请求流水号，没有的放特殊号
     * @param userId 用户ID或者税号
     * @param ip 访问ip
     * @param module 行为可放方法名称等
     * @param action 可放订单号,请求参数等关键信息
     * @param message 日志详情
     */
    public static void outInterfaceLogInfo(String guid,String userId,String ip,String module,String action,String message,String remark){

        outNuonuoLogInfo(guid,ip,userId,module,remark,action,message);
    }

    /**
     * 输出日志-info
     * @param guid 发票请求流水号，没有的放特殊号
     * @param userId 用户ID或者税号
     * @param action 可放订单号,请求参数等关键信息
     * @param message 日志详情
     */
    public static void outInterfaceLogInfo(String guid,String userId,String action,String message){
        outInterfaceLogInfo( guid, userId, null, LogCodeConstant.INTERFACE_REQUEST, action, message, null);
    }

    /**
     * 输出接口日志-error
     * @param guid 发票请求流水号，没有的放特殊号
     * @param userId 用户ID或者税号
     * @param ip 访问ip
     * @param module 行为可放方法名称等
     * @param action 可放订单号,请求参数等关键信息
     * @param message 日志详情
     * @param throwable 异常对象
     */
    public static void outInterfaceLogError(String guid,String userId,String ip,String module,String action,String message,String remark,Throwable throwable){

        outNuonuoLogError(guid,ip,userId,module,remark,action,message,throwable);
    }

    /**
     * 输出日志-error
     * @param guid 发票请求流水号，没有的放特殊号
     * @param userId 用户ID或者税号
     * @param action 可放订单号,请求参数等关键信息
     * @param message 日志详情
     * @param throwable 异常对象
     */
    public static void outInterfaceLogError(String guid,String userId,String action,String message,Throwable throwable){
        outInterfaceLogError( guid, userId, null, LogCodeConstant.INTERFACE_REQUEST, action, message, null, throwable);
    }


    /**
     * 输出日志到运维平台并更新日志链路信息-info
     */
    private static void outNuonuoLogInfo(String guid, String ip, String userId, String module, String remark, String action, String message){

    }

    /**
     * 输出日志到运维平台并更新日志链路信息-info
     */
    private static void outNuonuoLogError(String guid,String ip,String userId,String module,String remark,
                                          String action,String message,Throwable throwable){

    }

}

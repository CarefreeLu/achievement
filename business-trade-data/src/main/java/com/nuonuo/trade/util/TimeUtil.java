package com.nuonuo.trade.util;
;
import com.nuonuo.trade.constant.LogCodeConstant;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.util.Date;

/**
 * 类描述：TODO
 *
 * @author Jianhui Lu
 * @date 2019/9/12 13:46
 */
public class TimeUtil
{
    /**
     * 标准格式时间，作为首选时间格式
     */
    public static final FastDateFormat STD_DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

    public static final FastDateFormat yyyyMMddHHmmss = FastDateFormat.getInstance("yyyyMMddHHmmss");

    public static final FastDateFormat yyyyMM = FastDateFormat.getInstance("yyyyMM");

    public static final FastDateFormat yyyyMMdd = FastDateFormat.getInstance("yyyyMMdd");

    public static String format2Std(Date date)
    {
        if (date == null)
        {
            return null;
        }

        return STD_DATE_FORMAT.format(date);
    }

    public static Date parseStd(String date)
    {
        try
        {
            return STD_DATE_FORMAT.parse(date);
        }
        catch (Exception e)
        {
            LogUtils.outLogError(null, null, LogCodeConstant.COMMON_MODULE, LogCodeConstant.EXCEPTION, "字符串解析日期错误", e);
            return null;
        }
    }

    /**
     * 时间标准格式化（格式化为yyyyMMddHHmmss）
     *
     * @param date
     * @return
     */
    public static String format2yyyyMMddHHmmss(Date date)
    {
        if (date == null)
        {
            return null;
        }

        return yyyyMMddHHmmss.format(date);
    }

    /**
     * yyyyMMddHHmmss格式转为日期
     *
     * @param date
     * @return
     */
    public static Date parseyyyyMMddHHmmss(String date)
    {
        try
        {
            return yyyyMMddHHmmss.parse(date);
        }
        catch (ParseException e)
        {
            LogUtils.outLogError(null, null, LogCodeConstant.COMMON_MODULE, LogCodeConstant.EXCEPTION, "字符串解析日期错误", e);
            return null;
        }
    }
}

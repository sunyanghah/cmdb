package com.uino.cmdb.config.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类，主要在commons.lang3的日期工具类的基础上继承及整合
 * @author sunYang
 * @date 2021/9/14 10:46
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    /**
     * 以下皆为常用日期格式，如果不够用可自行补充
     */

    public static final String PATTERN_DATE = "yyyy-MM-dd";

    public static final String PATTERN_DATE_2 = "yyyy/MM/dd";

    public static final String PATTERN_DATE_3 = "yyyyMMdd";

    public static final String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";

    public static final String PATTERN_DATETIME_2 = "yyyy/MM/dd HH:mm:ss";

    public static final String PATTERN_DATETIME_MILLI = "yyyy-MM-dd HH:mm:ss SSS";

    public static final String PATTERN_TIME = "HH:mm:ss";

    public static final String PATTERN_TIME_MILLI = "HH:mm:ss SSS";

    public static final String PATTERN_DATETIME_3 = "yyyyMMddHHmmss";

    /**
     * Calendar日期格式化
     * @author sunYang
     * @param calendar
     * @param pattern
     * @return java.lang.String
     * @date 2021/9/14 10:44
     */
    public static String format(Calendar calendar,String pattern){
        return DateFormatUtils.format(calendar,pattern);
    }

    /**
     * Date日期格式化
     * @author sunYang
     * @param date
     * @param pattern
     * @return java.lang.String
     * @date 2021/9/14 10:44
     */
    public static String format(Date date,String pattern){
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * millis日期格式化
     * @author sunYang
     * @param millis
     * @param pattern
     * @return java.lang.String
     * @date 2021/9/14 10:45
     */
    public static String format(long millis,String pattern){
        return DateFormatUtils.format(millis, pattern);
    }

    /**
     * date 格式化成 yyyy-MM-dd
     * @author sunYang
     * @param date
     * @return java.lang.String
     * @date 2021/9/14 11:06
     */
    public static String formatPatternDate(Date date){
        return DateFormatUtils.format(date,PATTERN_DATE);
    }

    /**
     * date 格式化成 yyyy-MM-dd HH:mm:ss
     * @author sunYang
     * @param date
     * @return java.lang.String
     * @date 2021/9/14 11:06
     */
    public static String formatPatternDatetime(Date date){
        return DateFormatUtils.format(date,PATTERN_DATETIME);
    }

    /**
     * 常用的字符串转date
     * @author sunYang
     * @param str
     * @return java.util.Date
     * @date 2021/9/14 11:15
     */
    public static Date parseUsual(String str) throws ParseException {
        return parseDate(str,PATTERN_DATE,PATTERN_DATE_2,PATTERN_DATE_3,PATTERN_DATETIME,PATTERN_DATETIME_2,
                PATTERN_DATETIME_MILLI,PATTERN_TIME,PATTERN_TIME_MILLI,PATTERN_DATETIME_3);
    }

}

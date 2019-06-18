package com.itheima.ssm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class DateUtils {
    //日期转字符串
    public static String date2String(Date date,String patt){
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        String format = sdf.format(date);
        return format;
    }
    //字符串转日期
    public static Date string2Date(String date,String patt) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        Date parse = sdf.parse(date);
        return parse;
    }
}

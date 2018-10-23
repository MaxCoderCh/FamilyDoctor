package com.familydoctor.doctorsubject.YoonaLTsUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    /**
     * 时间超时验证
     *
     * @param date
     * @return i
     */
    public static int timeOutTest(Date date) {
        final long oneDay = 86400000;
        int i;
        long timeNow = new Date().getTime();
        if (timeNow - date.getTime() > oneDay) {
            i = 0;
            return i;
        } else {
            i = 1;
            return i;
        }
    }

    /**
     * String to Date
     *
     * @param string
     * @return date
     */
    public static Date stringToDate(String string) {

        //传入日期格式为19000000格式,转换为1900-00-00
        if (string.length() == 8) {
            StringBuffer stringBuffer = new StringBuffer(string);
            stringBuffer.insert(4, "-");
            stringBuffer.insert(7, "-");
            string = stringBuffer.toString();
        }


        Date date;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            date = simpleDateFormat.parse(string); //toString -->Mon Jan 14 00:00:00 CST 2013
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Date yo String("yyyy-MM-dd")
     *
     * @param date
     * @return string
     */
    public static String dateToString(Date date) {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String string = format.format(date);
        return string;

    }

    /**
     * 添加一年以后的时间
     *
     * @return calendar.toString()
     */
    public static Date nowTimeYearLater() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 1);//增加一年
        return calendar.getTime();
    }

}

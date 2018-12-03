package com.familydoctor.doctorsubject.YoonaLTsUtils;

import javax.validation.constraints.NotNull;
import java.text.*;
import java.util.*;


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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            date = simpleDateFormat.parse(string); //toString -->Mon Jan 14 00:00:00 CST 2013
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Date stringToDate2(String string) {

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
     * Date yo String("yyyy-MM-dd HH:mm:ss")
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
     * A year from now(正数为增加数量,负数为减少数量)
     */
    public static Date nowTimeYearLater() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 1);//增加一年
        return calendar.getTime();
    }

    /**
     * A day from parameter
     */
    public static Date dayAddDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);//增加一天
        return calendar.getTime();
    }

    /**
     * A month from parameter
     */
    public static Date dayAddMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);//增加一月
        return calendar.getTime();
    }

    /**
     * 获取当天0:0:0时刻
     */
    public static Date intradayBegin() {
        Date dateNow = new Date();
        Calendar day = Calendar.getInstance();
        day.setTime(dateNow);
        day.set(Calendar.HOUR_OF_DAY, 0);
        day.set(Calendar.MINUTE, 0);
        day.set(Calendar.SECOND, 0);
        day.set(Calendar.MILLISECOND, 0);
        Date date = day.getTime();

        return date;

    }

    /**
     * 获取传入日期0:0:0时刻
     */
    public static Date parmDateBegin(Date date) {
        Calendar day = Calendar.getInstance();
        day.setTime(date);
        day.set(Calendar.HOUR_OF_DAY, 0);
        day.set(Calendar.MINUTE, 0);
        day.set(Calendar.SECOND, 0);
        day.set(Calendar.MILLISECOND, 0);
        Date resDate = day.getTime();

        return resDate;

    }

    /**
     * 获取当天24:00:00时刻
     */
    public static Date intradayEnd() {
        Date dateNow = new Date();
        Calendar day = Calendar.getInstance();
        day.setTime(dateNow);
        day.set(Calendar.HOUR_OF_DAY, 23);
        day.set(Calendar.MINUTE, 59);
        day.set(Calendar.SECOND, 59);
        day.set(Calendar.MILLISECOND, 999);
        Date date = day.getTime();

        return date;

    }

    /**
     * Returns the days between two dates. Positive values indicate that the
     * second date is after the first, and negative values indicate, well, the
     * opposite. Relying on specific times is problematic.
     *
     * @param early the "first date"
     * @param late  the "second date"
     * @return the days between the two dates
     */
    public static final int daysBetween(Date early, Date late) {

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(early);
        c2.setTime(late);

        return daysBetween(c1, c2);
    }

    /**
     * Returns the days between two dates. Positive values indicate that the
     * second date is after the first, and negative values indicate, well, the
     * opposite.
     *
     * @param early
     * @param late
     * @return the days between two dates.
     */
    public static final int daysBetween(Calendar early, Calendar late) {

        return (int) (toJulian(late) - toJulian(early));
    }

    /**
     * Return a Julian date based on the input parameter. This is based from
     * calculations found at
     * <a href="http://quasar.as.utexas.edu/BillInfo/JulianDatesG.html">Julian
     * Day Calculations (Gregorian Calendar)</a>, provided by Bill Jeffrys.
     *
     * @param c a calendar instance
     * @return the julian day number
     */
    public static final float toJulian(Calendar c) {

        int Y = c.get(Calendar.YEAR);
        int M = c.get(Calendar.MONTH);
        int D = c.get(Calendar.DATE);
        int A = Y / 100;
        int B = A / 4;
        int C = 2 - A + B;
        float E = (int) (365.25f * (Y + 4716));
        float F = (int) (30.6001f * (M + 1));
        float JD = C + D + E + F - 1524.5f;

        return JD;
    }


    /**
     * 获取传入日期0:0:0时刻
     */
    public static Date dayBegin(Date date) {
        Calendar day = Calendar.getInstance();
        day.setTime(date);
        day.set(Calendar.HOUR_OF_DAY, 0);
        day.set(Calendar.MINUTE, 0);
        day.set(Calendar.SECOND, 0);
        day.set(Calendar.MILLISECOND, 0);
        Date resDate = day.getTime();

        return resDate;

    }

    /**
     * 获取传入日期24:00:00时刻
     */
    public static Date dayEnd(Date date) {
        Calendar day = Calendar.getInstance();
        day.setTime(date);
        day.set(Calendar.HOUR_OF_DAY, 23);
        day.set(Calendar.MINUTE, 59);
        day.set(Calendar.SECOND, 59);
        day.set(Calendar.MILLISECOND, 999);
        Date resDate = day.getTime();

        return resDate;

    }

}

package fr.adbonnin.xtra.base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import static fr.adbonnin.xtra.base.XtraLocale.getDefaultFormatLocale;

public final class XtraDates {

    public static final long MILLIS_PER_SECOND = 1000;

    public static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;

    public static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;

    public static final long MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR;

    public static final String DATE_PATTERN = "yyyy-MM-dd";

    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    public static Date parse(String str, String pattern) {
        return parse(str, pattern, null, null);
    }

    public static Date parse(String str, String pattern, Locale locale) {
        return parse(str, pattern, locale, null);
    }

    public static Date parse(String str, String pattern, Date defaultDate) {
        return parse(str, pattern, null, defaultDate);
    }

    public static Date parse(String str, String pattern, Locale locale, Date defaultDate) {

        if (str == null || pattern == null) {
            return defaultDate;
        }

        try {
            return newDate(str, pattern, locale);
        }
        catch (Throwable e) {
            return defaultDate;
        }
    }

    public static Date newDate(String str) {
        return newDate(str, DATE_PATTERN);
    }

    public static Date newDateTime(String str) {
        return newDate(str, DATE_TIME_PATTERN);
    }

    public static Date newDate(String str, String pattern) {
        return newDate(str, pattern, null);
    }

    public static Date newDate(String str, String pattern, Locale locale) {

        if (locale == null) {
            locale = getDefaultFormatLocale();
        }

        try {
            return new SimpleDateFormat(pattern, locale).parse(str);
        }
        catch (ParseException e) {
            throw new IllegalArgumentException("Date can't be parsed; " +
                "str: " + str + ", " +
                "pattern: " + pattern + ", " +
                "locale: " + locale, e);
        }
    }

    public static Date newDate(int year, int month, int dayOfMonth) {
        return new GregorianCalendar(year, month, dayOfMonth).getTime();
    }

    public static Calendar toCalendar(long time) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar;
    }

    public static Calendar toCalendar(Date date) {
        return toCalendar(date.getTime());
    }

    private XtraDates() { /* Cannot be instantiated */ }
}

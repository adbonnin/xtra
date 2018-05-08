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

    public static String format(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    public static Date parse(String str, String pattern) {
        return parse(str, pattern, null, null);
    }

    public static Date parse(String str, String pattern, Locale locale) {
        return parse(str, pattern, locale, null);
    }

    public static Date parse(String str, String pattern, Date nullDefault) {
        return parse(str, pattern, null, nullDefault);
    }

    public static Date parse(String str, String pattern, Locale locale, Date nullDefault) {

        if (str == null) {
            return nullDefault;
        }

        if (locale == null) {
            locale = getDefaultFormatLocale();
        }

        try {
            return new SimpleDateFormat(pattern, locale).parse(str);
        }
        catch (ParseException e) {
            return nullDefault;
        }
    }

    public static Date newDate(String str) {
        final String pattern = DATE_PATTERN;

        final Date date = parse(str, pattern);
        if (date == null) {
            throw new IllegalArgumentException("Date can't be parsed; " +
                "str: " + str + ", " +
                "pattern: " + pattern);
        }

        return date;
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

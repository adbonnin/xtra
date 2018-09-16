package fr.adbonnin.xtra.groovy.base

import fr.adbonnin.xtra.base.XtraDates

final class XtraDatesExtension {

    static String formatToDate(Date date) {
        return XtraDates.format(date, XtraDates.DATE_PATTERN)
    }

    static Calendar toCalendar(Date date) {
        return XtraDates.toCalendar(date)
    }

    static int getYear(Calendar calendar) {
        return calendar.get(Calendar.YEAR)
    }

    static void setYear(Calendar calendar, int year) {
        calendar.set(Calendar.YEAR, year)
    }

    static int getMonth(Calendar calendar) {
        return calendar.get(Calendar.MONTH)
    }

    static void setMonth(Calendar calendar, int month) {
        calendar.set(Calendar.MONTH, month)
    }

    static int getDayOfMonth(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_MONTH)
    }

    static void setDayOfMonth(Calendar calendar, int dayOfMonth) {
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
    }

    static int getMinutes(Calendar calendar) {
        return calendar.get(Calendar.MINUTE)
    }

    static void setMinutes(Calendar calendar, int minutes) {
        calendar.set(Calendar.MINUTE, minutes)
    }

    static int getSeconds(Calendar calendar) {
        return calendar.get(Calendar.SECOND)
    }

    static void setSeconds(Calendar calendar, int seconds) {
        calendar.set(Calendar.SECOND, seconds)
    }

    static int getMilliseconds(Calendar calendar){
        return calendar.get(Calendar.MILLISECOND)
    }

    static void setMilliseconds(Calendar calendar, int milliseconds) {
        calendar.set(Calendar.MILLISECOND, milliseconds)
    }

    private XtraDatesExtension() { /* Cannot be instantiated */ }
}

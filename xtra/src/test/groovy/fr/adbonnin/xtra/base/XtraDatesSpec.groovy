package fr.adbonnin.xtra.base

import spock.lang.Specification

class XtraDatesSpec extends Specification {

    def "should transform to calendar"() {
        given:
        def date = new Date()

        when:
        def calendar = date.toCalendar()

        then:
        calendar.timeInMillis == date.time
    }

    def "should create date from str"() {
        when:
        def date = XtraDates.newDate(dateStr)
        def calendar = date.toCalendar()

        then:
        calendar.get(Calendar.YEAR) == expectedYear
        calendar.get(Calendar.MONTH) == expectedMonth
        calendar.get(Calendar.DAY_OF_MONTH) == expectedDayOfMonth

        where:
        dateStr = '2018-09-08'

        expectedYear = 2018
        expectedMonth = Calendar.SEPTEMBER
        expectedDayOfMonth = 8
    }

    def "should create date time from str"() {
        when:
        def dateTime = XtraDates.newDateTime(dateTimeStr)
        def calendar = dateTime.toCalendar()

        then:
        calendar.get(Calendar.YEAR) == expectedYear
        calendar.get(Calendar.MONTH) == expectedMonth
        calendar.get(Calendar.DAY_OF_MONTH) == expectedDayOfMonth

        calendar.get(Calendar.HOUR_OF_DAY) == expectedHour
        calendar.get(Calendar.MINUTE) == expectedMinutes
        calendar.get(Calendar.SECOND) == expectedSeconds

        where:
        dateTimeStr = '2018-09-08 15:26:37'

        expectedYear = 2018
        expectedMonth = Calendar.SEPTEMBER
        expectedDayOfMonth = 8

        expectedHour = 15
        expectedMinutes = 26
        expectedSeconds = 37
    }

    def "should create new date"() {
        when:
        def date = XtraDates.newDate(year, month, dayOfMonth)

        def cal = Calendar.getInstance()
        cal.setTime(date)

        then:
        cal.get(Calendar.YEAR) == year
        cal.get(Calendar.MONTH) == month
        cal.get(Calendar.DAY_OF_MONTH) == dayOfMonth

        where:
        year = 2018
        month = Calendar.JANUARY
        dayOfMonth = 1
    }
}

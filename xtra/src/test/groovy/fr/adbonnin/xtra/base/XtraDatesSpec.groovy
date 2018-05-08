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

    def "should create date from pattern"() {
        when:
        def date = XtraDates.newDate(dateStr)
        def expectedCal = date.toCalendar()

        then:
        expectedCal.get(Calendar.YEAR) == expectedYear
        expectedCal.get(Calendar.MONTH) == expectedMonth
        expectedCal.get(Calendar.DAY_OF_MONTH) == expectedDayOfMonth

        where:
        dateStr = '2018-09-08'

        expectedYear = 2018
        expectedMonth = Calendar.SEPTEMBER
        expectedDayOfMonth = 8
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

package fr.adbonnin.xtra.groovy.base

import fr.adbonnin.xtra.base.XtraDates
import spock.lang.Specification
import spock.lang.Unroll

class XtraDatesExtensionSpec extends Specification {

    def "should format to date"() {
        expect:
        date.formatToDate() == expectedStr

        where:
        date = XtraDates.newDate(2018, Calendar.NOVEMBER, 13)
        expectedStr = '2018-11-13'
    }

    def "should format to date time"() {
        expect:
        date.formatToDateTime() == expectedStr

        where:
        date = XtraDates.newDate(2018, Calendar.NOVEMBER, 13, 8, 36, 42)
        expectedStr = '2018-11-13 08:36:42'
    }

    @Unroll
    def "should access field #field"() {
        given:
        def cal = Calendar.getInstance()
        cal.set(calendarField, initValue)

        expect:
        cal["$field"] == initValue

        when:
        cal["$field"] = newValue

        then:
        cal["$field"] == newValue

        where:
        calendarField         | field          | initValue        | newValue
        Calendar.YEAR         | 'year'         | 2017             | 2018
        Calendar.MONTH        | 'month'        | Calendar.JANUARY | Calendar.MARCH
        Calendar.DAY_OF_MONTH | 'dayOfMonth'   | 1                | 2
        Calendar.MINUTE       | 'minutes'      | 1                | 2
        Calendar.SECOND       | 'seconds'      | 1                | 2
        Calendar.MILLISECOND  | 'milliseconds' | 1                | 2
    }
}

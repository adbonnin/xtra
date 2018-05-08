package fr.adbonnin.xtra.groovy.base

import spock.lang.Specification
import spock.lang.Unroll

class XtraDatesExtensionSpec extends Specification {

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
        Calendar.MONTH        | 'month'        | Calendar.JANUARY | Calendar.FEBRUARY
        Calendar.DAY_OF_MONTH | 'dayOfMonth'   | 1                | 2
        Calendar.MINUTE       | 'minutes'      | 1                | 2
        Calendar.SECOND       | 'seconds'      | 1                | 2
        Calendar.MILLISECOND  | 'milliseconds' | 1                | 2
    }
}

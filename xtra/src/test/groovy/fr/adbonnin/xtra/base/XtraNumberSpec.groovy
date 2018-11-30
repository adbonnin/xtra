package fr.adbonnin.xtra.base

import spock.lang.Specification
import spock.lang.Unroll

class XtraNumberSpec extends Specification {

    @Unroll
    void "should transform #value to ordinal #ordinal"() {
        expect:
        XtraNumber.toOrdinal(value) == ordinal

        where:
        value   | ordinal
        1       | '1st'
        2       | '2nd'
        3       | '3rd'
        4       | '4th'
        5       | '5th'
        6       | '6th'
        7       | '7th'
        8       | '8th'
        9       | '9th'
        10      | '10th'
        11      | '11th'
        12      | '12th'
        13      | '13th'
        14      | '14th'
        15      | '15th'
        16      | '16th'
        17      | '17th'
        18      | '18th'
        19      | '19th'
        20      | '20th'
        21      | '21st'
        22      | '22nd'
        23      | '23rd'
        24      | '24th'
        25      | '25th'
        26      | '26th'
        27      | '27th'
        28      | '28th'
        29      | '29th'
        30      | '30th'
        31      | '31st'
        40      | '40th'
        50      | '50th'
        60      | '60th'
        70      | '70th'
        80      | '80th'
        90      | '90th'
        100     | '100th'
        1000    | '1000th'
        1000000 | '1000000th'
    }

    void "should parse to Integer"() {
        expect:
        XtraNumber.toIntegerObject(str, defaultValue) == expectedValue

        where:
        str   | defaultValue || expectedValue
        null  | 2            || 2
        " 3 " | 2            || 3
        "a"   | 4            || 4
    }

    void "should parse to Float"() {
        expect:
        XtraNumber.toFloatObject(str, defaultValue) == expectedValue

        where:
        str     | defaultValue || expectedValue
        null    | 2.2f         || 2.2f
        " 3.3 " | 2.2f         || 3.3f
        "a"     | 4.4f         || 4.4f
    }
}

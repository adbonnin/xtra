package fr.adbonnin.xtra.increment

import spock.lang.Specification

import static fr.adbonnin.xtra.increment.MapIncrement.newFull
import static fr.adbonnin.xtra.increment.MapIncrement.newPart

class MapIncrementSpec extends Specification {

    void "should compute hashcode"() {
        given:
        def part = newPart(revision, created, updated, removed)
        def full = newFull(revision, created)

        expect:
        part.hashCode() == expectedPartHashCode
        full.hashCode() == expectedFullHashCode

        where:
        revision | created | updated | removed    || expectedPartHashCode | expectedFullHashCode
        "rev"    | [:]     | [:]     | [] as Set  || 1096724259           | 1096754050
        "rev"    | [0: 1]  | [:]     | [] as Set  || 1096725220           | 1096755011
        "rev"    | [0: 1]  | [0: 1]  | [] as Set  || 1096725251           | 1096755011
        "rev"    | [0: 1]  | [0: 1]  | [1] as Set || 1096725252           | 1096755011
    }

    void "should compare partial increment with equals"() {
        given:
        def part = newPart(revision, created, updated, removed)

        expect:
        (part == otherPart) == expectedEqualsToPart
        (part == otherFull) == expectedEqualsToFull
        (part == otherObject) == expectedEqualsToObject

        where:
        revision | created | updated | removed    || expectedEqualsToPart | expectedEqualsToFull | expectedEqualsToObject
        "rev0"   | [:]     | [:]     | [] as Set  || true                 | false                | false
        "rev1"   | [:]     | [:]     | [] as Set  || false                | false                | false
        "rev0"   | [0: 1]  | [:]     | [] as Set  || false                | false                | false
        "rev0"   | [:]     | [0: 1]  | [] as Set  || false                | false                | false
        "rev0"   | [:]     | [:]     | [1] as Set || false                | false                | false

        otherPart = newPart("rev0", [:], [:], [] as Set)
        otherFull = newFull("rev0", [:])
        otherObject = new Object()
    }

    void "should compare full increment with equals"() {
        given:
        def part = newFull(revision, created)

        expect:
        (part == otherPart) == expectedEqualsToPart
        (part == otherFull) == expectedEqualsToFull
        (part == otherObject) == expectedEqualsToObject

        where:
        revision | created | expectedEqualsToPart | expectedEqualsToFull | expectedEqualsToObject
        "rev0"   | [:]     | false                | true                 | false
        "rev1"   | [:]     | false                | false                | false
        "rev0"   | [0: 1]  | false                | false                | false

        otherPart = newPart("rev0", [:], [:], [] as Set)
        otherFull = newFull("rev0", [:])
        otherObject = new Object()
    }

    void "should print to string"() {
        expect:
        increment.toString() == expectedToString

        where:
        increment                               || expectedToString
        newFull('rev0', [1: 1])                 || 'rev0 => full: true, created: {1=1}, updated: {}, removed: []'
        newPart('rev1', [1: 1], [:], [] as Set) || 'rev1 => full: false, created: {1=1}, updated: {}, removed: []'
        newPart('rev2', [:], [2: 2], [] as Set) || 'rev2 => full: false, created: {}, updated: {2=2}, removed: []'
        newPart('rev3', [:], [:], [3] as Set)   || 'rev3 => full: false, created: {}, updated: {}, removed: [3]'
    }
}

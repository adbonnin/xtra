package fr.adbonnin.xtra.increment

import fr.adbonnin.xtra.base.EqualsEquivalence
import spock.lang.Specification

import static fr.adbonnin.xtra.increment.MapIncrement.newFull
import static fr.adbonnin.xtra.increment.MapIncrement.newPart

class XtraIncrementSpec extends Specification {

    void "should create new partial map increment"() {
        when:
        def increment = XtraIncrement.newPartialMapIncrement(revision, oldValues, newValues, new EqualsEquivalence())

        then:
        increment.revision == revision
        !increment.full
        increment.created == expectedCreated
        increment.updated == expectedUpdated
        increment.removed == expectedRemoved

        where:
        oldValues | newValues || expectedCreated | expectedUpdated | expectedRemoved
        [:]       | [:]       || [:]             | [:]             | [] as Set
        [1: 1]    | [:]       || [:]             | [:]             | [1] as Set
        [:]       | [1: 1]    || [1: 1]          | [:]             | [] as Set
        [1: 1]    | [1: 10]   || [:]             | [1: 10]         | [] as Set

        revision = "rev"
    }

    void "should throw an exception when concat empty iterator"() {
        when:
        XtraIncrement.concatMapIncrements(Collections.emptyIterator())

        then:
        def ex = thrown(IllegalArgumentException)
        ex.message == "'iterator' must have at least one increment otherwise revision can't be found."
    }

    void "should concat full increment with partial to full"() {
        when:
        def increment = XtraIncrement.concatMapIncrements(increments.iterator())

        then:
        increment == expectedIncrement

        where:
        increments = [
            newPart("rev1", [1: 1], [2: 2], [3] as Set),
            newFull("rev2", [4: 4])
        ]

        expectedIncrement = newFull("rev2", [4: 4])
    }

    void "should concat partial increment with full to full"() {
        when:
        def increment = XtraIncrement.concatMapIncrements([initial, other].iterator())

        then:
        increment == expectedIncrement

        where:
        other                                   || expectedIncrement
        newPart("rev1", [1: 4], [:], [] as Set) || newFull("rev1", [1: 4])
        newPart("rev2", [:], [1: 4], [] as Set) || newFull("rev2", [1: 4])
        newPart("rev3", [:], [2: 2], [] as Set) || newFull("rev3", [1: 1, 2: 2])
        newPart("rev4", [:], [:], [1] as Set)   || newFull("rev4", [:])

        initial = newFull("rev0", [1: 1])
    }

    void "should concat partial increment with created"() {
        when:
        def increment = XtraIncrement.concatMapIncrements([initial, other].iterator())

        then:
        increment == expectedIncrement

        where:
        other                                   || expectedIncrement
        newPart("rev1", [1: 4], [:], [] as Set) || newPart("rev1", [1: 4], [2: 2], [3] as Set)
        newPart("rev2", [2: 4], [:], [] as Set) || newPart("rev2", [1: 1, 2: 4], [:], [3] as Set)
        newPart("rev3", [3: 4], [:], [] as Set) || newPart("rev3", [1: 1, 3: 4], [2: 2], [] as Set)

        initial = newPart("rev0", [1: 1], [2: 2], [3] as Set)
    }

    void "should concat partial increment with updated"() {
        when:
        def increment = XtraIncrement.concatMapIncrements([initial, other].iterator())

        then:
        increment == expectedIncrement

        where:
        other                                   || expectedIncrement
        newPart("rev1", [:], [1: 4], [] as Set) || newPart("rev1", [1: 4], [2: 2], [3] as Set)
        newPart("rev2", [:], [2: 4], [] as Set) || newPart("rev2", [1: 1], [2: 4], [3] as Set)
        newPart("rev3", [:], [3: 4], [] as Set) || newPart("rev3", [1: 1], [2: 2, 3: 4], [] as Set)

        initial = newPart("rev0", [1: 1], [2: 2], [3] as Set)
    }

    void "should concat partial increment with removed"() {
        when:
        def increment = XtraIncrement.concatMapIncrements([initial, other].iterator())

        then:
        increment == expectedIncrement

        where:
        other                                 || expectedIncrement
        newPart("rev1", [:], [:], [1] as Set) || newPart("rev1", [:], [2: 2], [1, 3] as Set)
        newPart("rev2", [:], [:], [2] as Set) || newPart("rev2", [1: 1], [:], [2, 3] as Set)
        newPart("rev3", [:], [:], [3] as Set) || newPart("rev3", [1: 1], [2: 2], [3] as Set)

        initial = newPart("rev0", [1: 1], [2: 2], [3] as Set)
    }
}

package fr.adbonnin.xtra.increment

import fr.adbonnin.xtra.base.EqualsEquivalence
import spock.lang.Specification
import spock.lang.Subject

import static java.util.Collections.emptyMap
import static java.util.Collections.emptySet

class IncrementalMapSpec extends Specification {

    void "should set the map"() {
        given:
        @Subject def map = new IncrementalMap(initialRevision, initialValues, maxSize, new EqualsEquivalence())

        expect:
        map.revision == initialRevision
        map.values == initialValues

        when:
        map.set(newRevision, newValues)

        then:
        map.revision == newRevision
        map.values == newValues

        where:
        initialRevision = 'rev0'
        initialValues = [0: 0]
        maxSize = 3

        newRevision = 'rev1'
        newValues = [0: 1]
    }

    void "should raise an exception"() {
        given:
        @Subject def map = new IncrementalMap(revision, values, maxSize, new EqualsEquivalence())

        when:
        map.set(revision, values)

        then:
        def ex = thrown(IllegalArgumentException)
        ex.message == 'revision already exists'

        where:
        revision = 'rev0'
        values = [0: 0]
        maxSize = 3
    }

    void "should get full increment"() {
        given:
        @Subject def map = new IncrementalMap(initialRevision, initialValues, maxSize, new EqualsEquivalence())

        expect:
        map.full() == MapIncrement.newFull(initialRevision, initialValues)

        when:
        map.set(newRevision, newValues)

        then:
        map.full() == MapIncrement.newFull(newRevision, newValues)

        where:
        initialRevision = 'rev0'
        initialValues = [0: 0]
        maxSize = 3

        newRevision = 'rev1'
        newValues = [1: 1]
    }

    void "should get full increment from revision"() {
        given:
        @Subject def map = new IncrementalMap(initialRevision, initialValues, 3, new EqualsEquivalence())

        expect:
        map.from(initialRevision) == MapIncrement.newFull(initialRevision, initialValues)

        where:
        initialRevision = 'rev0'
        initialValues = [0: 0]
        maxSize = 3
    }

    void "shoud get part increment from revision"() {
        given:
        @Subject def map = new IncrementalMap(initialRevision, initialValues, 3, new EqualsEquivalence())

        when:
        map.set(newRevision, newValues)

        then:
        map.from(newRevision) == MapIncrement.newPart(newRevision, emptyMap(), newValues, emptySet())

        where:
        initialRevision = 'rev0'
        initialValues = [0: 0]
        maxSize = 3

        newRevision = 'rev1'
        newValues = [0: 1]
    }
}

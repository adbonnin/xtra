package fr.adbonnin.xtra.collect

import spock.lang.Specification

class RandomCollectionSpec extends Specification {

    void "should indicate that there is elements"() {
        given:
        def randomCollection = new RandomCollection()

        expect:
        !randomCollection.hasNext()

        when:
        randomCollection.add(-1, 0)

        then:
        !randomCollection.hasNext()

        when:
        randomCollection.add(0.5, 1)

        then:
        randomCollection.hasNext()
    }

    void "should return random object"() {
        given:
        def random = Mock(Random) {
            1 * nextDouble() >> 0.1
            1 * nextDouble() >> 0.4
            1 * nextDouble() >> 0.6
        }

        def randomCollection = new RandomCollection(random)
            .add(20, 'A')
            .add(30, 'B')
            .add(50, 'C')

        expect:
        randomCollection.next() == 'A'
        randomCollection.next() == 'B'
        randomCollection.next() == 'C'
    }
}

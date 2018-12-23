package fr.adbonnin.xtra.collect

import spock.lang.Specification

class RandomCollectionSpec extends Specification {

    void "should indicate that there is elements"() {
        given:
        def randomCollection = new RandomCollection()
        def randomItr = randomCollection.iterator()

        expect:
        !randomItr.hasNext()

        when:
        randomCollection.add(-1, 0)

        then:
        !randomItr.hasNext()

        when:
        randomCollection.add(0.5, 1)

        then:
        randomItr.hasNext()
    }

    void "should return random object"() {
        given:
        def random = Mock(Random) {
            2 * nextDouble() >> 0.1
            2 * nextDouble() >> 0.4
            2 * nextDouble() >> 0.6
        }

        def randomCollection = new RandomCollection()
            .add(20, 'A')
            .add(30, 'B')
            .add(50, 'C')

        def randomIr = randomCollection.iterator(random)

        expect:
        randomCollection.get(random) == 'A'
        randomIr.hasNext()
        randomIr.next() == 'A'

        and:
        randomCollection.get(random) == 'B'
        randomIr.hasNext()
        randomIr.next() == 'B'

        and:
        randomCollection.get(random) == 'C'
        randomIr.hasNext()
        randomIr.next() == 'C'
    }
}

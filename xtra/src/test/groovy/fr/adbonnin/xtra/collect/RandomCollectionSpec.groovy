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

    void "should create random object"() {
        given:
        def random = Mock(Random) {
            2 * nextDouble() >> 0.1
            2 * nextDouble() >> 0.4
            2 * nextDouble() >> 0.6
        }

        def randomCollection = new RandomCollection()
            .add('A', 20)
            .add('B', 30)
            .add('C', 50)

        def randomItr = randomCollection.iterator(random)

        expect:
        randomCollection.get(random) == 'A'
        randomItr.hasNext()
        randomItr.next() == 'A'

        and:
        randomCollection.get(random) == 'B'
        randomItr.hasNext()
        randomItr.next() == 'B'

        and:
        randomCollection.get(random) == 'C'
        randomItr.hasNext()
        randomItr.next() == 'C'
    }

    void "should create random object from iterator"() {
        given:
        def random = Mock(Random) {
            2 * nextDouble() >> 0.1
            2 * nextDouble() >> 0.4
            2 * nextDouble() >> 0.6
        }

        def randomCollection = new RandomCollection().addAll(['A': 20, 'B': 30, 'C': 50].iterator())
        def randomItr = randomCollection.iterator(random)

        expect:
        randomCollection.get(random) == 'A'
        randomItr.hasNext()
        randomItr.next() == 'A'

        and:
        randomCollection.get(random) == 'B'
        randomItr.hasNext()
        randomItr.next() == 'B'

        and:
        randomCollection.get(random) == 'C'
        randomItr.hasNext()
        randomItr.next() == 'C'
    }
}

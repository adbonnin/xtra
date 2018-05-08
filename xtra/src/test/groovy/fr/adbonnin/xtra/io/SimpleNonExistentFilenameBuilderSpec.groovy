package fr.adbonnin.xtra.io

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class SimpleNonExistentFilenameBuilderSpec extends Specification {

    @Unroll
    def "name '#name' at retry #retry should be '#expected'"() {
        given:
        @Subject def filenameBuilder = SimpleNonExistentFilenameBuilder.INSTANCE

        def parent = new File("dir")
        def expectedFile = new File(parent, expected)

        def file = Mock(File) {
            getName() >> name
            getParentFile() >> parent
        }

        expect:
        filenameBuilder.buildNonExistentFile(file, null, retry) == expectedFile

        where:
        name      || expected
        'foo.bar' || 'foo (1).bar'
        'foo'     || 'foo (1)'
        '.bar'    || '(1).bar'
        ''        || '(1)'

        retry = 1
    }
}

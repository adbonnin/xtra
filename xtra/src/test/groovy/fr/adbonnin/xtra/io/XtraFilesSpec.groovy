package fr.adbonnin.xtra.io

import fr.adbonnin.xtra.exception.ExhaustedRetryException
import spock.lang.Specification
import spock.lang.Unroll

class XtraFilesSpec extends Specification {

    def "indexOfExtension: 'name' parameter validation"() {
        when: "name is null"
        XtraFiles.indexOfExtension(null)

        then:
        thrown(NullPointerException)
    }

    def "basename: 'name' parameter validation"() {
        when: "name is null"
        XtraFiles.basename(null)

        then:
        thrown(NullPointerException)
    }

    def "extension: 'name' parameter validation"() {
        when: "name is null"
        XtraFiles.extension(null)

        then:
        thrown(NullPointerException)
    }

    @Unroll
    def "name '#name' should have basename '#basename' #extensionLabel"() {
        expect:
        XtraFiles.indexOfExtension(name) == indexOfExtension
        XtraFiles.basename(name) == basename
        XtraFiles.extension(name) == extension
        XtraFiles.hasExtension(name) == hasExtension

        where:
        name      || indexOfExtension | basename | extension | hasExtension
        ""        || -1               | ""       | ""        | false
        "foo"     || -1               | "foo"    | ""        | false
        "foo."    || 3                | "foo"    | ""        | true
        ".foo"    || 0                | ""       | "foo"     | true
        "foo.bar" || 3                | "foo"    | "bar"     | true

        extensionLabel = hasExtension ? "with extension '$extension' at $indexOfExtension" : 'without extension'
    }

    def "newNonExistentFile: 'file' parameter validation"() {
        when:
        XtraFiles.newNonExistentFile(null, 0, Mock(NonExistentFilenameBuilder));

        then:
        thrown(NullPointerException)
    }

    def "newNonExistentFile: 'builder' parameter validation"() {
        given:
        def file = Mock(File) {
            exists() >> true
        }

        when: "builder is null"
        XtraFiles.newNonExistentFile(file, 1, null)

        then:
        thrown(NullPointerException)
    }

    def "newNonexistentFile: should return the same file if file doesn't exists"() {
        given:
        def file = Mock(File)
        def filenameBuilder = Mock(NonExistentFilenameBuilder)

        when:
        def result = XtraFiles.newNonExistentFile(file, 3)

        then:
        1 * file.exists() >> false
        0 * filenameBuilder.buildFilename(*_)

        result.is(file)
    }

    def "newNonexistentFile: should return a non existent file if file exists"() {
        given:
        def filenameBuilder = Mock(NonExistentFilenameBuilder)

        def file = Mock(File)
        def existentFile = Mock(File)
        def expected = Mock(File)

        when:
        def result = XtraFiles.newNonExistentFile(file, 3, filenameBuilder)

        then:
        1 * file.exists() >> true

        1 * filenameBuilder.buildNonExistentFile(file, null, 1) >> existentFile
        1 * existentFile.exists() >> true

        1 * filenameBuilder.buildNonExistentFile(file, existentFile, 2) >> expected
        1 * expected.exists() >> false

        result.is(expected)
    }

    def "newNonexistentFile: should throw an exception when retry limit is reached"() {
        given:
        def file = Mock(File)

        when:
        XtraFiles.newNonExistentFile(file, 0)

        then:
        1 * file.exists() >> true
        1 * file.getCanonicalPath() >> 'path'

        def ex = thrown(ExhaustedRetryException)
        ex.message == 'retry limit has been reached ; file: path'
    }

    def "cleanFilename: 'name' parameter validation"() {
        when: "name is null"
        XtraFiles.cleanFilename(null)

        then:
        thrown(NullPointerException)
    }

    @Unroll
    def "cleanFilename: shound clear '#filename' to '#expected'"() {
        expect:
        XtraFiles.cleanFilename(filename) == expected

        where:
        filename  | expected
        "foo}"    | "foo}"
        " foo"    | "foo"
        "foo."    | "foo"
        "foo "    | "foo"
        "foo>"    | "foo"
        "\01foo"  | "foo"
        " <foo> " | "foo"
        "< foo >" | "foo"
        " . "     | ""
    }

    def "tryCanonicalPath: parameters validation"() {
        expect:
        XtraFiles.tryCanonicalPath(null) == "null"
        XtraFiles.tryCanonicalPath(null, "foo") == "foo"
    }

    def "tryCanonicalPath: should return getCanonicalPath"() {
        given:
        def file = Mock(File)

        when:
        def result = XtraFiles.tryCanonicalPath(file)

        then:
        1 * file.getCanonicalPath() >> "canonicalPath"

        result == "canonicalPath"
    }

    def "tryCanonicalPath: should use getPath when getCanonicalPath throw IOException"() {
        given:
        def file = Mock(File)

        when:
        def result = XtraFiles.tryCanonicalPath(file)

        then:
        1 * file.getCanonicalPath() >> { throw new IOException() }
        1 * file.getPath() >> "path"

        result == "path"
    }
}

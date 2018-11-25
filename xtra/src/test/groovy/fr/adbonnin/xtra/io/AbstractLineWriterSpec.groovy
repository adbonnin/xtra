package fr.adbonnin.xtra.io

import spock.lang.Specification
import spock.lang.Unroll

import static java.util.Objects.requireNonNull

class AbstractLineWriterSpec extends Specification {

    def lineWriter = new SimpleLineWriter()

    @Unroll
    void "should write line"() {
        when:
        for (CharArrayBuffer buffer : buffers) {
            lineWriter.write(buffer.cbuf, buffer.off, buffer.len)
        }
        lineWriter.close()

        then:
        lineWriter.lines == expectedLines

        where:
        buffers                                           || expectedLines
        [new CharArrayBuffer('ab'.toCharArray(), 0, 1)]   || ['a']
        [new CharArrayBuffer('ab'.toCharArray(), 1, 1)]   || ['b']

        [new CharArrayBuffer('a\nb'.toCharArray(), 0, 3)] || ['a', 'b']
        [new CharArrayBuffer('\nab'.toCharArray(), 0, 3)] || ['', 'ab']
        [new CharArrayBuffer('ab\n'.toCharArray(), 0, 3)] || ['ab', '']

        [new CharArrayBuffer('a\rb'.toCharArray(), 0, 3)] || ['a', 'b']
        [new CharArrayBuffer('\rab'.toCharArray(), 0, 3)] || ['', 'ab']
        [new CharArrayBuffer('ab\r'.toCharArray(), 0, 3)] || ['ab', '']

        [new CharArrayBuffer('a\r\nb'.toCharArray(), 0, 4)] || ['a', 'b']
        [new CharArrayBuffer('\r\nab'.toCharArray(), 0, 4)] || ['', 'ab']
        [new CharArrayBuffer('ab\r\n'.toCharArray(), 0, 4)] || ['ab', '']

        [new CharArrayBuffer('\n\r\r\n'.toCharArray(), 0, 4)] || ['', '', '', '']
    }

    static class SimpleLineWriter extends AbstractLineWriter {

        final lines = [] as List<String>

        @Override
        void writeLine(String str) throws IOException {
            lines.add(str)
        }
    }

    static class CharArrayBuffer {

        final char[] cbuf

        final int off

        final int len

        CharArrayBuffer(char[] cbuf, int off, int len) {
            this.cbuf = requireNonNull(cbuf)
            this.off = off
            this.len = len
        }
    }
}

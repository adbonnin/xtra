package fr.adbonnin.xtra.io;

import java.io.IOException;
import java.io.Writer;

import static fr.adbonnin.xtra.base.XtraStrings.EMPTY;

public abstract class AbstractLineWriter extends Writer {

    public abstract void writeLine(String str) throws IOException;

    private StringBuilder s;

    private boolean skipLF = false;

    private boolean eol = false;

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        int nChars = off + len;
        int nextChar = off;

        for (;;) {
            if (nextChar >= nChars) {
                return;
            }

            if (skipLF && (cbuf[nextChar] == '\n')) {
                nextChar++;
            }

            skipLF = false;

            if (nextChar >= nChars) {
                return;
            }

            eol = false;
            char c = 0;
            int i;

            for (i = nextChar; i < nChars; i++) {
                c = cbuf[i];
                if ((c == '\n') || c == '\r') {
                    eol = true;
                    break;
                }
            }

            final int count = i - nextChar;
            final int startChar = nextChar;
            nextChar = i;

            if (eol) {
                final String str;
                if (s == null) {
                    str = new String(cbuf, startChar, count);
                }
                else {
                    s.append(cbuf, startChar, count);
                    str = s.toString();
                    s = null;
                }

                nextChar++;

                if (c == '\r') {
                    skipLF = true;
                }

                writeLine(str);
            }
            else if (count > 0) {
                if (s == null) {
                    s = new StringBuilder(count);
                }

                s.append(cbuf, startChar, count);
            }
        }
    }

    @Override
    public void flush() {
        // nothing to do
    }

    @Override
    public void close() throws IOException {
        if (s != null) {
            writeLine(s.toString());
            s = null;
        }
        else if (eol) {
            writeLine(EMPTY);
            eol = false;
        }
    }
}


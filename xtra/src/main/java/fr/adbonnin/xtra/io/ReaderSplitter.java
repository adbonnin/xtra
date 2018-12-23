package fr.adbonnin.xtra.io;

import fr.adbonnin.xtra.base.CircularCharArray;
import fr.adbonnin.xtra.base.XtraStrings;
import fr.adbonnin.xtra.collect.AbstractIterator;
import fr.adbonnin.xtra.collect.XtraIterators;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Objects;

public class ReaderSplitter extends AbstractIterator<String> {

    private final Reader reader;

    private final String separator;

    private final CircularCharArray separatorBuf;

    public ReaderSplitter(String str, String separator) {
        this(new StringReader(str), separator);
    }

    public ReaderSplitter(Reader reader, String separator) {
        this.reader = Objects.requireNonNull(reader);
        this.separator = separator;
        this.separatorBuf = new CircularCharArray(separator.length());
    }

    @Override
    protected String computeNext() {
        try {
            final StringBuilder buf = new StringBuilder();

            int ch;
            while ((ch = reader.read()) != -1) {
                final int rest = separatorBuf.addChar((char) ch);
                if (rest != -1) {
                    buf.append((char) rest);
                }

                if (XtraStrings.equals(separator, separatorBuf)) {
                    return buf.toString();
                }
            }

            if (buf.length() != 0 || separatorBuf.length() != 0) {
                return buf.toString() + separatorBuf.toString();
            }

            return endOfData();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            separatorBuf.clear();
        }
    }

    public int nextAsInt(int defaultValue) {
        return XtraIterators.nextAsInt(this, defaultValue);
    }

    public long nextAsLong(long defaultValue) {
        return XtraIterators.nextAsLong(this, defaultValue);
    }

    public float nextAsFloat(float defaultValue) {
        return XtraIterators.nextAsFloat(this, defaultValue);
    }

    public double nextAsDouble(double defaultValue) {
        return XtraIterators.nextAsDouble(this, defaultValue);
    }
}

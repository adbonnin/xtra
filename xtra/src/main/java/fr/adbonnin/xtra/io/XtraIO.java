package fr.adbonnin.xtra.io;

import java.io.*;
import java.nio.charset.Charset;

public final class XtraIO {

    public static final int EOM = -1;

    public static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            }
            catch (Throwable e) {
                /* Nothing to do */
            }
        }
    }

    public static String toString(InputStream input, Charset charset) throws IOException {
        Reader reader = null;
        try {
            reader = new InputStreamReader(input, charset);

            final Writer writer = new StringWriter();
            copy(reader, writer);
            return writer.toString();
        }
        finally {
            closeQuietly(reader);
        }
    }

    public static long copy(Reader reader, Writer writer) throws IOException {
        return copy(reader, writer, new char[DEFAULT_BUFFER_SIZE]);
    }

    public static long copy(Reader reader, Writer writer, char[] buffer) throws IOException {
        long count = 0;
        int n;
        while (EOM != (n = reader.read(buffer))) {
            writer.write(buffer, 0, n);
            count += n;
        }
        return count;
    }

    private XtraIO() { /* Cannot be instantiated */}
}

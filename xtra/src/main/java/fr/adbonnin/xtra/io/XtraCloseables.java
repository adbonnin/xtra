package fr.adbonnin.xtra.io;

import java.io.Closeable;

public final class XtraCloseables {

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

    private XtraCloseables() { /* Cannot be instantiated */}
}

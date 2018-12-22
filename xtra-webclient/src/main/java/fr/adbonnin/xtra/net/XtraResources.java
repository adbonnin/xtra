package fr.adbonnin.xtra.net;

import fr.adbonnin.xtra.io.XtraFiles;

import java.io.File;
import java.net.URI;

public final class XtraResources {

    public static final char PATH_SEPARATOR = '/';

    public static String toUriResourceName(URI uri) {
        final String path = uri.getPath();
        final int index = path.lastIndexOf(PATH_SEPARATOR);
        return path.substring(index + 1);
    }

    public static String toFilename(URI uri, String defaultEmptyFilename) {
        final String uriFilename = toUriResourceName(uri);
        final String filename = XtraFiles.cleanFilename(uriFilename);
        return filename.isEmpty() ? defaultEmptyFilename : filename;
    }

    private XtraResources() { /* Cannot be instantiated */ }
}

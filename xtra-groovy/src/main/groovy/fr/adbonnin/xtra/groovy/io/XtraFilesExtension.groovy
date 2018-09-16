package fr.adbonnin.xtra.groovy.io

import fr.adbonnin.xtra.io.XtraFiles

final class XtraFilesExtension {

    static String getTryCanonicalPath(File file) {
        return XtraFiles.tryCanonicalPath(file)
    }

    static String tryCanonicalPath(File file) {
        return XtraFiles.tryCanonicalPath(file)
    }

    static String tryCanonicalPath(File file, String nullDefault) {
        return XtraFiles.tryCanonicalPath(file, nullDefault)
    }

    static void createDir(File dir) {
        XtraFiles.createDir(dir)
    }

    private XtraFilesExtension() { /* Cannot be instantiated */ }
}

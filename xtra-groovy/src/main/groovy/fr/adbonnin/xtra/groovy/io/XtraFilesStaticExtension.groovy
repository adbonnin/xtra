package fr.adbonnin.xtra.groovy.io

import fr.adbonnin.xtra.io.XtraFiles

import java.nio.file.Files

final class XtraFilesStaticExtension {

    static File newCleanedFile(Files files, File dir, String filename) {
        def cleanedFile = XtraFiles.cleanFilename(filename)
        return new File(dir, cleanedFile)
    }

    static File newNonExistentFile(Files files, File file, int retryLimit) {
        return XtraFiles.newNonExistentFile(file, retryLimit)
    }

    static File newNonExistentFile(Files files, File file) {
        return XtraFiles.newNonExistentFile(file)
    }

    static File newNonExistentCleanedFile(Files files, File parent, String filename, int retryLimit) {
        return XtraFiles.newNonExistentCleanedFile(parent, filename, retryLimit)
    }

    static File newNonExistentCleanedFile(Files files, File parent, String filename) {
        return XtraFiles.newNonExistentCleanedFile(parent, filename)
    }
}

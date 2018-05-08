package fr.adbonnin.xtra.io;

import java.io.File;

public interface NonExistentFilenameBuilder {

    File buildNonExistentFile(File file, File previousTryFile, int retry);
}

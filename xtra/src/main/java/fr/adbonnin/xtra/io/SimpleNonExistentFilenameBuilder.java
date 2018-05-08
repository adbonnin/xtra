package fr.adbonnin.xtra.io;

import java.io.File;

public class SimpleNonExistentFilenameBuilder implements NonExistentFilenameBuilder {

    public static final SimpleNonExistentFilenameBuilder INSTANCE = new SimpleNonExistentFilenameBuilder();

    @Override
    public File buildNonExistentFile(File file, File previousFile, int retry) {
        final StringBuilder sb = new StringBuilder();
        final String name = file.getName();

        final String basename = XtraFiles.basename(name);
        if (!basename.isEmpty()) {
            sb.append(basename).append(' ');
        }

        sb.append('(').append(retry).append(')');

        if (XtraFiles.hasExtension(name)) {
            sb.append(XtraFiles.EXTENSION_SEPARATOR).append(XtraFiles.extension(name));
        }

        return new File(file.getParentFile(), sb.toString());
    }
}

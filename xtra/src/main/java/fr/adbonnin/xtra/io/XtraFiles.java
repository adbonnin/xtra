package fr.adbonnin.xtra.io;

import fr.adbonnin.xtra.base.SystemProperties;
import fr.adbonnin.xtra.exception.ExhaustedRetryException;

import java.io.File;
import java.io.IOException;

import static fr.adbonnin.xtra.base.XtraStrings.EMPTY;
import static fr.adbonnin.xtra.base.XtraObjects.NULL_DEFAULT;

public final class XtraFiles {

    public static final char EXTENSION_SEPARATOR = '.';

    public static final char[] FILENAME_RESERVED_CHARACTERS = {
        '"',  // 0x34 double quote
        '*',  // 0x42 asterisk
        '/',  // 0x47 forward slash
        ':',  // 0x58 colon
        '<',  // 0x60 less than
        '>',  // 0x62 greater than
        '?',  // 0x63 question mark
        '\\', // 0x92 backslash
        '|'   // 0x124 vertical bar or pipe
    };

    public static int indexOfExtension(String name) {
        return name.lastIndexOf(EXTENSION_SEPARATOR);
    }

    public static String basename(String name) {
        final int index = indexOfExtension(name);
        return index == -1 ? name : name.substring(0, index);
    }

    public static String extension(String name) {
        final int index = indexOfExtension(name);
        return index == -1 ? EMPTY : name.substring(index + 1);
    }

    public static boolean hasExtension(String name) {
        final int index = indexOfExtension(name);
        return index != -1;
    }

    public static File newNonExistentFile(File file, int retryLimit, NonExistentFilenameBuilder builder) {

        if (!file.exists()) {
            return file;
        }

        File previousFile = null;
        int retry = 1;
        while (retry <= retryLimit) {
            previousFile = builder.buildNonExistentFile(file, previousFile, retry);
            if (!previousFile.exists()) {
                return previousFile;
            }

            ++retry;
        }

        throw new ExhaustedRetryException("retry limit has been reached ; file: " + tryCanonicalPath(file));
    }

    public static File newNonExistentFile(File file, int retryLimit) {
        return newNonExistentFile(file, retryLimit, SimpleNonExistentFilenameBuilder.INSTANCE);
    }

    public static File newNonExistentFile(File file) {
        return newNonExistentFile(file, Integer.MAX_VALUE, SimpleNonExistentFilenameBuilder.INSTANCE);
    }

    public static File newNonExistentCleanedFile(File parent, String filename, int retryLimit) {
        final File cleanedFile = newCleanedFile(parent, filename);
        return XtraFiles.newNonExistentFile(cleanedFile, retryLimit);
    }

    public static File newNonExistentCleanedFile(File parent, String filename) {
        final File cleanedFile = newCleanedFile(parent, filename);
        return XtraFiles.newNonExistentFile(cleanedFile);
    }

    public static File newCleanedFile(File parent, String filename) {
        final String cleanedFilename = XtraFiles.cleanFilename(filename);
        return new File(parent, cleanedFilename);
    }

    public static File toCleanedFile(File file) {
        final String filename = file.getName();
        final String cleanedFilename = XtraFiles.cleanFilename(filename);
        return filename.equals(cleanedFilename) ? file : new File(file.getParent(), cleanedFilename);
    }

    /**
     * Remove reserved and illegals file name characters from {@code name}.
     *
     * @see <a href=https://msdn.microsoft.com/en-us/library/windows/desktop/aa365247(v=vs.85).aspx">Microsoft: Naming Files, Paths, and Namespaces</a>
     */
    public static String cleanFilename(String name) {
        final int len = name.length();
        final StringBuilder sb = new StringBuilder(len);

        for (int i = 0; i < len; i++) {
            final char c = name.charAt(i);

            if (c <= 31) {
                continue;
            }

            if (isNameReservedCharacter(c)) {
                continue;
            }

            sb.append(c);
        }

        int start = 0;
        int end = sb.length();

        while ((start < end) && sb.charAt(start) <= ' ') {
            ++start;
        }

        while (start < end) {
            final char c = sb.charAt(end - 1);
            if (c <= ' ' || c == EXTENSION_SEPARATOR) {
                --end;
            }
            else {
                break;
            }
        }

        return (end - start) == len ? name : sb.substring(start, end);
    }

    private static boolean isNameReservedCharacter(char c) {

        for (char reserved : FILENAME_RESERVED_CHARACTERS) {
            if (c == reserved) {
                return true;
            }
            else if (c < reserved) {
                return false;
            }
        }

        return false;
    }

    /**
     * Return {@code file.getCanonicalPath} or {@code file.getPath()} if an exception is thrown.
     * If {@code file} is null, {@code "null"} is returned.
     */
    public static String tryCanonicalPath(File file) {
        return tryCanonicalPath(file, NULL_DEFAULT);
    }

    /**
     * Return {@code file.getCanonicalPath} or {@code file.getPath()} if an exception is thrown.
     * If {@code file} is null, {@code nullDefault} is returned.
     */
    public static String tryCanonicalPath(File file, String nullDefault) {

        if (file == null) {
            return nullDefault;
        }

        try {
            return file.getCanonicalPath();
        }
        catch (IOException e) {
            return file.getPath();
        }
    }

    public static void createDir(File dir) throws IOException {
        if (dir.exists()) {
            if (!dir.isDirectory()) {
                throw new IOException("File exists but is not a directory; directory: " + tryCanonicalPath(dir));
            }
        }
        else {
            if (!dir.mkdirs()) {
                // Double-check that the directory wasn't created.
                if (!dir.isDirectory()) {
                    throw new IOException("Unable to create directory; directory: " + tryCanonicalPath(dir));
                }
            }
        }
    }

    public static File getCurrentDir() {
        return new File(".");
    }

    public static File getUserDir() {
        return new File(SystemProperties.USER_DIR.getValue());
    }

    private XtraFiles() { /* Cannot be instantiated */ }
}

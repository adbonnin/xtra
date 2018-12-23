package fr.adbonnin.xtra.jdownloader.folderwatch;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.adbonnin.xtra.io.XtraFiles;

import java.io.*;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static fr.adbonnin.xtra.io.XtraIO.closeQuietly;
import static fr.adbonnin.xtra.io.XtraFiles.EXTENSION_SEPARATOR;
import static java.nio.charset.StandardCharsets.UTF_8;

public final class XtraCrawlJobs {

    public static final String CRAWLJOB_EXTENSION = "crawljob";

    public static final String FOLDERWATCH_DIRNAME = "folderwatch";

    private static final ObjectMapper MAPPER = new ObjectMapper()
        .setSerializationInclusion(NON_NULL);

    public static File newCrawlJobCleanedFile(File jdownloaderDir, String basename) {
        final File folderWatchFile = new File(jdownloaderDir, FOLDERWATCH_DIRNAME);
        final String crawlJobFilename = basename + EXTENSION_SEPARATOR + CRAWLJOB_EXTENSION;
        return XtraFiles.newNonExistentCleanedFile(folderWatchFile, crawlJobFilename);
    }

    public static void write(Iterable<CrawlJob> crawlJobs, File file) throws IOException {

        FileOutputStream out = null;
        OutputStreamWriter writer = null;
        try {
            out = new FileOutputStream(file);
            writer = new OutputStreamWriter(out, UTF_8);
            write(crawlJobs, writer);
        }
        finally {
            closeQuietly(writer);
            closeQuietly(out);
        }
    }

    public static String write(Iterable<CrawlJob> crawlJobs) throws IOException {

        final StringWriter writer = new StringWriter();
        try {
            write(crawlJobs, writer);
        }
        finally {
            closeQuietly(writer);
        }

        return writer.toString();
    }

    public static void write(Iterable<CrawlJob> crawlJobs, Writer writer) throws IOException {
        MAPPER.writerWithDefaultPrettyPrinter().writeValue(writer, crawlJobs);
    }

    private XtraCrawlJobs() { /* Cannot be instantiated */ }
}

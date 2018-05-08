package fr.adbonnin.xtra.webclient;

import fr.adbonnin.xtra.io.XtraFiles;
import fr.adbonnin.xtra.net.XtraResources;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;

import java.io.*;
import java.net.URI;

import static fr.adbonnin.xtra.io.XtraCloseables.closeQuietly;
import static java.util.Objects.requireNonNull;

public class DownloadFileResponseHandler implements ResponseHandler<File> {

    private final File file;

    private final RedownloadMode redownloadMode;

    public DownloadFileResponseHandler(File file, RedownloadMode redownloadMode) {
        this.file = requireNonNull(file);
        this.redownloadMode = requireNonNull(redownloadMode);
    }

    @Override
    public File handleResponse(HttpResponse response) throws IOException {
        final File downloadFile;
        if (redownloadMode.override()) {
            downloadFile = file;
        }
        else {
            downloadFile = newNonExistentFile(file);
            if (!redownloadMode.redownload() && downloadFile != file) {
                return file;
            }
        }

        OutputStream out = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(downloadFile));
            XtraHttpClient.downloadFile(response, out);
        }
        finally {
            closeQuietly(out);
        }

        return downloadFile;
    }

    /**
     * Create new non existent file from {@code file}.
     * Note: Can be overridden.
     */
    protected File newNonExistentFile(File file) {
        return XtraFiles.newNonExistentFile(file);
    }

    public static DownloadFileResponseHandler downloadToDir(URI uri, File dir, String defaultEmptyFilename, RedownloadMode redownloadMode) {
        final File file = XtraResources.newDownloadFile(uri, dir, defaultEmptyFilename);
        return new DownloadFileResponseHandler(file, redownloadMode);
    }
}

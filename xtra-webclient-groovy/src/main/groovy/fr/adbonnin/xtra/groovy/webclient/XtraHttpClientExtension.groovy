package fr.adbonnin.xtra.groovy.webclient

import fr.adbonnin.xtra.net.XtraHttp
import fr.adbonnin.xtra.webclient.DownloadFileResponseHandler
import fr.adbonnin.xtra.webclient.ParseDocumentResponseHandler
import fr.adbonnin.xtra.webclient.RedownloadMode
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpUriRequest
import org.apache.http.client.utils.URIBuilder
import org.jsoup.nodes.Document

import static fr.adbonnin.xtra.net.XtraHttp.encodeUrl
import static fr.adbonnin.xtra.webclient.DownloadFileResponseHandler.downloadToDir

final class XtraHttpClientExtension {

    static Document parseDocument(HttpClient httpClient, String url) {
        return parseDocument(httpClient, newEncodedHttpGet(url))
    }

    static Document parseDocument(HttpClient httpClient, HttpUriRequest request) {
        def responseHandler = new ParseDocumentResponseHandler(request.URI.toString())
        return httpClient.execute(request, responseHandler)
    }

    static File downloadFile(HttpClient httpClient, String url, File file, RedownloadMode redownloadMode) {
        return downloadFile(httpClient, newEncodedHttpGet(url), file, redownloadMode)
    }

    static File downloadFile(HttpClient httpClient, HttpUriRequest request, File file, RedownloadMode redownloadMode) {
        def responseHandler = new DownloadFileResponseHandler(file, redownloadMode)
        return httpClient.execute(request, responseHandler)
    }

    static File downloadToDir(HttpClient httpClient, String url, File dir, String defaultEmptyFilename, RedownloadMode redownloadMode) {
        return downloadToDir(httpClient, newEncodedHttpGet(url), dir, defaultEmptyFilename, redownloadMode)
    }

    static File downloadToDir(HttpClient httpClient, HttpUriRequest request, File dir, String defaultEmptyFilename, RedownloadMode redownloadMode) {
        def responseHandler = downloadToDir(request.getURI(), dir, defaultEmptyFilename, redownloadMode)
        return httpClient.execute(request, responseHandler)
    }

    static String firstQueryParam(URIBuilder uriBuilder, String name) {
        return XtraHttp.firstQueryParam(uriBuilder, name)
    }

    private static HttpGet newEncodedHttpGet(String url) {
        return new HttpGet(encodeUrl(url))
    }
}

package fr.adbonnin.xtra.net;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;

public final class XtraHttp {

    public static final char PATH_SEPARATOR = '/';

    public static final String HEADER_CONTENT_TYPE = "Content-Type";

    public static final String MIME_BITTORRENT = "application/x-bittorrent";
    public static final String MIME_FORM = "application/x-www-form-urlencoded";
    public static final String MIME_HTML = "text/html";

    public static URI newUri(String baseUri, String relativePath, List<NameValuePair> parameters) {
        final URI uri = newUri(baseUri, relativePath);
        try {
            return new URIBuilder(uri).addParameters(parameters).build();
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static URI newUri(String baseUrl, String relativePath) {
        try {
            return newUri(new URL(new URL(baseUrl), relativePath));
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static URI newUri(String baseUrl) {
        return newUri(newUrl(baseUrl));
    }

    public static URI newUri(URL url) {
        try {
            return new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static URL newUrl(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static String encodeUrl(String url) {
        return encodeUrl(newUrl(url)).toExternalForm();
    }

    public static URL encodeUrl(URL url) {
        try {
            final String escaped = url.toExternalForm()
                .replace(" ", "%20")
                .replace("[", "%5B")
                .replace("]", "%5D")
                .replace("\"", "%22");

            final URI uri = new URI(escaped);
            return new URL(uri.toASCIIString());
        }
        catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static String tryDecodeUrl(String uri, String encoding) {
        try {
            return URLDecoder.decode(uri, encoding);
        } catch (UnsupportedEncodingException e) {
            return uri;
        }
    }

    public static String firstQueryParam(URIBuilder uriBuilder, String name) {
        requireNonNull(uriBuilder);
        requireNonNull(name);

        for (NameValuePair queryParam : uriBuilder.getQueryParams()) {
            if (name.equals(queryParam.getName())) {
                return queryParam.getValue();
            }
        }

        return null;
    }

    private XtraHttp() { /* Cannot be instantiated */}
}

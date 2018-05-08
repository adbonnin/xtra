package fr.adbonnin.xtra.webclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

public final class XtraHttpClient {

    public static final SSLConnectionSocketFactory UNSAFE_SSL_SOCKET_FACTORY = buildUnsafeSSLSocketFactory();

    public static HttpClient buildUnsafeHttpClient() {
        return setUnsafeHttpClientBuilder(HttpClientBuilder.create()).build();
    }

    public static HttpClientBuilder setUnsafeHttpClientBuilder(HttpClientBuilder builder) {
        return builder
            .setSSLSocketFactory(UNSAFE_SSL_SOCKET_FACTORY)
            .setRedirectStrategy(new LaxRedirectStrategy());
    }

    public static String getLastLocation(HttpClientContext context, String defaullEmpty) {
        final List<URI> locations = context.getRedirectLocations();
        return locations == null ? defaullEmpty : locations.get(locations.size() - 1).toString();
    }

    public static void downloadFile(HttpResponse response, OutputStream output) throws IOException {
        final HttpEntity entity = response.getEntity();
        try {
            entity.writeTo(output);
        }
        finally {
            EntityUtils.consumeQuietly(entity);
        }
    }

    public static Document parseDocument(HttpResponse response, String baseUri, String charsetName) throws IOException {
        final HttpEntity entity = response.getEntity();
        try {
            return Jsoup.parse(entity.getContent(), charsetName, baseUri);
        }
        finally {
            EntityUtils.consumeQuietly(entity);
        }
    }

    /** @see XtraHttpClient#UNSAFE_SSL_SOCKET_FACTORY */
    private static SSLConnectionSocketFactory buildUnsafeSSLSocketFactory() {
        final SSLContext sslContext;
        try {
            sslContext = new SSLContextBuilder()
                .loadTrustMaterial(new TrustSelfSignedStrategy())
                .build();

            sslContext.init(new KeyManager[0], new TrustManager[]{new DummyTrustManager()}, new SecureRandom());
            sslContext.init(null, new X509TrustManager[]{new DummyTrustManager()}, new SecureRandom());
        }
        catch (GeneralSecurityException e) {
            throw new IllegalArgumentException(e); // never append
        }

        return new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
    }

    private static class DummyTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            // everything is trusted
        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            // everything is trusted
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private XtraHttpClient() { /* Cannot be instantiated */ }
}

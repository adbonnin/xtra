package fr.adbonnin.xtra.webclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.jsoup.nodes.Document;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.requireNonNull;

public class ParseDocumentResponseHandler implements ResponseHandler<Document> {

    private final String charsetName;

    private final String baseUri;

    public ParseDocumentResponseHandler(String baseUri) {
        this(baseUri, UTF_8.name());
    }

    public ParseDocumentResponseHandler(String baseUri, String charsetName) {
        this.charsetName = requireNonNull(charsetName);
        this.baseUri = requireNonNull(baseUri);
    }

    @Override
    public Document handleResponse(HttpResponse response) throws IOException {
        return XtraHttpClient.parseDocument(response, baseUri, charsetName);
    }
}

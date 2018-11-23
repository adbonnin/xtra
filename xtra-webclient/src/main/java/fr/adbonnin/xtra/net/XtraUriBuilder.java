package fr.adbonnin.xtra.net;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;

import java.util.List;
import java.util.Objects;

public final class XtraUriBuilder {

    public static NameValuePair getQueryParam(URIBuilder uriBuilder, String name) {

        final List<NameValuePair> params = uriBuilder.getQueryParams();
        for (NameValuePair param : params) {
            if (Objects.equals(param.getName(), name)) {
                return param;
            }
        }

        return null;
    }

    private XtraUriBuilder() { /* Cannot be instantiated */ }
}

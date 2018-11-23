package fr.adbonnin.xtra.groovy.net

import fr.adbonnin.xtra.net.XtraUriBuilder
import org.apache.http.NameValuePair
import org.apache.http.client.utils.URIBuilder

final class XtraUriBuilderExtension {

    static NameValuePair getQueryParam(URIBuilder uriBuilder, String name) {
        return XtraUriBuilder.getQueryParam(uriBuilder, name)
    }

    private XtraUriBuilderExtension() { /* Cannot be instantiated */}
}

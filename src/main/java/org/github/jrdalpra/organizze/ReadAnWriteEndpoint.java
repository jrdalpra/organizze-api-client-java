package org.github.jrdalpra.organizze;

import java.net.http.HttpResponse;

import org.github.jrdalpra.organizze.utils.ExtendedHttpClient;
import org.github.jrdalpra.organizze.utils.ExtendedURI;

public class ReadAnWriteEndpoint<T extends Resource> extends ReadOnlyEndpoint<T> {

    public ReadAnWriteEndpoint(ExtendedHttpClient http, ExtendedURI root, Class<T> single, Class multiple) {
        super(http, root, single, multiple);
    }

    public HttpResponse<T> post(T value) {
        return this.http.post(this.address.uri(), value);
    }

    public HttpResponse<T> put(T value) {
        return this.http.put(this.address.slash(value.getId()).uri(), value);
    }

    public HttpResponse<Void> delete(T value) {
        return this.http.delete(this.address.slash(value.getId()).uri());
    }
}

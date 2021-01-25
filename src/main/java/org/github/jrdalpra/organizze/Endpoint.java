package org.github.jrdalpra.organizze;

import java.net.http.HttpResponse;

import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.github.jrdalpra.organizze.utils.ExtendedHttpClient;
import org.github.jrdalpra.organizze.utils.ExtendedURI;

/**
 * Endpoint is a utility class to easily call GET, POST, PUT and DELETE methods
 *
 * @param <T>
 */
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
public class Endpoint<T extends Resource> {

    ExtendedHttpClient http;
    ExtendedURI address;
    Class<T> single;
    Class<T[]> multiple;

    public Endpoint(ExtendedHttpClient http, ExtendedURI root, Class<T> single, Class multiple) {
        this.http = http;
        this.address = root;
        this.single = single;
        this.multiple = multiple;
    }

    @SneakyThrows
    public HttpResponse<T[]> get() {
        return this.http.get(this.address.uri(), this.multiple);
    }

    @SneakyThrows
    public HttpResponse<T> get(Integer id) {
        return this.http.get(this.address.slash(id).uri(), this.single);
    }

    public HttpResponse<T[]> list(Object... args) {
        var path = this.address;
        for (var arg : args) {
            path = path.slash(arg);
        }
        return this.http.get(path.uri(), this.multiple);
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

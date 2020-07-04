package org.github.jrdalpra.organizze;

import java.net.http.HttpResponse;

import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.github.jrdalpra.organizze.utils.ExtendedHttpClient;
import org.github.jrdalpra.organizze.utils.ExtendedURI;


@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
public class ReadOnlyEndpoint<T extends Resource> {

    ExtendedHttpClient http;
    ExtendedURI address;
    Class<T> single;
    Class<T[]> multiple;

    public ReadOnlyEndpoint(ExtendedHttpClient http, ExtendedURI root, Class<T> single, Class multiple) {
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

    public static interface Get<T extends Resource> {
        HttpResponse<T[]> get();

        HttpResponse<T> get(Integer id);
    }

    public static interface List<T extends Resource> {
        HttpResponse<T[]> list(Object... args);
    }
}

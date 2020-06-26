package org.github.jrdalpra.organizze.utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.Delegate;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ExtendedHttpClient extends HttpClient {

    @Delegate
    HttpClient delegate;
    JsonMapper handler;

    public ExtendedHttpClient(HttpClient delegate) {
        this.delegate = delegate;
        this.handler = new JsonMapper(this);
    }

    @SneakyThrows
    public <T> HttpResponse<T> get(URI uri, Class<T> type) {
        return this.send(HttpRequest.newBuilder(uri).GET().build(), handler.deserialize(type));
    }

    @SneakyThrows
    public <T> HttpResponse<T> post(URI uri, T value) {
        return this.send(
                HttpRequest.newBuilder(uri).POST(handler.serialize(value)).build(),
                handler.deserialize(value.getClass())
        );
    }


}

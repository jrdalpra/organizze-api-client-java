package org.github.jrdalpra.organizze;

import java.net.URI;
import java.net.http.HttpResponse;

import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.github.jrdalpra.organizze.utils.ExtendedHttpClient;
import org.github.jrdalpra.organizze.utils.ExtendedURI;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Users {

    ExtendedHttpClient http;
    ExtendedURI address;

    public Users(ExtendedHttpClient http, ExtendedURI root) {
        this.http = http;
        this.address = root.slash(URI.create("users"));
    }

    @SneakyThrows
    public HttpResponse<User[]> get() {
        return this.http.get(this.address.uri(), User[].class);
    }

    @SneakyThrows
    public HttpResponse<User> get(Integer id) {
        return this.http.get(this.address.slash(id).uri(), User.class);
    }
}

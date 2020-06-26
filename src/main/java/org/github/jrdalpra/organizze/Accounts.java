package org.github.jrdalpra.organizze;

import java.net.URI;
import java.net.http.HttpResponse;

import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.github.jrdalpra.organizze.utils.ExtendedHttpClient;
import org.github.jrdalpra.organizze.utils.ExtendedURI;


@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Accounts {

    ExtendedHttpClient http;
    ExtendedURI address;

    public Accounts(ExtendedHttpClient http, ExtendedURI root) {
        this.http = http;
        this.address = root.slash(URI.create("accounts"));
    }

    @SneakyThrows
    public HttpResponse<Account[]> get() {
        return this.http.get(this.address.uri(), Account[].class);
    }

    @SneakyThrows
    public HttpResponse<Account> get(Integer id) {
        return this.http.get(this.address.slash(id).uri(), Account.class);
    }

    public HttpResponse<Account> post(Account value) {
        return this.http.post(this.address.uri(), value);
    }

}

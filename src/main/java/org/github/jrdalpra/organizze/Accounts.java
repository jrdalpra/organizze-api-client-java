package org.github.jrdalpra.organizze;

import java.net.http.HttpResponse;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.github.jrdalpra.organizze.utils.ExtendedHttpClient;
import org.github.jrdalpra.organizze.utils.ExtendedURI;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Accounts {

    ReadAnWriteEndpoint<Account> client;

    public Accounts(ExtendedHttpClient http, ExtendedURI root) {
        this.client = new ReadAnWriteEndpoint<>(http, root.slash("accounts"), Account.class, Account[].class);
    }

    public HttpResponse<Account[]> get() {
        return this.client.get();
    }

    public HttpResponse<Account> get(Integer id) {
        return this.client.get(id);
    }

    public HttpResponse<Account> post(Account value) {
        return this.client.post(value);
    }

}

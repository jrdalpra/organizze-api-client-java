package org.github.jrdalpra.organizze;

import java.net.http.HttpResponse;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.github.jrdalpra.organizze.utils.ExtendedHttpClient;
import org.github.jrdalpra.organizze.utils.ExtendedURI;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Users {

    ReadOnlyEndpoint<User> client;

    public Users(ExtendedHttpClient http, ExtendedURI root) {
        this.client = new ReadOnlyEndpoint<User>(http, root.slash("users"), User.class, User[].class);
    }

    public HttpResponse<User[]> get() {
        return client.get();
    }

    public HttpResponse<User> get(Integer id) {
        return client.get(id);
    }

}

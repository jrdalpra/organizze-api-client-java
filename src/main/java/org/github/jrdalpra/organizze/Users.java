package org.github.jrdalpra.organizze;

import java.net.http.HttpResponse;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.github.jrdalpra.organizze.utils.ExtendedHttpClient;
import org.github.jrdalpra.organizze.utils.ExtendedURI;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Users {

    Endpoint<User> endpoint;

    public Users(ExtendedHttpClient http, ExtendedURI root) {
        this.endpoint = new Endpoint<User>(http, root.slash("users"), User.class, User[].class);
    }

    public HttpResponse<User[]> get() {
        return this.endpoint.get();
    }

    public HttpResponse<User> get(Integer id) {
        return this.endpoint.get(id);
    }

}

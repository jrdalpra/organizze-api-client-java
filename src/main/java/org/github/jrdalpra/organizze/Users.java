package org.github.jrdalpra.organizze;

import lombok.AccessLevel;
import lombok.experimental.Delegate;
import lombok.experimental.FieldDefaults;
import org.github.jrdalpra.organizze.utils.ExtendedHttpClient;
import org.github.jrdalpra.organizze.utils.ExtendedURI;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Users {

    @Delegate
    ReadOnlyEndpoint<User> client;

    public Users(ExtendedHttpClient http, ExtendedURI root) {
        this.client = new ReadOnlyEndpoint<User>(http, root.slash("users"), User.class, User[].class);
    }
}

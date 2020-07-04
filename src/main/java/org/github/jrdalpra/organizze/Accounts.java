package org.github.jrdalpra.organizze;

import lombok.AccessLevel;
import lombok.experimental.Delegate;
import lombok.experimental.FieldDefaults;
import org.github.jrdalpra.organizze.utils.ExtendedHttpClient;
import org.github.jrdalpra.organizze.utils.ExtendedURI;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Accounts {

    @Delegate(excludes = {ReadAnWriteEndpoint.Put.class})
    ReadAnWriteEndpoint<Account> client;

    public Accounts(ExtendedHttpClient http, ExtendedURI root) {
        this.client = new ReadAnWriteEndpoint<>(http, root.slash("accounts"), Account.class, Account[].class);
    }
}

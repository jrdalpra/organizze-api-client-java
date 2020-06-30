package org.github.jrdalpra.organizze;

import lombok.AccessLevel;
import lombok.experimental.Delegate;
import lombok.experimental.FieldDefaults;
import org.github.jrdalpra.organizze.utils.ExtendedHttpClient;
import org.github.jrdalpra.organizze.utils.ExtendedURI;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Budgets {

    @Delegate
    ReadAnWriteEndpoint<Budget> client;

    public Budgets(ExtendedHttpClient http, ExtendedURI root) {
        this.client = new ReadAnWriteEndpoint<Budget>(http, root.slash("budgets"), Budget.class, Budget[].class);
    }
}

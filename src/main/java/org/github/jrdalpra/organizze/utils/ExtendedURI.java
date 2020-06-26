package org.github.jrdalpra.organizze.utils;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.net.URI;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExtendedURI {

    public static ExtendedURI of(String string) {
        return new ExtendedURI(URI.create(string));
    }


    URI uri;

    public ExtendedURI slash(URI segment) {
        return new ExtendedURI(URI.create(this.uri + "/" + segment));
    }

    public ExtendedURI slash(Object value) {
        return slash(URI.create(value.toString()));
    }


    public URI uri() {
        return uri;
    }
}

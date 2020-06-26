package org.github.jrdalpra.organizze;

import static java.net.http.HttpClient.Redirect.ALWAYS;
import static java.net.http.HttpClient.Version.HTTP_1_1;
import static java.net.http.HttpClient.newBuilder;

import java.net.Authenticator;
import java.net.URI;
import java.util.logging.Logger;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.github.jrdalpra.organizze.utils.BasicAuthentication;
import org.github.jrdalpra.organizze.utils.ExtendedHttpClient;
import org.github.jrdalpra.organizze.utils.ExtendedURI;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class Organizze {

    ExtendedHttpClient http;
    ExtendedURI root;
    Logger log;

    private static ExtendedHttpClient defaultHttpClient(Authenticator auth) {
        return new ExtendedHttpClient(newBuilder().authenticator(auth).followRedirects(ALWAYS).version(HTTP_1_1).build());
    }

    private static ExtendedURI root() {
        return new ExtendedURI(URI.create("https://api.organizze.com.br/rest/v2"));
    }

    public Organizze(Authenticator auth, Logger log) {
        this(defaultHttpClient(auth), root(), log);
    }


    public Organizze(String username, String password, Logger log) {
        this(new BasicAuthentication(username, password, log), log);
    }

    public Organizze(String username, String password) {
        this(username, password, Logger.getLogger(Organizze.class.getName()));
    }

    public Users users() {
        return new Users(this.http, this.root);
    }

    public Accounts accounts() {
        return new Accounts(this.http, this.root);
    }

}

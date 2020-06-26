package org.github.jrdalpra.organizze.utils;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.logging.Logger;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BasicAuthentication extends Authenticator {
    String username, password;
    Logger log;

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(this.username, this.password.toCharArray());
    }
}
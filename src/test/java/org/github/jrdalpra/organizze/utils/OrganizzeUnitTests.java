package org.github.jrdalpra.organizze.utils;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import com.pgssoft.httpclient.HttpClientMock;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.apache.commons.io.IOUtils;
import org.github.jrdalpra.organizze.Organizze;
import org.github.jrdalpra.organizze.User;
import org.junit.Assert;
import org.junit.Before;

@FieldDefaults(level = AccessLevel.PROTECTED)
public class OrganizzeUnitTests {

    static final Charset DEFAULT_CS = Charset.defaultCharset();
    User expectedUser;

    protected static InputStream resource(String name) {
        var resource = OrganizzeUnitTests.class.getResourceAsStream(name);
        Assert.assertNotNull("Resource " + name + " is null!", resource);
        return resource;
    }

    @SneakyThrows
    protected static String toString(String resource) {
        var result = IOUtils.toString(resource(resource), DEFAULT_CS);
        Assert.assertNotNull("String from " + resource + " is null", result);
        return result;
    }

    protected static ZonedDateTime toDateAndTime(String value) {
        return ZonedDateTime.parse(value, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }


    HttpClientMock http;
    Logger log;
    Organizze organizze;

    @Before
    public void mocks() {
        this.http = new HttpClientMock();
        this.log = Logger.getGlobal();
        this.organizze = new Organizze(new ExtendedHttpClient(this.http), ExtendedURI.of("http://localhost"), log);
    }

}

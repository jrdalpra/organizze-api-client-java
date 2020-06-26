package org.github.jrdalpra.organizze.utils;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.github.jrdalpra.organizze.User;
import org.junit.Before;
import org.junit.Test;

public class UsersUnitTests extends OrganizzeUnitTests {
    @Override
    @Before
    public void mocks() {
        super.mocks();
        this.expectedUser = new User(3, "Esdras Mayrink", "falecom@email.com.br", "admin");
        this.http.onGet("http://localhost/users").doReturn(toString("users.json"));
        this.http.onGet("http://localhost/users/3").doReturn(toString("user.json"));
    }

    @Test
    public void when_getting_all_must_return_expected_result() {
        var actual = this.organizze.users().get().body();
        assertThat("Users should not be empty", asList(actual), hasSize(1));
        assertThat("User is not the one expected", actual[0], equalTo(this.expectedUser));
    }

    @Test
    public void when_getting_one_must_return_expected_result() {
        var actual = this.organizze.users().get(3).body();
        assertThat("Users should not be null", actual, not(nullValue()));
        assertThat("User is not the one expected", actual, equalTo(this.expectedUser));
    }
}


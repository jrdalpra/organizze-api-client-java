package org.github.jrdalpra.organizze.utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;

import lombok.SneakyThrows;
import org.github.jrdalpra.organizze.Account;
import org.junit.Before;
import org.junit.Test;

public class AccountsUnitTests extends OrganizzeUnitTests {

    private List<Account> allExpectedAccounts;

    @Override
    @Before
    public void mocks() {
        super.mocks();
        this.allExpectedAccounts = Arrays.asList(
                new Account(3, "Bradesco CC", "Some descriptions", false, toDateAndTime("2015-06-22T16:17:03-03:00"), toDateAndTime("2015-08-31T22:24:24-03:00"), true, "checking"),
                new Account(4, "Caixa Poupança", "", false, toDateAndTime("2015-08-20T17:59:06-03:00"), toDateAndTime("2015-08-31T18:46:23-03:00"), false, "savings"),
                new Account(5, "Carteira", null, false, toDateAndTime("2015-08-31T18:19:01-03:00"), toDateAndTime("2015-08-31T18:19:01-03:00"), false, "other")
        );
    }


    @Test
    @SneakyThrows
    public void when_getting_all_must_return_expected_result() {
        this.http.onGet("http://localhost/accounts").doReturn(toString("accounts.json"));
        var current = 0;
        var response = this.organizze.accounts().get().body();
        assertThat("Response has wrong size", response, arrayWithSize(3));
        assertThat("Expected has wrong size", this.allExpectedAccounts, hasSize(3));
        for (var expected : this.allExpectedAccounts) {
            var actual = response[current];
            assertThat("Account " + current + "is null", actual, not(nullValue()));
            assertThat("Actual should be equal to expected", actual, equalTo(expected));
            current++;
        }
    }

    @Test
    @SneakyThrows
    public void when_getting_one_must_return_expected_result() {
        this.http.onGet("http://localhost/accounts/3").doReturn(toString("account.json"));
        var expected = this.allExpectedAccounts.get(0);
        var actual = this.organizze.accounts().get(3).body();
        assertThat("Account should not be null", actual, not(nullValue()));
        assertThat("Account is not the one expected", actual, equalTo(expected));
    }

    @Test
    @SneakyThrows
    public void when_getting_missing_one_must_return_expected_result() {
        this.http.onGet("http://localhost/accounts/999").doReturn(toString("error.json")).withStatus(401);
        var actual = this.organizze.accounts().get(999);
        assertThat("Body should have errors", actual.body().hasErrors(), equalTo(true));
        assertThat("Error should be expected", actual.body().getError(), equalTo("Não autorizado"));
    }

    @Test
    @SneakyThrows
    public void when_getting_missing_must_have_error() {
        this.http.onPost("http://localhost/accounts").doReturn(toString("account_with_errors.json")).withStatus(422);
        var actual = this.organizze.accounts().post(new Account());
        assertThat("Status code should be 401", actual.statusCode(), equalTo(422));
        assertThat("Response should have errors", actual.body().hasErrors(), equalTo(true));
        assertThat(
                "Errors should have at least one entry",
                actual.body().getErrors(),
                hasEntry(any(String.class), any(List.class))
        );
        assertThat(
                "Errors should match the expected value",
                actual.body().getErrors(),
                hasEntry(equalTo("name"), anything())
        );
        assertThat(
                "Errors should match the expected value",
                actual.body().getErrors().get("name"),
                hasItems("não pode estar em branco")
        );
        assertThat("Single error should be null", actual.body().getError(), nullValue());

    }

    @Test
    public void builder_must_build_expected_result() {
        var account = Account.with().name("name").type("checking").description("description").defaultOne(false).get();
        assertThat("Name not expected", account.getName(), equalTo("name"));
        assertThat("Type not expected", account.getType(), equalTo("checking"));
        assertThat("Description not expected", account.getDescription(), equalTo("description"));
        assertThat("Default not expected", account.getDefaultOne(), equalTo(false));
        assertThat("ToString not expected",
                account.toString(),
                equalTo("Account(super=ErrorAware(error=null, errors=null), id=null, name=name, description=description, archived=null, created_at=null, updated_at=null, defaultOne=false, type=checking)"));
    }
}

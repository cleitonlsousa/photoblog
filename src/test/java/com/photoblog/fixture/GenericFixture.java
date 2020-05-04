package com.photoblog.fixture;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.photoblog.api.request.AccountRequest;
import com.photoblog.api.request.JwtRequest;
import com.photoblog.api.response.AccountResponse;
import com.photoblog.api.response.JwtResponse;
import com.photoblog.model.Account;

public class GenericFixture implements TemplateLoader {

    public final static String ACCOUNT_EMAIL_PETER = "peterparker@email.com";
    public final static String ACCOUNT_EMAIL_TONY = "tonystark@email.com";

    public final static String ACCOUNT_PETER = "ACCOUNT_PETER";
    public final static String ACCOUNT_TONY = "ACCOUNT_TONY";

    public final static String ACCOUNT_REQUEST_PETER = "ACCOUNT_REQUEST_PETER";
    public final static String ACCOUNT_REQUEST_TONY = "ACCOUNT_REQUEST_TONY";
    public final static String JWT_REQUEST = "JWT_REQUEST";
    public final static String JWT_RESPONSE = "JWT_RESPONSE";

    @Override
    public void load() {

        Fixture.of(AccountRequest.class).addTemplate(ACCOUNT_REQUEST_PETER, new Rule() {
            {
                add("firstName", "peter");
                add("lastName", "parker");
                add("email", "peterparker@email.com");
                add("secret", "123456");

            }
        });

        Fixture.of(AccountRequest.class).addTemplate(ACCOUNT_REQUEST_TONY, new Rule() {
            {
                add("firstName", "tony");
                add("lastName", "stark");
                add("email", "tonystark@email.com");
                add("secret", "123456");

            }
        });

        Fixture.of(Account.class).addTemplate(ACCOUNT_PETER, new Rule() {
            {
                add("id", 1);
                add("firstName", "peter");
                add("lastName", "parker");
                add("email", "peterparker@email.com");

            }
        });

        Fixture.of(Account.class).addTemplate(ACCOUNT_TONY, new Rule() {
            {
                add("id", 2);
                add("firstName", "tony");
                add("lastName", "stark");
                add("email", "tonystark@email.com");

            }
        });

        Fixture.of(JwtRequest.class).addTemplate(JWT_REQUEST, new Rule() {
            {
                add("username", "johndoe@email.com");
                add("password", "123456");

            }
        });

        Fixture.of(JwtResponse.class).addTemplate(JWT_RESPONSE, new Rule() {
            {
                add("token", "token");

            }
        });


    }

}


package com.photoblog.fixture;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.photoblog.api.request.AccountRequest;
import com.photoblog.api.request.JwtRequest;

public class GenericFixture implements TemplateLoader {

    public final static String ACCOUNT_REQUEST_CREATE = "ACCOUNT_REQUEST_CREATE";
    public final static String ACCOUNT_REQUEST_UPDATE = "ACCOUNT_REQUEST_UPDATE";
    public final static String JWT_REQUEST = "JWT_REQUEST";

    @Override
    public void load() {

        Fixture.of(AccountRequest.class).addTemplate(ACCOUNT_REQUEST_CREATE, new Rule() {
            {
                add("firstName", "john");
                add("lastName", "doe");
                add("email", "johndoe@email.com");
                add("secret", "123456");

            }
        }).addTemplate(ACCOUNT_REQUEST_UPDATE).inherits(ACCOUNT_REQUEST_CREATE, new Rule() {
            {
                add("lastName", "Rambo");
            }

        });

        Fixture.of(JwtRequest.class).addTemplate(JWT_REQUEST, new Rule() {
            {
                add("username", "johndoe@email.com");
                add("password", "123456");

            }
        });

    }

}


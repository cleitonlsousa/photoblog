package com.photoblog.api.request;

import javax.validation.constraints.NotNull;

public class AccountRequest {

    @NotNull(message = "firstName is required")
    private String firstName;
    private String lastName;
    @NotNull(message = "email is required")
    private String email;
    @NotNull(message = "secret is required")
    private String secret;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}

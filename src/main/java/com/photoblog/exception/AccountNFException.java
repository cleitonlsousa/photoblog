package com.photoblog.exception;

public class AccountNFException extends RuntimeException {

    public AccountNFException(String email) {

        super(String.format("Account with email %s not found", email));
    }

}

package com.photoblog.exception;

public class EmailExistsException extends RuntimeException {

    public EmailExistsException(String email) {

        super(String.format("E-mail %s already exist", email));
    }

}

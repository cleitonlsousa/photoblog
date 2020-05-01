package com.photoblog.exception;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException() {
        super("Unauthorized, you don't have the needed permissions to perform this operation");
    }
}

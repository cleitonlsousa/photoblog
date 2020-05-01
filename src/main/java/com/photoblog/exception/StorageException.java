package com.photoblog.exception;

public class StorageException extends RuntimeException {

    public StorageException(String fileName) {

        super(String.format("Could not store file %s . Please try again!", fileName));
    }
}

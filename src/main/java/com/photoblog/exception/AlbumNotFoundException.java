package com.photoblog.exception;

public class AlbumNotFoundException extends RuntimeException {

    public AlbumNotFoundException(Integer id) {

        super(String.format("Album with Id %s not found", id));
    }

}

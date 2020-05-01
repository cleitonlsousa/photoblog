package com.photoblog.exception;

public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException(Integer id) {

        super(String.format("Post with Id %s not found", id));
    }

}

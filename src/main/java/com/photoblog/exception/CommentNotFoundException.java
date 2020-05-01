package com.photoblog.exception;

public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException(Integer id) {

        super(String.format("Comment with Id %s not found", id));
    }

}

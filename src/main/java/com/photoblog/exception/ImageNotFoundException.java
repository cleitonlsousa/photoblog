package com.photoblog.exception;

public class ImageNotFoundException extends RuntimeException {

    public ImageNotFoundException(Integer id) {

        super(String.format("Image not found for album %s ", id));
    }

}

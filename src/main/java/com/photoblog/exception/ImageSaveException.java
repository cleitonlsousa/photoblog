package com.photoblog.exception;

public class ImageSaveException extends RuntimeException {

    public ImageSaveException() {

        super("Could not save file. Please try again!");
    }
}

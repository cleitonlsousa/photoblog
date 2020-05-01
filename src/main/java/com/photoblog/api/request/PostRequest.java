package com.photoblog.api.request;

import javax.validation.constraints.NotNull;

public class PostRequest {

    @NotNull(message = "title is required")
    private String title;
    @NotNull(message = "body is required")
    private String body;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}

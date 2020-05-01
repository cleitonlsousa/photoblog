package com.photoblog.api.request;

import javax.validation.constraints.NotNull;

public class AlbumRequest {

    @NotNull(message = "description is required")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

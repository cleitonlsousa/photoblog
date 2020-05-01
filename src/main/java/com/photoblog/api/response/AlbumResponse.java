package com.photoblog.api.response;

import java.time.LocalDate;
import java.util.List;

public class AlbumResponse {

    private Integer id;
    private String description;
    private String createdBy;
    private LocalDate createdDate;
    private List<ImageResponse> photos;

    public AlbumResponse(Integer id, String description, String createdBy, LocalDate createdDate) {
        this.id = id;
        this.description = description;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public List<ImageResponse> getPhotos() {
        return photos;
    }

    public void setPhotos(List<ImageResponse> photos) {
        this.photos = photos;
    }
}

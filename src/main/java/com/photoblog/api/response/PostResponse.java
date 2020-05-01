package com.photoblog.api.response;

import com.photoblog.model.Comment;

import java.time.LocalDate;
import java.util.List;

public class PostResponse {

    private Integer id;
    private String title;
    private String body;
    private String createdBy;
    private LocalDate createdDate;
    private List<CommentResponse> comments;

    public PostResponse(Integer id, String title, String body, String createdBy, LocalDate createdDate) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public List<CommentResponse> getComments() {
        return comments;
    }

    public void setComments(List<CommentResponse> comments) {
        this.comments = comments;
    }
}

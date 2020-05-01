package com.photoblog.api.response;

import java.time.LocalDate;

public class CommentResponse {

    private Integer id;
    private String author;
    private String comment;
    private LocalDate createdDate;

    public CommentResponse(Integer id, String author, String comment, LocalDate createdDate) {
        this.id = id;
        this.author = author;
        this.comment = comment;
        this.createdDate = createdDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
